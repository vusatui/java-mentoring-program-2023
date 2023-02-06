package com.vusatui.jmt.bank.api;

import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.UserDTO;
import com.vusatui.jmt.bank.api.exception.CardNotFoundException;

public interface Bank {

    BankCardDTO createBankCard(UserDTO user, BankCardConsumer bankCardConsumer);

    BankCardDTO getCardByNumber(String number) throws CardNotFoundException;

    @FunctionalInterface
    interface BankCardConsumer {
        BankCardDTO get(String number, UserDTO user);
    }
}
