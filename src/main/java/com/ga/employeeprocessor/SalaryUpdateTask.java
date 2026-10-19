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
        boolean acquired = false;
        String threadName = Thread.currentThread().getName();

        try {
            semaphore.acquire();
            acquired = true;

            synchronized (System.out) {
                System.out.printf(
                        "[%s] Processing employee: %s (%s)%n",
                        threadName,
                        employee.getName(),
                        employee.getRole()
                );
            }

            Thread.sleep(500 + (long) (Math.random() * 2000));

            double oldSalary = employee.getSalary();
            double increasePercent =
                    EmployeeProcessor.getPercentIncrease(employee);
            double newSalary = oldSalary * (1 + increasePercent);

            employee.setSalary(newSalary);

            synchronized (System.out) {
                System.out.printf(
                        "[%s] Finished: %s | Salary: %.2f -> %.2f " +
                                "(Increase: %.1f%%)%n",
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
            if (acquired) {
                semaphore.release();
            }
        }
    }
}