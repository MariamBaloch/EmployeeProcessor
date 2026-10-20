package com.ga.employeeprocessor.controller;

import com.ga.employeeprocessor.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/process")
    public String processEmployees() {
        try {
            employeeService.processEmployees();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Employee processing completed, Please check the terminal for the output";
    }
}