package com.vusatui.jmp.dto;

import java.time.LocalDate;

public class UserDTO {

    private final String name;
    private final String surname;
    private final LocalDate birthday;

    public UserDTO(String name, String surname, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
