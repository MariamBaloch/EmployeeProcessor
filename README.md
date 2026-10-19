# Employee Processor

A Java application that processes employee salary updates concurrently using multi-threading.

## Features

- Reads employee data from CSV files
- Calculates salary increases based on role, tenure, and project completion
- Processes employees concurrently with semaphore-based thread limiting
- Thread-safe output logging

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Building

```bash
mvn clean compile
```

## Running

After building the project, run the application using the compiled classes:

```bash
mvn clean compile
java -cp target/classes com.ga.employeeprocessor.Main
```

## Salary Increase Logic

An employee is eligible for a salary increase if:
- Their project completion is at least 60%
- They have completed at least 1 year of service

The increase percentage is calculated as:
```
yearsOfService × rolePercentageIncrease
```

Role-based increase rates:
- Director: 5% per year
- Manager: 2% per year
- Employee: 1% per year

## Project Structure

```
src/main/java/com/ga/employeeprocessor/
├── Main.java              # Application entry point
├── Employee.java          # Employee data model
├── EmployeeProcessor.java # CSV reading and salary calculation
├── SalaryUpdateTask.java  # Concurrent salary update task
└── Roles.java             # Employee role enum
```

