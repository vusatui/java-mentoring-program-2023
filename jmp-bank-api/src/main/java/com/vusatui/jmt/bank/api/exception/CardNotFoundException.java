package com.vusatui.jmt.bank.api.exception;

public class CardNotFoundException extends Exception {

    public CardNotFoundException(String cardNumber) {
        super("Card with number '" + cardNumber + "' not found");
    }
}
