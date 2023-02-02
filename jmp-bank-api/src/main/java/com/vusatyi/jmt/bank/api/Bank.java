package com.vusatyi.jmt.bank.api;

import com.vusatui.jmp.dto.BankCardDTO;
import com.vusatui.jmp.dto.BankCardType;
import com.vusatui.jmp.dto.UserDTO;

public interface Bank {

    BankCardDTO createBankCard(UserDTO user, BankCardType bankCardType);
}
