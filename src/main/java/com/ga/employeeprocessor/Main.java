package com.ga.employeeprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Employee> employees = EmployeeProcessor.getEmployeesFromFile("employees.csv");

        Semaphore semaphore = new Semaphore(5);
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
    }
}

