This is a Spring Boot web app that implements zero-based budgeting.

Zero-based budgeting is where every dollar of income is assigned a specific purpose until the assigned balance is
exactly zero.

The core idea is ``Total Income - Total ALlocated = $0.00``. This entire project, and the budgeting methodology itself.
is built around that equation.

## Software Used

- The backend is Java 25, with Spring Boot 4.
- The frontend is Jakarta Server Pages and JSTL.

## Requirements

* Java 25 or later.
* Maven 3.9+

## Getting started

### Running the server

1. Clone the repository.
2. Open the project in your IDE or terminal.
3. Run the application with ``./mvnw spring-boot:run``
4. The server should be accessible from ``https://localhost:8080``

### Using the software

1. Register or log into an account.
2. Add your expected monthly sources of income.
3. Add your expected monthly expenses.
4. Balance income and expenses until the budget reaches 0.
