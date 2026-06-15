# Audio Track Chat App

This is a university team project developed for the **Enterprise Software Development** unit.

The project was based on a Twitter/X and Spotify-style brief. The system allows users to chat with friends, search for audio tracks, share tracks and playlists, rate tracks, and manage favourites.

The application was developed using Spring Boot, Thymeleaf, Maven, and related Java testing tools.

---

## 👥 Team roles and contributions

This was a five-member university team project. For confidentiality, individual names, student numbers, and personal GitHub usernames are omitted from this public portfolio version. Contributions are described by project role.

| Role | Main contribution |
| --- | --- |
| Project Manager | Coordinated project planning, documentation structure, team communication, and delivery alignment. Developed the automated JUnit 5, Mockito test suite, and the k6 load testing script used to support project testing and quality assurance evidence. |
| Technical Manager | Led technical planning and supported implementation decisions, project structure, integration direction, and technical documentation. |
| Quality Manager | Led quality assurance activities, including user acceptance testing, testing documentation, and quality-related evidence. Used the automated test suite as part of the broader QA process. |
| Programmer 1 | Contributed to application feature implementation, source code updates, and project delivery tasks. |
| Programmer 2 | Contributed to application feature implementation, source code updates, and project delivery tasks. |

### My contribution

My role was Project Manager. I coordinated planning, documentation structure, team communication, and delivery alignment.

I also developed the automated JUnit 5 and Mockito test suite and created the k6 load testing script. The Quality Manager used this testing evidence as part of the user acceptance testing and quality assurance process, alongside other QM responsibilities.

---

## 🛠 Technologies used

- Java 21
- Spring Boot
- Spring Data JPA
- H2 in-memory database
- Thymeleaf
- Maven
- JUnit 5
- Mockito
- k6
- Jackson ObjectMapper

---

## 🎧 Audio-track data

The application uses two audio-track sources:

- an iTunes Search API integration for searching external audio-track metadata
- local demo audio files stored in `src/main/resources/static/music` for playback and seeded demo data

The local audio files are retained in the public portfolio version so the audio-track playback and sharing features can run without missing static resources.

---

## 📂 Repository structure

```txt
docs/                       Project, testing, sprint, and risk summaries
load-tests/                 k6 load testing scripts
screenshots/                Application screenshots captured for portfolio evidence
src/                        Application source code
src/test/                   Automated test classes
testing-evidence/           Testing evidence screenshots and outputs
README.md                   Project documentation
pom.xml                     Maven configuration
```

---

## 🚀 Running the application

Run the application from the project root with Maven:

```bash
mvn spring-boot:run
```

Open the application in a browser:

```txt
http://localhost:8080/
```

---

## 🧪 Test suite

Run the Java automated tests:

```bash
mvn test
```

The automated test suite uses JUnit 5 and Mockito.

---

## 📈 Load testing

A basic k6 load test script is included in the `load-tests` folder.

Assuming Grafana k6 is installed on the testing machine, run:

```bash
k6 run load-tests/ten-users.js
```

---

### Demo login credentials

The application uses in-memory demo accounts for local testing.

Multiple demo users can log in with the same password:
admin
friend
user1
user2
user3
user4
user5
user6
user7
user8
user9
user10

```txt
Username: user
Password: password
```

---

## ✅ User acceptance testing

User acceptance testing and quality assurance evidence were managed by the Quality Manager.

The automated JUnit 5 and Mockito test suite and k6 load testing script were developed by the Project Manager (myself) and used as part of the broader testing and quality assurance process.

The application also included a testing endpoint used to demonstrate expected application behaviour:

```txt
http://127.0.0.1:8080/tests
```

---

## 📝 Testing note

The project included automated Java tests, user acceptance testing evidence supported by the automated test suite, and a basic k6 load testing script.

---

## 📌 Portfolio note

This project is included as a Year 3 enterprise software development project. It demonstrates Spring Boot application development, team-based delivery, audio track search and sharing features, chat functionality, and testing evidence using JUnit 5, Mockito, user acceptance testing, and k6.
