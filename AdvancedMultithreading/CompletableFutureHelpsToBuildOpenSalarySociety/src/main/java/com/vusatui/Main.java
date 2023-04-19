package com.vusatui;

import java.util.concurrent.CompletableFuture;

import com.vusatui.controller.EmployeeController;

public class Main {

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        CompletableFuture.supplyAsync(() -> employeeController.getHiredEmployees()
                .thenAccept(employeeDTOS -> employeeDTOS.forEach(employeeDTO -> {
                    System.out.println(Thread.currentThread().getId());
                    System.out.printf("%s %s %s %s %n", employeeDTO.getId(), employeeDTO.getName(),
                            employeeDTO.getSurname(), employeeDTO.getSalary());
                })));
        System.out.println("Non blocked");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}