package com.vusatui;

import java.util.concurrent.CompletableFuture;

import com.vusatui.controller.EmployeeController;

public class Main {

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        final CompletableFuture<Void> voidCompletableFuture = employeeController.getHiredEmployees()
                .thenAccept(employeeDTOS -> employeeDTOS.forEach(employeeDTO -> {
                    System.out.println(Thread.currentThread().getId());
                    System.out.printf("%s %s %s %s %n", employeeDTO.getId(), employeeDTO.getName(),
                            employeeDTO.getSurname(), employeeDTO.getSalary());
                }));
        System.out.println("Non blocked");
        voidCompletableFuture.join();
    }
}