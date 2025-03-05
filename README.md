# SauceDemo Test Automation Framework

This project provides an automated test framework for the [SauceDemo](https://www.saucedemo.com/) e-commerce website. This project is hosted on [GitHub](https://github.com/TapanKu/ui-test-automation).
It's built using Java, Selenium WebDriver, Cucumber, JUnit, and Maven.

## Features

*   **Test Automation:** Automates various test scenarios on the SauceDemo website, such as:
    *   Adding items to the cart.
    *   Verifying items in the cart.
    *   Verifying that items are not in the cart.
    *   Verifying the "Add to Cart" button changes to "Remove".
    *   Completing the checkout process.
    *   Verifying the order confirmation message.
    *   Logout.
*   **Cucumber BDD:** Uses Cucumber for Behavior-Driven Development (BDD), enabling tests to be written in a human-readable format (Gherkin).
*   **Selenium WebDriver:** Leverages Selenium for web browser automation.
*   **JUnit 5:** Employs JUnit 5 for test execution.
*   **Page Object Model (POM):** Follows the Page Object Model design pattern to create a clean and maintainable structure.
*   **Configuration File:** Uses `config.properties` to manage environment-specific settings (e.g., browser type, URL).
*   **Slf4j:** Used for logging.
*   **Lombok:** Used to reduce boilerplate code.
*   **Commons io:** Used for utility classes.
*   **SnakeYaml:** Used to read yml files.

## Prerequisites

*   **Java:** Make sure you have Java 17 or higher installed on your system.
*   **Maven:** The project uses Maven for dependency management and building.
*   **Web Browser:** Chrome and Firefox are supported.

## Getting Started

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/TapanKu/saucedemo-test-automation.git
    ```

2.  **Navigate to the project directory:**
    ```bash
    cd saucedemo-test-automation
    ```

3.  **Run the tests using Maven:**
    ```bash
    mvn clean test
    ```
    *   `mvn clean`: Cleans the target directory.
    *   `mvn test`: Executes the tests and generates reports.

## Configuration

1.  **`config.properties`:**
    *   This file is located in the project's root directory.
    *   Modify the `browser` property to select the browser you want to use (`chrome` or `firefox`).
    *   Modify the `url` property to select the application url to use.
    
## Chrome Options Configuration
*   The `chrome_options.yml` file, located in `src/main/resources/`, allows you to customize Chrome browser options.
*   **Headless Mode:**
*   To run tests in headless mode (without a visible browser window), ensure that the `--headless` argument is present in the `chrome_options.yml` file:
```yaml
   - "--headless"
    ```
* To run with visible browser, remove this argument from `chrome_options.yml`.
## Running Tests with Tags

You can run specific scenarios or features based on tags. Here are some examples:

*   **Run all tests:**
    ```bash
    mvn clean test
    ```

*   **Run smoke tests:**
    ```bash
    mvn clean test -Dcucumber.filter.tags="@smoke"
    ```

*   **Run regression tests:**
    ```bash
    mvn clean test -Dcucumber.filter.tags="@regression"
    ```

*   **Run tests with multiple tags (AND):**
    ```bash
    mvn clean test -Dcucumber.filter.tags="@smoke and @test_01"
    ```
*   **Run tests with multiple tags (OR):**
    ```bash
    mvn clean test -Dcucumber.filter.tags="@smoke or @regression"
    ```
* **Run test and avoid some test:**
    ```bash
     mvn clean test -Dcucumber.filter.tags="not @regression"
    ```
* **Run scenario outline:**

## Cucumber Reports

Cucumber will generate reports in different formats automatically. You can use
these commands.

* **HTML Report:** An HTML report will be generated under
  `target/cucumber-reports/cucumber-reports.html`.
* **JSON Report:** A JSON report will be generated under
  `target/cucumber-reports/cucumber-reports.json`.
* **JUNIT Report:** A XML report will be generated under
  `target/cucumber-reports/cucumber-reports.xml`.
* **Surefire Report**: The surefire plugin will generate a report in html
  format under `target/surefire-reports` directory.

## Project Structure

    saucedemo-test-automation/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── org.mytest/
    │   │   │       └── pages/      # Page Object classes (HomePage)
    │   │   │       └── helperUtils/      # Helper classes 
    │   │   └── resources/
    │   │       └── chrome_options.yml    # Chrome browser options configuration
    │   └── test/
    │       ├── java/
    │       │   └── org.mytest/
    │       │       ├── stepdefinition/         # Cucumber Step Definition classes
    │       │       ├── RunCucumberTest.java    # Cucumber Run file
    │       │       |── Hooks/                  # Cucumber Hooks file
    │       └── resources/
    │           └── features/            # Cucumber feature files (Gherkin)
    │               └── SauceDemo.feature  # Example Feature file
    │
    ├── config.properties               # Configuration settings    
    ├── pom.xml                          # Maven project configuration
    └── README.md                       # Documentation

## Dependencies

* **Selenium WebDriver:** For web automation.
* **Cucumber:** For BDD testing.
* **JUnit 5:** For test execution.
* **Maven:** For project management and dependency handling.
* **Slf4j:** For logging.
* **Lombok:** For reduce boilerplate code.
* **Commons io:** For util classes.
* **SnakeYaml:** For read yml files.

## Authors

* Tapan Sahoo
