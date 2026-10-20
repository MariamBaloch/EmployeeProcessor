# Employee Processor

A Spring Boot application that reads employee records from a CSV file and processes salary updates concurrently.

## Requirements

- Java 17 or later
- Maven 3.6 or later

## Build and run

Build the application:

```bash
mvn clean package
```

Start the web application:

```bash
mvn spring-boot:run
```

The server listens on port `8080`. Submit a request to start processing:

```bash
curl -X POST http://localhost:8080/employees/process
```

The endpoint processes the CSV records and returns a completion message. Detailed per-employee progress and salary
summaries are printed to the server terminal. Access swagger to run the API
directly [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

You can also run the packaged application:

```bash
java -jar target/EmployeeProcessor-1.0-SNAPSHOT.jar
```

## Employee data

The application reads `employees.csv` from its current working directory. The repository includes a sample file. Each
line must contain these comma-separated values, in order, with no header row:

```text
id,name,salary,joinDate,role,percentOfProjectCompleted
```

For example:

```text
1,Alice,52000.0,2019-05-12,Employee,0.8
```

Use an ISO date (`YYYY-MM-DD`) for `joinDate`. The role must be `Director`, `Manager`, or `Employee`, and project
completion is a decimal fraction from `0.0` to `1.0`.

## Salary increase logic

An employee receives an increase only if project completion is at least 60% and they have completed at least one full
year of service. The increase is calculated as:

```text
yearsOfService × roleIncreasePerYear
```

| Role     | Increase per year |
|----------|------------------:|
| Director |                5% |
| Manager  |                2% |
| Employee |                1% |

## Concurrency and output

Each employee is processed in a separate thread, with a semaphore limiting processing to five employees at a time.
Salary updates are calculated independently for each employee, and terminal log output is synchronized to keep each
message intact.

## Project structure

```text
src/main/java/com/ga/employeeprocessor/
├── EmployeeProcessorApplication.java
├── controller/
│   └── EmployeeController.java
├── enums/
│   └── Roles.java
├── model/
│   └── Employee.java
└── service/
    ├── EmployeeProcessor.java
    ├── EmployeeService.java
    └── SalaryUpdateTask.java
src/main/resources/
└── application.properties
employees.csv
```
