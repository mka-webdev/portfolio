# Whiskey Product Information System

## Overview

The Whiskey Product Information System is a Java desktop application developed for the Software Design and Development unit. The application provides product information about single malt whiskies, including product listings, region-based searches, age-range queries, and navigation through query results.

This project is included in my portfolio as both a runnable software development project and an existing application that can support testing evidence. The application remains close to its original university project state, while the surrounding documentation is being cleaned and expanded for portfolio use.

## Project context

- **Unit:** Software Design and Development
- **Project type:** Individual project
- **Application type:** Java desktop application
- **Project folder:** `WIS`
- **Portfolio status:** Runnable project with testing evidence being documented

## Technologies and tools

- Java
- JavaFX
- FXML
- Maven
- MySQL
- JDBC
- JUnit 5
- NetBeans

## Main features

- Login screen with password validation
- Required default password change before product access
- Product query screen for whiskey information
- Display of all whiskey products
- Search by whiskey region
- Search by age range
- Previous and next navigation through query results
- Validation for login, password change, region search, and age-range search

## Running the project

This is a Maven-based JavaFX project. It was originally developed and tested in NetBeans, but it can be opened in any Java IDE with Maven support, such as IntelliJ IDEA, Eclipse, VS Code, or NetBeans.

To run the project:

1. Clone or download the repository.
2. Open the `WIS` folder in a Java IDE with Maven support.
3. Allow the IDE to import Maven dependencies from `pom.xml`.
4. Configure the required MySQL database connection.
5. Run the JavaFX application from the IDE.

The project has been confirmed to launch successfully in NetBeans, opening to the Whiskey Information System login screen.

## Testing focus

My portfolio focus for this project is on testing the existing application rather than redesigning or rebuilding it.

The project already includes JUnit 5 unit tests for selected application logic and validation classes. The testing evidence is being documented to show how the application behaviour can be checked against expected outcomes.

Current testing areas include:

- whiskey data navigation using previous and next behaviour
- age-range validation
- region validation
- login field validation
- change-password field validation
- current credential checks
- new password validation rules

## Documentation

Additional documentation is being separated into smaller files:

- [Project summary](./docs/project-summary.md)
- [Testing summary](./docs/testing-summary.md)

## Portfolio note

This project is included to show Java implementation work, JavaFX interface development, database interaction, validation logic, and basic unit testing evidence based on a provided assessment design specification.

The application remains a university project, but the portfolio documentation focuses on how it can be tested, explained, and reviewed as a working software artefact.

For portfolio review, screenshots and testing notes provide quick evidence of the application behaviour without requiring the reviewer to configure the full local environment.