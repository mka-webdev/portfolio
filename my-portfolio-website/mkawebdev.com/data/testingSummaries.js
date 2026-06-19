const testingSummaries = {
  'whiskey-product-information-system': {
    intro:
      'The Whiskey Product Information System includes JUnit 5 testing evidence for selected validation and application logic. Testing focused on record navigation, whiskey search validation, login validation, password-change validation, and credential checking. Other testing levels were performed informally through manual development checks and final application validation.',

    sections: [
      {
        title: 'Testing Levels',
        type: 'table',
        columns: ['Level', 'How it applied', 'Evidence / Artefacts', 'Notes'],
        rows: [
          [
            'Unit Testing',
            'Selected application logic and validation methods were tested using JUnit 5. The tests focused on record navigation, whiskey search validation, login validation, password-change validation, and credential checking.',
            'WhiskeyDataManagerTest.java, WhiskeyDataValidatorTest.java, UserDataValidatorTest.java.',
            'This is the strongest formal testing evidence for this project.'
          ],
          [
            'Component Integration Testing',
            'Limited informal component-level testing occurred during development by running the JavaFX application and checking that classes worked together as expected.',
            'Manual development checks while implementing and running the application.',
            'This was not documented as a formal integration test suite.'
          ],
          [
            'System Testing',
            'The completed desktop application was manually checked through normal user workflows, such as searching whiskey records, navigating product data, logging in, and changing passwords.',
            'Manual testing of the running JavaFX application.',
            'This was performed as practical validation of the working application, not as automated end-to-end testing.'
          ],
          [
            'System Integration Testing',
            'Not formally applicable to this project. The application did not involve major external systems or separate deployed services requiring dedicated system integration testing.',
            'Not applicable / no separate evidence.',
            'Database, file, or data interaction may have been part of application behaviour, but this was not treated as a separate formal system integration testing phase.'
          ],
          [
            'Acceptance Testing / UAT',
            'The project was assessed against the university project brief, design expectations, and required application behaviour.',
            'University requirements, design expectations, completed application behaviour, and final submission.',
            'No separate formal UAT document is included in this portfolio version.'
          ]
        ]
      },
      {
        title: 'Test Types Used',
        type: 'table',
        columns: ['Type', 'How it appeared in this project'],
        rows: [
          [
            'Automated Testing',
            'Automated JUnit 5 tests were written for selected validation and navigation logic.'
          ],
          [
            'Manual Testing',
            'The JavaFX application was manually checked during development and before submission.'
          ],
          [
            'Functional Testing',
            'Application behaviours were checked against expected outcomes, including whiskey record navigation, product query validation, login validation, password-change validation, and credential checking.'
          ],
          [
            'Regression Testing',
            'Regression checking was mainly supported by re-running the JUnit 5 tests after changes. This helped confirm that existing tested behaviours still passed.'
          ],
          [
            'Black-Box Testing',
            'Some validation tests were based on inputs and expected outputs, such as checking invalid age ranges, empty fields, invalid usernames, and password-rule failures.'
          ],
          [
            'White-Box Testing',
            'Some tests were written with knowledge of the application classes and methods, such as directly testing validator and data manager methods.'
          ]
        ]
      },
      {
        title: 'Automated Test Suite',
        body: [
          {
            heading: 'WhiskeyDataManagerTest.java',
            text:
              'This test file covers navigation behaviour for whiskey records, including next and previous navigation with no records, one record, multiple records, and null data scenarios.'
          },
          {
            heading: 'WhiskeyDataValidatorTest.java',
            text:
              'This test file covers validation logic for whiskey product queries, including age-range validation and region validation.'
          },
          {
            heading: 'UserDataValidatorTest.java',
            text:
              'This test file covers validation logic for login and password-change behaviour, including field presence, current credential checks, and new password validation rules.'
          }
        ]
      },
      {
        title: 'Portfolio Boundary',
        body: [
          {
            heading: 'Evidence Status',
            text:
              'The strongest testing evidence for this project is the JUnit 5 test suite covering selected validation and navigation logic.'
          },
          {
            heading: 'Honest Scope',
            text:
              'The project is not presented as a complete professional QA project. Component integration testing and system testing were performed informally through development and manual application checks.'
          },
          {
            heading: 'Project Framing',
            text:
              'This project is included as a JavaFX university application with selected automated unit testing evidence and practical manual validation.'
          }
        ]
      }
    ]
  },

  'wisdom-game': {
    intro:
      'Wisdom was tested mainly through playtesting, manual gameplay validation, feedback analysis, bug identification, and iterative refinement. The project did not include automated unit tests or a formal software testing suite, so its strongest testing evidence is the playtesting and feedback process used to improve gameplay clarity and player experience.',

    sections: [
      {
        title: 'Testing Levels',
        type: 'table',
        columns: ['Level', 'How it applied in this project', 'Evidence / Artefacts', 'Notes'],
        rows: [
          [
            'Unit Testing',
            'Not formally performed. The project did not include automated unit tests for Unity/C# scripts.',
            'No unit test suite.',
            'This was outside the project scope and timeframe.'
          ],
          [
            'Component Integration Testing',
            'Informal testing occurred while combining scripts, game objects, UI elements, puzzle systems, inventory behaviour, level objects, and player interactions inside Unity.',
            'Unity Play Mode testing, team debugging, local gameplay checks, and issue fixing during development.',
            'This was practical developer testing rather than a formal integration test suite.'
          ],
          [
            'System Testing',
            'The game was tested as a complete playable experience. This included checking whether the player could move through levels, interact with puzzles, collect or use items, and progress through intended gameplay sequences.',
            'Playtesting sessions, gameplay checks, feedback review, and final build validation.',
            'This was the closest equivalent to system-level testing for the game.'
          ],
          [
            'System Integration Testing',
            'Not formally applicable as a separate testing phase. The project did not involve major external systems, APIs, or separately deployed services.',
            'Not applicable / no separate evidence.',
            'Unity systems were integrated inside the game project, but this was not treated as formal system integration testing.'
          ],
          [
            'Acceptance Testing / Player Validation',
            'Player feedback was used to check whether the game met intended design goals, such as clarity, playability, puzzle understanding, and level flow.',
            'Playtesting feedback, team review, design expectations, gameplay iteration, and final project validation.',
            'This was closer to player-focused validation than formal UAT.'
          ]
        ]
      },
      {
        title: 'Test Types Used',
        type: 'table',
        columns: ['Type', 'How it appeared in this project'],
        rows: [
          [
            'Playtesting',
            'Players tested the game by playing through sections and providing feedback on clarity, gameplay, puzzles, difficulty, and usability.'
          ],
          [
            'Manual Testing',
            'Team members manually tested game features inside Unity and in playable builds.'
          ],
          [
            'Functional Testing',
            'Gameplay features were checked to confirm that puzzles, inventory interactions, player movement, triggers, UI, and progression worked as expected.'
          ],
          [
            'Usability Testing',
            'Playtesting helped identify where players became confused, missed cues, misunderstood objectives, or struggled with interactions.'
          ],
          [
            'Exploratory Testing',
            'Team members and playtesters discovered issues by freely interacting with the game and trying different gameplay paths.'
          ],
          [
            'Regression Checking',
            'After bugs or gameplay issues were fixed, the affected areas were rechecked to confirm the issue was resolved and did not obviously break related behaviour. This was informal rather than a formal regression test phase.'
          ],
          [
            'Bug Fix Verification',
            'Bugs identified during development or playtesting were checked again after fixes were applied.'
          ]
        ]
      },
      {
        title: 'Playtesting Focus',
        body: [
          {
            heading: 'Gameplay Clarity',
            text:
              'Testing checked whether players understood the gameplay mechanics, level flow, objectives, and what they were expected to do next.'
          },
          {
            heading: 'Puzzle Interaction',
            text:
              'Playtesting helped identify whether puzzle interactions were clear and whether players understood how to interact with puzzle-related objects.'
          },
          {
            heading: 'Inventory and Item Use',
            text:
              'Testing checked whether players understood inventory behaviour, item collection, and item use during gameplay.'
          },
          {
            heading: 'Player Guidance',
            text:
              'Feedback was used to identify where players needed clearer cues, instructions, or visual guidance.'
          },
          {
            heading: 'Difficulty and Balance',
            text:
              'Player feedback helped assess whether the game felt understandable, playable, and appropriately balanced.'
          }
        ]
      },
      {
        title: 'Contribution Boundary',
        body: [
          {
            heading: 'My Role',
            text:
              'My designated role was Lead Programmer. In practice, my contribution also included leading the playtesting process, helping identify gameplay and usability issues, supporting bug triage, and contributing to gameplay refinement.'
          },
          {
            heading: 'Team Collaboration',
            text:
              'Bug fixing and gameplay refinement were collaborative. The final game was the result of shared team effort across design, art, coding, testing, debugging, playtesting, and iteration.'
          },
          {
            heading: 'Portfolio Boundary',
            text:
              'This project is not presented as a formal software testing case study. It is presented as a Unity/C# game project where quality was improved through playtesting, manual validation, feedback analysis, and iteration.'
          }
        ]
      }
    ]
  },

  'audio-track-chat-app': {
    intro:
      'The Audio Track Chat App had a dedicated Quality Manager who led the broader QA process, including UAT, testing documentation, quality evidence, and testing-related reporting. My direct testing contribution was implementation-focused: I developed the automated JUnit 5 and Mockito test suite and created the k6 load testing script.',

    sections: [
      {
        title: 'Testing Levels',
        type: 'table',
        columns: ['Level', 'How it applied in this project', 'Evidence / Artefacts', 'My contribution'],
        rows: [
          [
            'Unit Testing',
            'Selected application behaviours were tested using automated Java tests. These tests focused on checking specific controller, service, and application behaviours in isolation or near-isolation.',
            'JUnit 5 and Mockito test suite included in the project codebase.',
            'I developed the automated JUnit 5 and Mockito test suite.'
          ],
          [
            'Component Integration Testing',
            'During development, team members tested how implemented components worked together before merging or finalising features. This included checking interactions between controllers, services, repositories, security configuration, persistence, and feature logic.',
            'Local testing, branch-based checks, peer review, and working-code validation before integration into the main project.',
            'I contributed as both developer and Project Manager by supporting working-code validation and helping coordinate project progress.'
          ],
          [
            'System Testing',
            'The application was checked as a complete system through manual workflow validation. This included confirming that key features worked together in the running Spring Boot application.',
            'Manual checks of chat functionality, audio-track sharing, playlist-related behaviour, authentication, persistence, and iTunes Search API integration.',
            'I supported system-level validation through project coordination, implementation work, and testing-related artefacts.'
          ],
          [
            'System Integration Testing',
            'The project included interaction with external or separate system elements, including persistence and the iTunes Search API. These integrations were checked as part of the running application rather than through a dedicated formal system integration test suite.',
            'Working application behaviour involving database persistence and external API integration.',
            'I contributed to the project implementation and validation process, but this was not documented as a separate formal system integration testing phase.'
          ],
          [
            'Acceptance Testing / UAT',
            'Acceptance-level testing was handled as part of the broader quality assurance process. The project was checked against expected user workflows, project requirements, and team-defined functionality.',
            'Quality Manager testing documentation, UAT evidence, project requirements, use cases, and final project validation.',
            'I contributed use cases and expected workflow documentation as Project Manager. The formal UAT/testing report was prepared by the Quality Manager.'
          ]
        ]
      },
      {
        title: 'Test Types Used',
        type: 'table',
        columns: ['Type', 'How it appeared in this project'],
        rows: [
          [
            'Automated Testing',
            'Automated Java tests were created using JUnit 5 and Mockito.'
          ],
          [
            'Manual Testing',
            'Team members manually checked features during development, integration, and final validation.'
          ],
          [
            'Functional Testing',
            'Core features were checked against expected behaviours, including chat, audio-track sharing, playlists, authentication, persistence, and iTunes Search API functionality.'
          ],
          [
            'Regression Testing',
            'Regression checking was mainly handled through repeated test execution and feature re-checking after changes. This was supported by the automated test suite and broader QA work, but was not documented as a separate dedicated regression test phase by me.'
          ],
          [
            'Load Testing',
            'A k6 script was created to simulate multiple users interacting with the application.'
          ],
          [
            'Acceptance Testing / UAT',
            'Acceptance-level validation was documented by the Quality Manager and supported by project requirements, use cases, and expected workflows.'
          ]
        ]
      },
      {
        title: 'My Testing Artefacts',
        body: [
          {
            heading: 'JUnit 5 and Mockito Test Suite',
            text:
              'I developed the automated Java test suite using JUnit 5, Mockito, and Spring Boot testing support. The tests checked selected application behaviours and supported project testing work.'
          },
          {
            heading: 'k6 Load Testing Script',
            text:
              'I created the k6 load testing script stored as load-tests/ten-users.js. This script was created to simulate multiple users interacting with the application.'
          },
          {
            heading: 'Project-Level Support',
            text:
              'As Project Manager, I also supported project-level planning, use case documentation, expected workflows, feature validation, and team coordination.'
          }
        ]
      },
      {
        title: 'Contribution Boundary',
        body: [
          {
            heading: 'My Direct Contribution',
            text:
              'My direct testing-related contribution was the automated JUnit 5 and Mockito test suite, the k6 load testing script, and support for project planning and workflow documentation.'
          },
          {
            heading: 'Quality Manager Contribution',
            text:
              'The broader QA process, user acceptance testing, testing documentation, quality evidence, and testing-related reporting were led by the Quality Manager.'
          },
          {
            heading: 'Portfolio Boundary',
            text:
              'This project is included primarily as an enterprise software development team project with selected testing-related implementation work. It is not presented as a dedicated testing portfolio project.'
          }
        ]
      }
    ]
  },

  'online-accessibility-governance-platform': {
    intro:
      'Testing summary content for this project will be added later.',
    sections: []
  },

  'nuscan-android-app': {
    intro:
      'Testing summary content for this project will be added later.',
    sections: []
  }
};

module.exports = testingSummaries;