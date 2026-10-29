# Greeting Spring Boot API

A Spring Boot REST API that processes a query parameter, validates the input based on strict alphabetical constraints, and returns a tailored JSON response.

## Assumptions Made

1. **English Alphabet Scope:** The validation constraints (`a-m`) specifically assume the standard 26-letter English alphabet. Non-English accented characters (e.g., `á`, `ç`) or symbols are treated as invalid input.
2. **Whitespace Handling:** Leading and trailing whitespaces in the query parameter (e.g., `?name=  alice  `) are safely trimmed before processing to ensure the final greeting message doesn't contain unwanted padding.
3. **Missing vs. Empty Inputs:** An explicitly missing parameter (`?name=`), an empty parameter, or a completely absent parameter key are all treated uniformly as missing data, triggering a standard validation error.

---

## Prerequisites

Before running the application, make sure you have the following installed:
* **Java 21** (JDK)
* **Maven 3.x** (or use the included Maven wrapper `./mvnw`)

---

## How to Run the Application

You can start the Spring Boot application using your IDE or directly from your terminal using the Maven wrapper.

### Using the Terminal
Navigate to the project root directory and run the following command:

```bash
# On Linux/macOS
./mvnw spring-boot:run

# On Windows (Command Prompt)
mvnw.cmd spring-boot:run

# On Windows (PowerShell)
.\mvnw.cmd spring-boot:run
```

Once started, the application will be hosted locally at: `http://localhost:8082/hello-world`

### API Endpoint Usage Examples

* **Success Scenario (Name starts with A-M):**
    * **Request:** `GET http://localhost:8082/hello-world?name=alice`
    * **Response Status:** `200 OK`
    * **JSON Body:** `{ "message": "Hello Alice" }`

* **Failure Scenario (Name starts with N-Z / Invalid input):**
    * **Request:** `GET http://localhost:8082/hello-world?name=nancy`
    * **Response Status:** `400 Bad Request`
    * **JSON Body:** `{ "error": "Invalid Input" }`

---

## How to Run the Tests

The project includes an isolated unit testing layer for the business logic (`GreetingServiceTest`) and a mock web-environment controller suite (`GreetingControllerTest`).

To execute the entire test suite, run:

```bash
# On Linux/macOS
./mvnw test

# On Windows
./mvnw.cmd test
```

### Reviewing Test Reports
After running the tests, Maven will generate a summary directly in your console. For detailed reports, you can inspect the target files located inside the `target/surefire-reports/` directory.