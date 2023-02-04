package com.vusatyi.jmp.cloud.bank.exception;

public class CardNotFoundException extends Exception {

    public CardNotFoundException(String cardNumber) {
        super("Card with number '" + cardNumber + "' not found");
    }
}
