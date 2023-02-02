package com.vusatui.jmp.cloud.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.SubscriptionDTO;
import com.vusatui.jmp.dto.UserDTO;
import com.vusatui.jmp.service.api.Service;

public class ServiceImpl implements Service {

    private final List<UserDTO> userDTOS = new ArrayList<>();

    @Override
    public void subscribe(BankCardDTO bankCard) {
        var userDTOFromBankCard = bankCard.getUser();
        var filteredUser = userDTOS.stream().filter(
                userDTO -> userDTO.equals(userDTOFromBankCard)).findFirst();
        if (filteredUser.isEmpty()) userDTOS.add(userDTOFromBankCard);
    }

    @Override
    public Optional<SubscriptionDTO> getSubscriptionByBankCardNumber(String number) {
        return Optional.empty();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDTOS;
    }
}
