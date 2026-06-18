# Testing Summary

This document summarises the testing process and testing evidence for the Whiskey Product Information System university project.

The project was developed as a JavaFX desktop application and includes JUnit 5 tests for selected application logic and validation classes. The testing evidence focuses on unit-level validation of existing behaviour rather than presenting the project as a complete modern testing case study.

This project is kept close to its original university submission state. The testing summary documents what was realistically tested, what evidence exists, and which testing levels were not formally covered.

---

## Testing Levels

| Testing Level | How it applied in this project | Evidence / Artefacts | Notes |
| --- | --- | --- | --- |
| Unit Testing | Selected application logic and validation methods were tested using JUnit 5. The tests focused on record navigation, whiskey search validation, login validation, password-change validation, and credential checking. | `WhiskeyDataManagerTest.java`, `WhiskeyDataValidatorTest.java`, `UserDataValidatorTest.java` | This is the strongest formal testing evidence for this project. |
| Component Integration Testing | Limited informal component-level testing occurred during development by running the JavaFX application and checking that classes worked together as expected. | Manual development checks while implementing and running the application. | This was not documented as a formal integration test suite. |
| System Testing | The completed desktop application was manually checked through normal user workflows, such as searching whiskey records, navigating product data, logging in, and changing passwords. | Manual testing of the running JavaFX application. | This was performed as practical validation of the working application, not as automated E2E testing. |
| System Integration Testing | Not formally applicable to this project. The application did not involve major external systems or separate deployed services requiring dedicated system integration testing. | Not applicable / no separate evidence. | Database/file/data interaction may have been part of application behaviour, but this was not treated as a separate formal system integration testing phase. |
| Acceptance Testing / UAT | The project was assessed against the university project brief, design expectations, and required application behaviour. Acceptance criteria were provided through the assignment/project requirements rather than written as a separate UAT artefact by me. | University requirements, design expectations, completed application behaviour, and final submission. | No separate formal UAT document is included in this portfolio version. |

---

## Test Types Used

The project included several test types, although not all were formalised as separate testing phases.

| Test Type | How it appeared in this project |
| --- | --- |
| Automated Testing | Automated JUnit 5 tests were written for selected validation and navigation logic. |
| Manual Testing | The JavaFX application was manually checked during development and before submission. |
| Functional Testing | Application behaviours were checked against expected outcomes, including whiskey record navigation, product query validation, login validation, password-change validation, and credential checking. |
| Regression Testing | Regression checking was mainly supported by re-running the JUnit 5 tests after changes. This helped confirm that existing tested behaviours still passed. |
| Black-Box Testing | Some validation tests were based on inputs and expected outputs, such as checking invalid age ranges, empty fields, invalid usernames, and password-rule failures. |
| White-Box Testing | Some tests were written with knowledge of the application classes and methods, such as directly testing validator and data manager methods. |

---

## Automated Test Suite

The project includes three JUnit 5 test files:

1. `WhiskeyDataManagerTest.java`
2. `WhiskeyDataValidatorTest.java`
3. `UserDataValidatorTest.java`

These tests cover selected application logic and validation behaviour.

---

## WhiskeyDataManagerTest

This test file covers navigation behaviour for whiskey records.

### Methods Tested

1. `next()`
2. `previous()`

### Coverage Areas

1. Previous navigation when no records are available.
2. Next navigation when no records are available.
3. Previous navigation with two records.
4. Next navigation with two records.
5. Previous navigation when the data array is null.
6. Next navigation when the data array is null.
7. Previous navigation with one record.
8. Previous navigation across multiple records.
9. Next navigation across multiple records.
10. Next navigation with one record.

---

## WhiskeyDataValidatorTest

This test file covers validation logic for whiskey product queries.

### Methods Tested

1. `checkAgeRange(String b1, String b2)`
2. `checkRegion(String r)`

### Age-Range Validation Coverage

1. Valid age range.
2. Null lower bound.
3. Null upper bound.
4. Empty lower bound.
5. Empty upper bound.
6. Non-numeric lower bound.
7. Non-numeric upper bound.
8. Negative lower bound.
9. Negative upper bound.
10. Lower bound above accepted range.
11. Upper bound above accepted range.
12. Upper bound lower than lower bound.
13. Boundary values.

### Region Validation Coverage

1. Valid region name.
2. Null region input.
3. Empty region input.
4. Non-alphabetic region input.

---

## UserDataValidatorTest

This test file covers validation logic for login and password-change behaviour.

### Methods Tested

1. `checkForFieldsPresent(String n, String p)`
2. `checkForFieldsPresent(String n, String oldp, String newp)`
3. `checkCurrentDetails(UserDetails ud, String n, String oldp)`
4. `checkNewDetails(UserDetails ud, String n, String oldp, String newp)`

### Login Field Validation Coverage

1. Username and password both present.
2. Null username.
3. Null password.
4. Empty username.
5. Empty password.
6. Non-alphabetic username.

### Change-Password Field Validation Coverage

1. Username, old password, and new password all present.
2. Null username.
3. Null old password.
4. Null new password.
5. Empty username.
6. Empty old password.
7. Empty new password.
8. Non-alphabetic username.

### Current Credential Validation Coverage

1. Valid current username and password.
2. Missing or corrupted user data.
3. Non-existing username.
4. Incorrect current password.

### New Password Validation Coverage

1. Valid new password.
2. New password matching old password.
3. New password too short.
4. New password without an uppercase letter.
5. New password without a lowercase letter.
6. New password without a digit.
7. New password containing invalid characters.

---

## Testing Evidence

The project includes a JUnit test result screenshot:

`junit-tests-passed.png`

This screenshot shows the NetBeans Test Results panel with passing JUnit tests.

The screenshot is included as supporting evidence only. The main verification method is the test suite itself, which can be run in the project environment.

---

## Portfolio Boundary

This project is included in my portfolio as a JavaFX university application with selected automated unit testing evidence.

It demonstrates:

1. JavaFX desktop application development.
2. Java application logic.
3. Input validation.
4. Login and password validation.
5. Record navigation behaviour.
6. JUnit 5 unit testing.
7. Practical manual testing during development.

It is not presented as a complete professional QA project.

The goal is not to overstate the project. The goal is to show that the application contains testable logic, includes automated JUnit 5 tests for selected behaviours, and can be reviewed as an existing university application under test.

---

## Portfolio Note

This summary documents the testing process honestly.

The strongest evidence is the JUnit 5 test suite covering selected validation and navigation logic. Other testing levels, such as component integration testing and system testing, were performed informally through development and manual application checks. System integration testing was not formally applicable, and acceptance-level validation was based on university project requirements rather than a separate UAT process.