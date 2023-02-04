package com.vusatui.jmp.cloud.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.SubscriptionDTO;
import com.vusatui.jmp.dto.UserDTO;
import com.vusatui.jmp.service.api.Service;

public class ServiceImpl implements Service {

    private final Map<UserDTO, SubscriptionDTO> subscriptions = new HashMap<>();

    @Override
    public void subscribe(BankCardDTO bankCard) {
        subscriptions.computeIfAbsent(bankCard.getUser(), userDTO -> new SubscriptionDTO(bankCard.getNumber(), LocalDate.now()));
    }

    @Override
    public Optional<SubscriptionDTO> getSubscriptionByBankCardNumber(String number) {
        return subscriptions.values().stream().filter(subscriptionDTO -> subscriptionDTO.getBankcard().equals(number)).findFirst();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return subscriptions.keySet().stream().collect(Collectors.toUnmodifiableList());
    }
}
