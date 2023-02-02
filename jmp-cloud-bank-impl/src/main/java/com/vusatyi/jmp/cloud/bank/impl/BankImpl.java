package com.vusatyi.jmp.cloud.bank.impl;


import static com.vusatui.jmp.dto.BankCardType.CREDIT;

import java.util.HashMap;
import java.util.UUID;

import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.BankCardType;
import com.vusatui.jmp.dto.CreditCardDTO;
import com.vusatui.jmp.dto.DebitCardDTO;
import com.vusatui.jmp.dto.UserDTO;
import com.vusatui.jmt.bank.api.Bank;

public class BankImpl implements Bank {

    private final HashMap<String, BankCardDTO> bankCardDTOs = new HashMap<>();

    @Override
    public BankCardDTO createBankCard(UserDTO user, BankCardType bankCardType) {
        var uuid = UUID.randomUUID().toString();
        var bankCard = bankCardType.equals(CREDIT)
                ? new CreditCardDTO(uuid, user)
                : new DebitCardDTO(uuid, user);
        registerCard(bankCard);
        return bankCard;
    }

    public BankCardDTO getCardByNumber(String number) throws Exception {
        if (bankCardDTOs.containsKey(number)) {
            return bankCardDTOs.get(number);
        } else {
            throw new Exception("Card with number '" + number + "' not found");
        }
    }

    private void registerCard(BankCardDTO bankCardDTO) {
        bankCardDTOs.put(bankCardDTO.getNumber(), bankCardDTO);
    }
}
