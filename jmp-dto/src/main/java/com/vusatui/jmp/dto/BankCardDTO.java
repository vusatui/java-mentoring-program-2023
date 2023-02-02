package com.vusatui.jmp.dto;

public class BankCardDTO {

    private final String number;
    private final UserDTO user;

    public BankCardDTO(String number, UserDTO user) {
        this.number = number;
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public UserDTO getUser() {
        return user;
    }
}
