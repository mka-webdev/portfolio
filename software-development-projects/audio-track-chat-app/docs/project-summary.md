# Sprint Plan Summary

This document summarises the sprint planning used during the Audio Track Chat App team project.

The original sprint plan was updated weekly from Week 8 to Week 11. This public portfolio version combines those weekly updates into one cleaned summary. Individual names, private Trello links, meeting recordings, and weekly assessment-specific artefact structure are omitted.

## Planning approach

The project was managed through weekly sprint planning. Work was separated into feature development tasks and ongoing project coordination tasks.

Feature work was organised through weekly master cards with subtasks and acceptance criteria. Ongoing project work included sprint planning, requirements updates, diagrams, risk register updates, testing notes, and team coordination.

Tasks were prioritised using four factors:

1. Complexity
2. Impact on the project
3. Demo value
4. Risk reduction

These scores helped identify high-value and high-risk work during each sprint.

## Team role structure

| Role | Planning responsibility |
| --- | --- |
| Project Manager | Sprint planning, project coordination, risk tracking, delivery alignment, and automated test suite development. |
| Technical Manager | Requirements, diagrams, technical planning, and implementation direction. |
| Quality Manager | Testing notes, user acceptance testing, quality evidence, and acceptance criteria verification. |
| Programmer 1 | Feature implementation and interface-related development tasks. |
| Programmer 2 | Feature implementation and service/data-related development tasks. |

## Sprint overview

| Sprint | Main focus | Main outcome |
| --- | --- | --- |
| Week 8 / Sprint 1 | Chat feature | Delivered the initial chat feature with message persistence, message retrieval, and a simple web interface. |
| Week 9 / Sprint 2 | Audio tracks and playlists | Added audio-track and playlist functionality, including local sample tracks and playlist-related entities. |
| Week 10 / Sprint 3 | Scalability foundation, ratings, and favourites | Refactored services to support separation of concerns and added rating and favourite-related functionality. |
| Week 11 / Sprint 4 | Authentication, iTunes API integration, and final code adjustments | Added authentication, integrated the iTunes API, protected core areas, reviewed the test suite, and finalised the project. |

## Week 8 sprint goal

The Week 8 sprint goal was to deliver a working chat feature that allowed users to send and view messages through a simple web interface.

Before and during the first sprint, project setup tasks were completed. These included establishing communication channels, setting up the repository, inviting collaborators, creating project planning templates, and preparing the initial project management structure.

## Week 8 feature tasks

| Feature area | Task | Status |
| --- | --- | --- |
| Chat | Chat master card | Done |
| Chat | Solution draft | Done |
| Chat | Message entity and repository | Done |
| Chat | Chat UI and conversation view | Done |
| Chat | Message retrieval logic | Done |
| Chat | Unit or integration test | Done |
| Chat | Message persistence at runtime | Done |

## Week 8 coordination tasks

| Task | Responsible role | Status |
| --- | --- | --- |
| Update sprint plan | Project Manager | Ongoing |
| Adjust risk levels | Project Manager | Ongoing |
| Facilitate weekly meeting | Project Manager | Ongoing |
| Update weekly requirements | Technical Manager | Ongoing |
| Update weekly diagrams | Technical Manager | Ongoing |
| Update testing notes | Quality Manager | Ongoing |

## Week 8 outcome

The chat feature was delivered as the first working feature foundation. The project backlog was separated into ongoing coordination work and weekly feature work. This structure was used for the remaining sprints.

## Week 9 sprint goal

The Week 9 sprint goal was to extend the system with audio functionality. This included storing and managing tracks and supporting playlist sharing between users. These features built on the chat functionality delivered in Week 8.

## Week 9 feature tasks

| Feature area | Task | Status |
| --- | --- | --- |
| Audio | Audio master card | Done |
| Audio | Add sample tracks to static music resources | Done |
| Audio | Audiotrack entity and repository | Done |
| Audio | Playlist entity and repository | Done |
| Audio | Playlist UI with Thymeleaf | Done |
| Audio | Playlist item, repository, and controller | Done |

## Week 9 coordination tasks

| Task | Responsible role | Status |
| --- | --- | --- |
| Update sprint plan | Project Manager | Ongoing |
| Adjust risk levels | Project Manager | Ongoing |
| Facilitate weekly meeting | Project Manager | Ongoing |
| Update weekly requirements | Technical Manager | Ongoing |
| Update weekly diagrams | Technical Manager | Ongoing |
| Update testing notes | Quality Manager | Ongoing |

## Week 9 outcome

The application was extended from chat-only functionality into an audio-track and playlist system. Local sample tracks were added to support playback and demonstration of audio-track features.

## Week 10 sprint goal

The Week 10 sprint goal was to establish a foundation for scalability and multi-user use. The sprint also aimed to separate chat and audio services more clearly and expose application features through REST and JSON endpoints.

A second feature focus was added for rating audio tracks and artists, including favourite-related functionality.

## Week 10 feature tasks

| Feature area | Task | Status |
| --- | --- | --- |
| Scaling | Scaling master card | Done |
| Scaling | Refactor chat code to allow service separation | Done |
| Scaling | Refactor audio code to allow service separation | Done |
| Ratings | Ratings master card | Done |
| Ratings | Extend audiotrack with rating field | Done |
| Ratings | Add favourite relationship | Done |
| Ratings | Update Thymeleaf UI for ratings and favourites | Done |
| Ratings | Write unit test for rating persistence | Done |

## Week 10 coordination tasks

| Task | Responsible role | Status |
| --- | --- | --- |
| Update sprint plan | Project Manager | Ongoing |
| Adjust risk levels | Project Manager | Ongoing |
| Facilitate weekly meeting | Project Manager | Ongoing |
| Update weekly requirements | Technical Manager | Ongoing |
| Update weekly diagrams | Technical Manager | Ongoing |
| Update testing notes | Quality Manager | Ongoing |

## Week 10 outcome

The project moved from feature implementation into service separation and multi-user readiness. Ratings and favourites were added, and the application structure was adjusted to better support later authentication and API integration.

Peer-coding sessions were also scheduled during this stage to support integration and coordination.

## Week 11 sprint goal

The Week 11 sprint goal was to complete external API integration and authentication.

Users needed to log in with unique credentials so sessions, ratings, favourites, playlists, and related interactions could be tied to individual users. The iTunes API was integrated to enrich track information and expand audio-track search functionality.

The system also continued to support REST and JSON endpoints for messages, tracks, playlists, and ratings, while maintaining separation between chat and audio services.

## Week 11 feature tasks

| Feature area | Task | Status |
| --- | --- | --- |
| iTunes API | iTunes API master card | Done |
| iTunes API | Implement API client using RestTemplate | Done |
| iTunes API | Add service to fetch and display track information | Done |
| iTunes API | Update Thymeleaf UI to show results | Done |
| iTunes API | Write integration test for API fetch | Done |
| iTunes API | Verify acceptance criteria | Done |
| Authentication | Authentication master card | Done |
| Authentication | Add Spring Security and form login | Done |
| Authentication | Protect chat and audio endpoints | Done |
| Authentication | Write unit test for login requirement | Done |
| Authentication | Verify acceptance criteria | Done |
| Final adjustments | Final code adjustments master card | Done |
| Final adjustments | Review and polish Thymeleaf UI | Done |
| Final adjustments | Clean unused imports, comments, and dead code | Done |
| Final adjustments | Review and polish MVC structure | Done |
| Final adjustments | Review and polish test suite | Done |
| Final adjustments | Verify acceptance criteria | Done |

## Week 11 coordination tasks

| Task | Responsible role | Status |
| --- | --- | --- |
| Update sprint plan | Project Manager | Done |
| Adjust risk levels | Project Manager | Done |
| Facilitate weekly meeting | Project Manager | Done |
| Update weekly requirements | Technical Manager | Done |
| Update weekly diagrams | Technical Manager | Done |
| Update testing notes | Quality Manager | Done |
| Demonstration of understanding recording | Team | Done |

## Week 11 outcome

The final sprint completed the main integration work. Authentication was added, the iTunes API was connected, and final code adjustments were completed. The project plan and risk register were also finalised.

## Sprint progression summary

| Stage | Description |
| --- | --- |
| Chat foundation | Initial messaging functionality and message persistence. |
| Audio-track and playlist features | Local sample tracks, playlist data, and playlist interface support. |
| Scalability and ratings | Service separation, REST and JSON support, ratings, and favourites. |
| Authentication and iTunes API integration | User-specific access, external track search, final code review, and test suite polish. |

## Project Manager contribution

My Project Manager contribution included coordinating sprint planning, maintaining sprint plan updates, tracking project risks, supporting team communication, and helping keep weekly delivery aligned with the project requirements.

I also contributed to the technical evidence by developing automated tests using JUnit 5 and Mockito and reviewing the test suite during the final sprint.

## Portfolio note

This summary is based on the original Week 8 to Week 11 sprint plan updates. It keeps the sprint goals, task progression, and planning structure, but removes private team details, private Trello links, meeting recordings, and weekly assessment-specific artefact presentation.
