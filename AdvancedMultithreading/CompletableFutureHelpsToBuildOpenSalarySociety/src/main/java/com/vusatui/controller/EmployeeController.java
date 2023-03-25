package com.vusatui.controller;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import com.vusatui.dto.EmployeeDTO;
import com.vusatui.repository.EmployeeRepository;

public class EmployeeController {

    private static final EmployeeRepository employeeRepository = new EmployeeRepository();

    public CompletableFuture<Collection<EmployeeDTO>> getHiredEmployees() {
        return CompletableFuture.supplyAsync(() -> employeeRepository.hiredEmployees()
                .thenCompose(employeeDTOS -> {
                    final List<CompletableFuture<Void>> completableFutures = employeeDTOS.stream().map(
                            employeeDTO -> employeeRepository.getSalary(employeeDTO.getId())
                                    .thenAccept(salary -> {
                                        System.out.println(employeeDTO.getId());
                                        System.out.println(Thread.currentThread().getId());
                                        try {
                                            Thread.sleep(2000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        employeeDTO.setSalary(salary);
                                    })
                    ).toList();
                    final CompletableFuture<Void> futures = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
                    return CompletableFuture.supplyAsync(() -> {
                        futures.join();
                        return employeeDTOS;
                    });
                }).join());
    }
}
