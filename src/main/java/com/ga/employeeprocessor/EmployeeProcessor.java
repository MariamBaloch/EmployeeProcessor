package com.ga.employeeprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
}

