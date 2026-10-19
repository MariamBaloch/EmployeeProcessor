package com.ga.employeeprocessor;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private Long id;
    private String name;
    private double salary;
    private LocalDate joinDate;
    private Roles role;
    private double percentOfProjectCompleted;

    public static Employee fromCsvLine(String line) {
        String[] parts = line.split(",");
        return new Employee(
                Long.parseLong(parts[0].trim()),
                parts[1].trim(),
                Double.parseDouble(parts[2].trim()),
                LocalDate.parse(parts[3].trim()),
                Roles.valueOf(parts[4].trim()),
                Double.parseDouble(parts[5].trim())
        );
    }
}

