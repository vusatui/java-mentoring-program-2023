package com.vusatyi.jmp.cloud.bank.impl;


import java.util.HashMap;
import java.util.UUID;

import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.UserDTO;
import com.vusatui.jmt.bank.api.Bank;
import com.vusatyi.jmp.cloud.bank.exception.CardNotFoundException;

public class BankImpl implements Bank {

    private final HashMap<String, BankCardDTO> bankCardDTOs = new HashMap<>();

    @Override
    public BankCardDTO createBankCard(UserDTO user, BankCardConsumer bankCardConsumer) {
        var uuid = UUID.randomUUID().toString();
        var bankCard = bankCardConsumer.get(uuid, user);
        registerCard(bankCard);
        return bankCard;
    }

    public BankCardDTO getCardByNumber(String number) throws CardNotFoundException {
        if (bankCardDTOs.containsKey(number)) {
            return bankCardDTOs.get(number);
        } else {
            throw new CardNotFoundException("Card with number '" + number + "' not found");
        }
    }

    private void registerCard(BankCardDTO bankCardDTO) {
        bankCardDTOs.put(bankCardDTO.getNumber(), bankCardDTO);
    }
}
