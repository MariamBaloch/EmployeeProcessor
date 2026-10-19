package com.ga.employeeprocessor;

import lombok.Getter;

@Getter
public enum Roles {
    Director(0.05),
    Manager(0.02),
    Employee(0.01);

    private final double percentageIncrease;

    Roles(double percentage) {
        this.percentageIncrease = percentage;
    }
}

