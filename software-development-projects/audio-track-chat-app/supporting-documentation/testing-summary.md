# Testing Summary

This document summarises the testing process and testing-related contributions for the Audio Track Chat App university team project.

The project had a dedicated Quality Manager who led the broader quality assurance process, including user acceptance testing, testing documentation, quality evidence, and testing-related reporting.

My own testing-related contribution was implementation-focused. I developed the automated JUnit 5 and Mockito test suite and created the k6 load testing script. I also contributed to project-level planning and use case documentation as Project Manager.

This summary does not reproduce the full Quality Manager report or present that work as my own. Instead, it documents how testing was represented across the project and clarifies my direct contribution.

---

## Testing Levels

| Testing Level | How it applied in this project | Evidence / Artefacts | My involvement |
| --- | --- | --- | --- |
| Unit Testing | Selected application behaviours were tested using automated Java tests. These tests focused on checking specific controller, service, and application behaviours in isolation or near-isolation. | JUnit 5 and Mockito test suite included in the project codebase. | I developed the automated JUnit 5 and Mockito test suite. |
| Component Integration Testing | During development, team members tested how implemented components worked together before merging or finalising features. This included checking interactions between controllers, services, repositories, security configuration, persistence, and feature logic. | Local testing, branch-based checks, peer review, and working-code validation before integration into the main project. | I contributed as both developer and Project Manager by supporting working-code validation and helping coordinate project progress. |
| System Testing | The application was checked as a complete system through manual workflow validation. This included confirming that key features worked together in the running Spring Boot application. | Manual checks of chat functionality, audio-track sharing, playlist-related behaviour, authentication, persistence, and iTunes Search API integration. | I supported system-level validation through project coordination, implementation work, and testing-related artefacts. |
| System Integration Testing | The project included interaction with external or separate system elements, including persistence and the iTunes Search API. These integrations were checked as part of the running application rather than through a dedicated formal system integration test suite. | Working application behaviour involving database persistence and external API integration. | I contributed to the project implementation and validation process, but this was not documented as a separate formal system integration testing phase. |
| Acceptance Testing / UAT | Acceptance-level testing was handled as part of the broader quality assurance process. The project was checked against expected user workflows, project requirements, and team-defined functionality. | Quality Manager testing documentation, UAT evidence, project requirements, use cases, and final project validation. | I contributed use cases and expected workflow documentation as Project Manager. The formal UAT/testing report was prepared by the Quality Manager. |

---

## Test Types Used

The project included several test types, although not all were formalised as separate testing phases.

| Test Type | How it appeared in this project |
| --- | --- |
| Automated Testing | Automated Java tests were created using JUnit 5 and Mockito. |
| Manual Testing | Team members manually checked features during development, integration, and final validation. |
| Functional Testing | Core features were checked against expected behaviours, including chat, audio-track sharing, playlists, authentication, persistence, and iTunes Search API functionality. |
| Regression Testing | Regression checking was mainly handled through repeated test execution and feature re-checking after changes. This was supported by the automated test suite and broader QA work, but was not documented as a separate dedicated regression test phase by me. |
| Load Testing | A k6 script was created to simulate multiple users interacting with the application. |
| Acceptance Testing / UAT | Acceptance-level validation was documented by the Quality Manager and supported by project requirements, use cases, and expected workflows. |

---

## My Testing-Related Contribution

My direct testing-related contribution included:

1. Developing the automated Java test suite using JUnit 5 and Mockito.
2. Creating the k6 load testing script for basic load testing.
3. Supporting feature validation and project coordination as Project Manager.
4. Writing or contributing to use cases and expected workflows that supported acceptance-level understanding.

These artefacts were used as part of the wider quality assurance process documented by the Quality Manager.

---

## Automated Test Suite

The automated test suite was implemented using:

1. JUnit 5
2. Mockito
3. Spring Boot testing support

The tests were written to check selected application behaviours and support project testing work. They were part of the submitted project codebase, but they are not presented here as a complete modern testing case study.

This project is kept close to its original university submission state. The test suite is included as evidence of my development and testing-related implementation contribution, not as a full demonstration of my current testing methodology.

---

## Load Testing Script

The project includes a k6 load testing script:

`load-tests/ten-users.js`

This script was created to simulate multiple users interacting with the application. It is retained as part of my testing-related implementation contribution.

---

## Quality Manager Contribution

The project had a dedicated Quality Manager who led the broader quality assurance process.

This included:

1. User acceptance testing.
2. Testing documentation.
3. Quality assurance evidence.
4. Broader testing-related reporting.

The Quality Manager used project testing evidence, including the automated test suite and load testing output, as part of the broader QA process. Their testing report was valuable within the original team project, but it is not reproduced in this public portfolio version because it was not my individual artefact.

---

## Portfolio Boundary

This project is included in my portfolio primarily as a Year 3 enterprise software development project. It demonstrates Spring Boot development, team delivery, project management, chat functionality, audio-track sharing, iTunes Search API integration, persistence, authentication, and selected testing-related implementation work.

It is not presented as a dedicated testing portfolio project.

This testing summary is included to show how testing existed across the project, how different testing levels can be identified retrospectively, and which testing artefacts I personally contributed.

Dedicated testing artefacts will be developed separately in the testing portfolio using sources and exercises intended specifically for that purpose.

---

## Portfolio Note

This summary clarifies contribution boundaries.

The broader QA and UAT documentation is credited to the Quality Manager. This public portfolio documentation focuses on the testing-related artefacts I directly contributed, including the JUnit 5 and Mockito test suite, the k6 load testing script, and project-management artefacts such as use cases and expected workflows.

The testing process is documented honestly: some testing levels were supported by formal artefacts, while others were performed informally through development workflow, manual validation, team coordination, and final project checks.