# Whiskey Product Information System — Project Summary

## Overview

The Whiskey Product Information System is a Java desktop application developed for the Software Design and Development unit. The application provides product information about single malt whiskies and allows users to view, search, and navigate whiskey product records.

The project is included in this portfolio as a runnable university software development project and as an existing application that can support testing evidence.

## Project context

- **Unit:** Software Design and Development
- **Project type:** Individual project
- **Application type:** Java desktop application
- **Project folder:** `WIS`
- **Original development environment:** NetBeans
- **Build system:** Maven

## Application purpose

The purpose of the application is to provide users with access to whiskey product information stored in a database. Users can log in, change the default password when required, and then query whiskey records by viewing all products, searching by region, or searching by age range.

## Main features

### Login and password management

The application includes a login screen where users enter a username and password. Users with the default password are required to change it before accessing the whiskey product database.

Password-change behaviour includes validation for:

- required fields
- existing username and password
- new password strength
- password reuse
- invalid characters

### Whiskey product queries

After login and required password change, users can access the whiskey product query screen.

The application supports:

- displaying all whiskey records
- searching by whiskey region
- searching by age range
- navigating through query results using previous and next buttons

### Input validation

The application validates user input for:

- login fields
- password-change fields
- region searches
- age-range searches

Validation is used to prevent empty, invalid, or unsupported input from being processed by the application.

## Technologies and tools

- Java
- JavaFX
- FXML
- Maven
- MySQL
- JDBC
- JUnit 5
- NetBeans

## Architecture

The project followed a design specification provided for the assessment. The application was implemented using a simple three-layer structure:

- **Presentation layer:** JavaFX screens and controllers
- **Application layer:** data manager and validation classes
- **Data access layer:** JDBC-based database access classes

The implementation separates the interface, application logic, validation, and database access responsibilities.

## Database

The application uses MySQL databases for whiskey product data and user login data.

The whiskey product data includes fields such as:

- distillery
- age
- region
- price

The user data supports login and password-change behaviour.

## Running the project

This is a Maven-based JavaFX project. It was originally developed and tested in NetBeans, but it can be opened in any Java IDE with Maven support.

To run the project:

1. Open the `WIS` folder in a Java IDE with Maven support.
2. Allow the IDE to import Maven dependencies from `pom.xml`.
3. Configure the required MySQL database connection.
4. Run the JavaFX application from the IDE.

The project has been confirmed to launch successfully in NetBeans, opening to the Whiskey Information System login screen.

## Portfolio note

This project remains close to its original university project state. The surrounding portfolio documentation is being cleaned and expanded to show how the application works, how it can be reviewed, and how it can support practical testing evidence.