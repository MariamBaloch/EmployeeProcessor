package com.ga.employeeprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

public class EmployeeProcessor {

    public static List<Employee> getEmployeesFromFile(String fileName) {
        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .map(Employee::fromCsvLine)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + fileName, e);
        }
    }

    public static double getPercentIncrease(Employee employee) {
        if (employee.getPercentOfProjectCompleted() < 0.6) {
            return 0.0;
        }

        long yearsOfWorkCompleted = ChronoUnit.YEARS.between(employee.getJoinDate(), LocalDate.now());
        if (yearsOfWorkCompleted < 1) {
            return 0.0;
        }

        return yearsOfWorkCompleted * employee.getRole().getPercentageIncrease();
    }
}

