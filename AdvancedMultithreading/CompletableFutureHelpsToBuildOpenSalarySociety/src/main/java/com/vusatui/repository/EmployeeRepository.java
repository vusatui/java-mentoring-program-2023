package com.vusatui.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.github.javafaker.*;

import com.vusatui.dto.EmployeeDTO;

public class EmployeeRepository {

    private static final Faker faker = new Faker();

    public CompletableFuture<Collection<EmployeeDTO>> hiredEmployees() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.printf("hiredEmployees - ");
            System.out.println(Thread.currentThread().getId());
            return List.of(
                    new EmployeeDTO(faker.idNumber().valid(), faker.name().firstName(), faker.name().lastName()),
                    new EmployeeDTO(faker.idNumber().valid(), faker.name().firstName(), faker.name().lastName()),
                    new EmployeeDTO(faker.idNumber().valid(), faker.name().firstName(), faker.name().lastName())
            );
        });
    }

    public CompletableFuture<BigDecimal> getSalary(String hiredEmployeeId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.printf("getSalary %s - ", hiredEmployeeId);
            System.out.println(Thread.currentThread().getId());
            return BigDecimal.valueOf(faker.number().numberBetween(100_000, 200_000));
        });
    }
}
