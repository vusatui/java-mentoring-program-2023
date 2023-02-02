package com.vusatui.jmp.service.api;

import java.util.List;
import java.util.Optional;

import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.SubscriptionDTO;
import com.vusatui.jmp.dto.UserDTO;

public interface Service {

    void subscribe(BankCardDTO bankCard);

    Optional<SubscriptionDTO> getSubscriptionByBankCardNumber(String number);

    List<UserDTO> getAllUsers();
}
