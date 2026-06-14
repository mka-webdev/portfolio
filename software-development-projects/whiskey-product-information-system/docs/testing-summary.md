# Whiskey Product Information System — Testing Summary

## Purpose

This document summarises the testing evidence connected to the Whiskey Product Information System.

The portfolio focus for this project is on testing the existing application rather than redesigning or rebuilding it. The application remains a university software development project, while the testing documentation explains how its behaviour can be checked against expected outcomes.

## Testing scope

The project includes JUnit 5 unit tests for selected application logic and validation classes.

The testing focuses on areas that can be checked independently from the full JavaFX interface and database setup, including:

- whiskey record navigation
- age-range validation
- region validation
- login field validation
- password-change field validation
- existing credential checks
- new password validation rules

## Classes under test

### WhiskeyDataManager

The `WhiskeyDataManager` tests focus on navigation behaviour through whiskey records.

Tested methods:

- `next()`
- `previous()`

Testing covered:

- empty record arrays
- one-record navigation
- two-record navigation
- multiple-record navigation
- null or empty record data
- previous and next behaviour across available records

### WhiskeyDataValidator

The `WhiskeyDataValidator` tests focus on input validation for whiskey product queries.

Tested methods:

- `checkAgeRange(String b1, String b2)`
- `checkRegion(String r)`

Testing covered:

- valid age ranges
- null age bounds
- empty age bounds
- non-numeric age input
- negative age values
- values outside the expected range
- boundary values
- upper bound lower than lower bound
- valid region names
- null region input
- empty region input
- non-alphabetic region input

### UserDataValidator

The `UserDataValidator` tests focus on login and password-change validation.

Tested methods:

- `checkForFieldsPresent(String n, String p)`
- `checkForFieldsPresent(String n, String oldp, String newp)`
- `checkCurrentDetails(UserDetails ud, String n, String oldp)`
- `checkNewDetails(UserDetails ud, String n, String oldp, String newp)`

Testing covered:

- login field presence
- password-change field presence
- null fields
- empty fields
- valid username and password checks
- non-alphabetic username input
- missing or corrupted user data
- non-existing usernames
- incorrect existing-user passwords
- new password strength rules
- password comparison rules
- invalid characters in new passwords

## Testing approach

This project is not being retrofitted into a full Software Testing Bootcamp-style artefact set.

Instead, it is being used as an existing application under test. The goal is to document practical testing evidence around the project as it exists.

The testing documentation may be expanded with:

- selected JUnit test result screenshots
- selected cleaned test files
- notes explaining important test cases
- application screenshots
- short notes about any small bug fixes or code corrections

## Evidence status

Current evidence:

- JUnit 5 unit tests exist in the project
- application launches successfully in NetBeans
- login screen has been confirmed to open
- testing areas have been identified and summarised

Evidence to add later:

- screenshot of the application login screen
- screenshot of product query screen
- screenshot of JUnit test results
- selected notes from cleaned test files
- database setup notes, if needed for review

## Portfolio note

This testing evidence supports my portfolio direction by showing how an existing Java application can be treated as a system under test.

The focus is not to make this project look like a new testing bootcamp exercise. The focus is to show existing unit testing, validation behaviour, and practical review evidence for a working JavaFX application.