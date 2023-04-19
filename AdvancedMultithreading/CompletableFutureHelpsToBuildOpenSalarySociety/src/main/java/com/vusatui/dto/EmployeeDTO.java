package com.vusatui.dto;

import java.math.BigDecimal;

public class EmployeeDTO {

    private final String id;

    private final String name;

    private final String surname;

    private BigDecimal salary;

    public EmployeeDTO(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }
}
