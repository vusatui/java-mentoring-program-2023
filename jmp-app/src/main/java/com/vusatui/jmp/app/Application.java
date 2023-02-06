package com.vusatui.jmp.app;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import com.vusatui.jmp.cloud.service.impl.exception.SubscriptionNotFoundException;
import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.CreditCardDTO;
import com.vusatui.jmp.dto.SubscriptionDTO;
import com.vusatui.jmp.dto.UserDTO;
import com.vusatui.jmp.service.api.Service;
import com.vusatui.jmt.bank.api.Bank;

public class Application {

    public static void main(String[] args) throws SubscriptionNotFoundException {
        System.out.println("*** Start application ***");

        Optional<Bank> optionalBank = ServiceLoader.load(Bank.class).findFirst();
        Optional<Service> optionalService = ServiceLoader.load(Service.class).findFirst();

        if (!optionalBank.isPresent() && !optionalService.isPresent()) {
            return;
        }

        System.out.println("*** All required services was loaded ***");

        Bank bank = optionalBank.get();
        Service service = optionalService.get();

        System.out.println("*** Creating a user ***");
        var user1 = new UserDTO("Foo", "Bar", LocalDate.now().minus(Period.of(18, 0, 0)));

        System.out.println("*** Creating bank card ***");
        BankCardDTO bankCardDTO = bank.createBankCard(user1, CreditCardDTO::new);

        System.out.println("*** Making a subscription ***");
        service.subscribe(bankCardDTO);

        System.out.println("*** All subscriptions before now: ***");
        service.getAllSubscriptionsByCondition(subscriptionDTO -> subscriptionDTO.getStartDate().equals(LocalDate.now()))
                .forEach(subscriptionDTO -> System.out.println(getSubscriptionString(subscriptionDTO)));
        System.out.println("***");

        System.out.printf("*** Average users age is: %s years.", service.getAverageUsersAge());

        var cardNumber = bankCardDTO.getNumber();
        var subscription = service.getSubscriptionByBankCardNumber(cardNumber).orElseThrow(SubscriptionNotFoundException::new);
        System.out.printf("*** Subscription by card number \"%s\" was started at %s", cardNumber, subscription.getStartDate());

        var allUsers = service.getAllUsers().stream().map(Application::getUserString).collect(Collectors.joining("\n", "*** - ", ""));
        System.out.println("*** The list of all subscribed users: \n" + allUsers);
    }

    private static String getUserString(UserDTO userDTO) {
        return userDTO.getName() + " " + userDTO.getName() + " " + userDTO.getBirthday();
    }

    private static String getSubscriptionString(SubscriptionDTO subscriptionDTO) {
        return subscriptionDTO.getBankcard() + " " + subscriptionDTO.getStartDate();
    }
}
