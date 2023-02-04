package com.vusatui.jmp.service.api;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.SubscriptionDTO;
import com.vusatui.jmp.dto.UserDTO;

public interface Service {

    int LEGAL_AGE = 18;

    void subscribe(BankCardDTO bankCard);

    Optional<SubscriptionDTO> getSubscriptionByBankCardNumber(String number);

    List<UserDTO> getAllUsers();

    default double getAverageUsersAge() {
        var now = LocalDate.now();
        var allUsers = getAllUsers();
        var usersAgeSum = (double) allUsers.stream().mapToLong(userDTO -> ChronoUnit.YEARS.between(userDTO.getBirthday(), now)).sum();
        return (usersAgeSum / allUsers.size());
    }

    static boolean isPayableUser(UserDTO userDTO) {
       return ChronoUnit.YEARS.between(userDTO.getBirthday(), LocalDate.now()) >= LEGAL_AGE;
    }
}
