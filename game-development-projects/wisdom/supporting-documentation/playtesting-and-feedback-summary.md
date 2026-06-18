# Playtesting and Testing Summary

This document summarises the playtesting, testing-related work, and bug-fixing process completed for **Wisdom**, a collaborative Unity/C# 2D game project.

Wisdom was developed as a team game development project. Although team members had designated roles, the actual development process was highly collaborative. Team members often contributed outside their assigned roles, including coding, visual fixes, gameplay adjustments, debugging, documentation, and design refinement.

My designated role was **Lead Programmer**. In practice, my contribution also included leading the playtesting process, helping identify gameplay and usability issues, supporting bug triage, and contributing to gameplay refinement.

This project is not presented as a formal software testing case study with unit tests, integration tests, or automated test suites. Instead, the testing evidence is documented through playtesting, gameplay validation, feedback analysis, bug identification, and iterative improvement.

---

## Testing Context

Game testing in this project was different from testing a standard Java, web, or API application.

The main focus was not only whether the code executed correctly, but whether the game was understandable, playable, balanced, and enjoyable for players.

Testing focused on:

1. Gameplay clarity.
2. Puzzle interaction.
3. Inventory and item use.
4. Level flow.
5. Player guidance.
6. Difficulty balance.
7. Usability issues.
8. Repeated player confusion.
9. Bugs affecting gameplay or progression.
10. Whether implemented changes improved the player experience.

Because Wisdom was a Unity game project, much of the testing happened through playing the game, observing player behaviour, identifying bugs or confusion points, and improving the game through iteration.

---

## Testing Levels

| Testing Level | How it applied in this project | Evidence / Artefacts | Notes |
| --- | --- | --- | --- |
| Unit Testing | Not formally performed. The project did not include automated unit tests for Unity/C# scripts. | No unit test suite. | This was outside the project scope and timeframe. |
| Component Integration Testing | Informal testing occurred while combining scripts, game objects, UI elements, puzzle systems, inventory behaviour, level objects, and player interactions inside Unity. | Unity play mode testing, team debugging, local gameplay checks, and issue fixing during development. | This was practical developer testing rather than a formal integration test suite. |
| System Testing | The game was tested as a complete playable experience. This included checking whether the player could move through levels, interact with puzzles, collect or use items, and progress through intended gameplay sequences. | Playtesting sessions, gameplay checks, feedback review, and final build validation. | This was the closest equivalent to system-level testing for the game. |
| System Integration Testing | Not formally applicable as a separate testing phase. The project did not involve major external systems, APIs, or separately deployed services. | Not applicable / no separate evidence. | Unity systems were integrated inside the game project, but this was not treated as formal system integration testing. |
| Acceptance Testing / Player Validation | Player feedback was used to check whether the game met intended design goals, such as clarity, playability, puzzle understanding, and level flow. | Playtesting feedback, team review, design expectations, gameplay iteration, and final project validation. | This was closer to player-focused validation than formal UAT. |

---

## Playtesting

Playtesting was the main testing approach used for this project.

The purpose of playtesting was to check whether players could understand, navigate, and complete key parts of the game.

The playtesting process focused on:

1. Whether players understood the gameplay mechanics.
2. Whether puzzle interactions were clear.
3. Whether players understood inventory and item use.
4. Whether level flow made sense.
5. Whether players knew where to go next.
6. Whether difficulty and balance felt appropriate.
7. Whether usability issues caused confusion.
8. Whether repeated issues appeared across multiple playtesters.
9. Whether changes improved the player experience.

Playtesting was treated as part of the development process rather than something done only at the end.

---

## Test Types Used

| Test Type | How it appeared in this project |
| --- | --- |
| Playtesting | Players tested the game by playing through sections and providing feedback on clarity, gameplay, puzzles, difficulty, and usability. |
| Manual Testing | Team members manually tested game features inside Unity and in playable builds. |
| Functional Testing | Gameplay features were checked to confirm that puzzles, inventory interactions, player movement, triggers, UI, and progression worked as expected. |
| Usability Testing | Playtesting helped identify where players became confused, missed cues, misunderstood objectives, or struggled with interactions. |
| Exploratory Testing | Team members and playtesters discovered issues by freely interacting with the game and trying different gameplay paths. |
| Regression Checking | After bugs or gameplay issues were fixed, the affected areas were rechecked to confirm the issue was resolved and did not obviously break related behaviour. This was informal rather than a formal regression test phase. |
| Bug Fix Verification | Bugs identified during development or playtesting were checked again after fixes were applied. |

---

## Bug Identification and Fixing

Bugs and issues were identified through development, Unity play mode testing, team review, and playtesting.

Although bugs were often collected or coordinated through the programming side of the project, fixes were a team effort. The project was highly collaborative, and different team members helped resolve different types of problems.

Bug-fixing work included:

1. Gameplay logic fixes.
2. Puzzle interaction fixes.
3. Inventory and item-use fixes.
4. Visual or level-layout fixes.
5. UI and guidance improvements.
6. Player-flow adjustments.
7. Balance and difficulty adjustments.
8. Fixes to confusing or unclear interactions.

This reflected the nature of the project: even though roles existed, the team often worked across role boundaries to complete the game.

---

## Collaboration Note

Wisdom was a major collaborative project.

Although team members had designated roles, the actual development process required everyone to contribute beyond those role titles. Artists contributed to implementation tasks, programmers helped solve visual and gameplay issues, and the team worked together to refine the game.

My main role was Lead Programmer, but the final game was the result of shared team effort across design, art, coding, testing, debugging, playtesting, and iteration.

This summary does not present all testing or bug-fixing work as my individual work. It documents my role in leading programming and playtesting while acknowledging that the game was built and improved collaboratively.

---

## My Testing-Related Contribution

My testing-related contribution included:

1. Leading the playtesting process.
2. Identifying gameplay areas that needed testing.
3. Helping collect and review player feedback.
4. Analysing repeated confusion points or usability issues.
5. Connecting playtesting feedback to practical improvements.
6. Supporting bug identification and bug-fix verification.
7. Helping refine gameplay, puzzle interaction, and player guidance.
8. Working with the team to improve the game through iteration.

This work supported both the quality of the game and the documentation of the development process.

---

## Portfolio Boundary

This project is included in my portfolio as a collaborative Unity/C# 2D game project.

It demonstrates:

1. Unity game development.
2. C# gameplay programming.
3. Puzzle and interaction logic.
4. Inventory and item-use systems.
5. Level-based gameplay.
6. Team collaboration.
7. Playtesting and feedback analysis.
8. Iterative improvement based on player experience.

It is not presented as a formal software testing project.

The goal is not to overstate the testing process. The goal is to document how quality was improved in a game development context through playtesting, manual validation, bug fixing, feedback analysis, and iteration.

---

## Portfolio Note

This repository does not include the original Game Design Document, internal team documents, Unity source project, private team material, or detailed internal bug records.

This file provides a public-facing summary of the playtesting and testing-related work completed for the project.

The strongest testing-related evidence for Wisdom is not automated test code. It is the playtesting process, player feedback analysis, bug identification, team-based fixes, and gameplay iteration used to improve the final game.