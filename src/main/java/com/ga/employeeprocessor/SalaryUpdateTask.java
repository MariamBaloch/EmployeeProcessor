package com.ga.employeeprocessor;

import java.util.concurrent.Semaphore;

public class SalaryUpdateTask implements Runnable {
    private final Employee employee;
    private final Semaphore semaphore;

    public SalaryUpdateTask(Employee employee, Semaphore semaphore) {
        this.employee = employee;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();

            String threadName = Thread.currentThread().getName();

            synchronized (System.out) {
                System.out.printf(
                        "[%s] Processing employee: %s (%s)%n",
                        threadName,
                        employee.getName(),
                        employee.getRole()
                );
            }

            double oldSalary = employee.getSalary();
            double increasePercent = EmployeeProcessor.getPercentIncrease(employee);
            double newSalary = EmployeeProcessor.calculateNewSalary(employee);

            employee.setSalary(newSalary);

            synchronized (System.out) {
                System.out.printf(
                        "[%s] Finished: %s | Salary: %.2f -> %.2f (Increase: %.1f%%)%n",
                        threadName,
                        employee.getName(),
                        oldSalary,
                        newSalary,
                        increasePercent * 100
                );
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }
}

