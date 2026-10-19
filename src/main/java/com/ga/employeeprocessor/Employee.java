package com.ga.employeeprocessor;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private double salary;
    private LocalDate joinDate;
    private Roles role;
}

