package com.vusatui.jmt.bank.api;

import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.UserDTO;

public interface Bank {

    BankCardDTO createBankCard(UserDTO user, BankCardConsumer bankCardConsumer);

    @FunctionalInterface
    interface BankCardConsumer {
        BankCardDTO get(String number, UserDTO user);
    }
}
