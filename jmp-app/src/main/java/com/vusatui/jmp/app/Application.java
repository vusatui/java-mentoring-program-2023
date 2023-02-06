package com.vusatui.jmp.app;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.vusatui.jmp.cloud.service.impl.exception.SubscriptionNotFoundException;
import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.CreditCardDTO;
import com.vusatui.jmp.dto.SubscriptionDTO;
import com.vusatui.jmp.dto.UserDTO;
import com.vusatui.jmp.service.api.Service;
import com.vusatui.jmt.bank.api.Bank;
import com.vusatui.jmt.bank.api.exception.CardNotFoundException;

public class Application {

    static final Collector<CharSequence, ?, String> listCollector = Collectors.joining("\n", "*** - ", "");

    public static void main(String[] args) throws SubscriptionNotFoundException, CardNotFoundException {
        System.out.println("*** Start application ***");

        Optional<Bank> optionalBank = ServiceLoader.load(Bank.class).findFirst();
        Optional<Service> optionalService = ServiceLoader.load(Service.class).findFirst();

        Bank bank = optionalBank.orElseThrow();
        Service service = optionalService.orElseThrow();

        System.out.println("*** All required services was loaded ***");

        System.out.println("*** Creating a user ***");
        var user1 = new UserDTO("Foo", "Bar", LocalDate.now().minus(Period.of(18, 0, 0)));

        System.out.println("*** Creating bank card ***");
        BankCardDTO bankCardDTO = bank.createBankCard(user1, CreditCardDTO::new);

        System.out.println("*** Making a subscription ***");
        service.subscribe(bankCardDTO);

        System.out.println("*** All subscriptions before now: ***");
        var allSubscriptions = service.getAllSubscriptionsByCondition(subscriptionDTO -> subscriptionDTO.getStartDate()
                .equals(LocalDate.now())).stream().map(Application::getSubscriptionString)
                .collect(listCollector);
        System.out.println("*** The list of all subscriptions: %n" + allSubscriptions);

        System.out.printf("*** Average users age is: %s years. %n", service.getAverageUsersAge());

        var cardNumber = bankCardDTO.getNumber();
        System.out.printf("*** The owner of card %s: %s %n", cardNumber, getUserString(bank.getCardByNumber(cardNumber).getUser()));

        var subscription = service.getSubscriptionByBankCardNumber(cardNumber).orElseThrow(SubscriptionNotFoundException::new);
        System.out.printf("*** Subscription by card number \"%s\" was started at %s %n", cardNumber, subscription.getStartDate());

        var allUsers = service.getAllUsers().stream().map(Application::getUserString).collect(listCollector);
        System.out.println("*** The list of all subscribed users: \n" + allUsers);
    }

    private static String getUserString(UserDTO userDTO) {
        return userDTO.getName() + " " + userDTO.getName() + " " + userDTO.getBirthday();
    }

    private static String getSubscriptionString(SubscriptionDTO subscriptionDTO) {
        return subscriptionDTO.getBankcard() + " " + subscriptionDTO.getStartDate();
    }
}
