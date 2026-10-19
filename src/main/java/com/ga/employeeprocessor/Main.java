package com.ga.employeeprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    private static final int MAX_CONNECTIONS = 5;

    public static void main(String[] args) throws InterruptedException {
        List<Employee> employees = EmployeeProcessor.getEmployeesFromFile("employees.csv");

        System.out.println("\n=== Original Salary Summary ===");
        employees.forEach(employee ->
                System.out.printf("  %s: %.2f%n",
                        employee.getName(), employee.getSalary())
        );
        System.out.println("===============================");

        System.out.printf("Starting salary update process for %d employees...%n", employees.size());

        Semaphore semaphore = new Semaphore(MAX_CONNECTIONS);
        List<Thread> threads = new ArrayList<>();

        for (Employee employee : employees) {
            threads.add(new Thread(new SalaryUpdateTask(employee, semaphore)));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\n=== Updated Salary Summary ===");
        employees.forEach(employee ->
                System.out.printf("  %s: %.2f%n",
                        employee.getName(), employee.getSalary())
        );
        System.out.println("==============================");
    }
}