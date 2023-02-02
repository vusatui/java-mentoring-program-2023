package com.vusatui.jmp.dto;

import java.time.LocalDate;

public class SubscriptionDTO {

    private final String bankcard;
    private final LocalDate startDate;

    public SubscriptionDTO(String bankcard, LocalDate startDate) {
        this.bankcard = bankcard;
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getBankcard() {
        return bankcard;
    }
}
