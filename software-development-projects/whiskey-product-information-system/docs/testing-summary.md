# Whiskey Product Information System — Testing Evidence

## Purpose

This folder documents testing evidence for the Whiskey Product Information System.

The focus is on showing how the existing JavaFX application can be treated as a system under test. The project already includes JUnit 5 tests for selected application logic and validation classes, and this documentation summarises the areas covered by those tests.

## Evidence

- [JUnit test results screenshot](./junit-tests-passed.png)

The screenshot shows the NetBeans Test Results panel with passing JUnit tests.

## Test files

The project includes three JUnit 5 test files:

- `WhiskeyDataManagerTest.java`
- `WhiskeyDataValidatorTest.java`
- `UserDataValidatorTest.java`

## WhiskeyDataManagerTest

This test file covers navigation behaviour for whiskey records.

### Methods tested

- `next()`
- `previous()`

### Test coverage

- previous navigation when no records are available
- next navigation when no records are available
- previous navigation with two records
- next navigation with two records
- previous navigation when the data array is null
- next navigation when the data array is null
- previous navigation with one record
- previous navigation across multiple records
- next navigation across multiple records
- next navigation with one record

## WhiskeyDataValidatorTest

This test file covers validation logic for whiskey product queries.

### Methods tested

- `checkAgeRange(String b1, String b2)`
- `checkRegion(String r)`

### Age-range validation coverage

- valid age range
- null lower bound
- null upper bound
- empty lower bound
- empty upper bound
- non-numeric lower bound
- non-numeric upper bound
- negative lower bound
- negative upper bound
- lower bound above accepted range
- upper bound above accepted range
- upper bound lower than lower bound
- boundary values

### Region validation coverage

- valid region name
- null region input
- empty region input
- non-alphabetic region input

## UserDataValidatorTest

This test file covers validation logic for login and password-change behaviour.

### Methods tested

- `checkForFieldsPresent(String n, String p)`
- `checkForFieldsPresent(String n, String oldp, String newp)`
- `checkCurrentDetails(UserDetails ud, String n, String oldp)`
- `checkNewDetails(UserDetails ud, String n, String oldp, String newp)`

### Login field validation coverage

- username and password both present
- null username
- null password
- empty username
- empty password
- non-alphabetic username

### Change-password field validation coverage

- username, old password, and new password all present
- null username
- null old password
- null new password
- empty username
- empty old password
- empty new password
- non-alphabetic username

### Current credential validation coverage

- valid current username and password
- missing or corrupted user data
- non-existing username
- incorrect current password

### New password validation coverage

- valid new password
- new password matching old password
- new password too short
- new password without an uppercase letter
- new password without a lowercase letter
- new password without a digit
- new password containing invalid characters

## Testing approach

This project is not being retrofitted into a full Software Testing Bootcamp-style artefact set. Instead, it is being documented as an existing university application under test.

The testing evidence shows:

- existing JUnit 5 unit tests
- validation behaviour
- expected and actual outcomes
- record navigation behaviour
- password and login validation rules

## Portfolio note

This evidence supports my portfolio direction by showing existing Java unit testing experience on a working JavaFX application.

The goal is not to overstate the project. The goal is to show that the application can be run, reviewed, and tested with documented evidence.