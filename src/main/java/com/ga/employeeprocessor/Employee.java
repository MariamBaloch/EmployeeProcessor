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
}

