# Campus Pulse

Campus Pulse is an AI-powered smart campus management system for students. It brings campus events, announcements, mentorship, communication, and service routing into one server-rendered Spring Boot application.

## Problem Statement

Students often search across disconnected channels for academic help, events, notices, mentors, and campus services. Campus Pulse provides one clear starting point and routes questions to the most relevant campus module.

## Solution

The application combines MongoDB-backed campus data with a transparent, rule-based Smart Routing Hub. The Intelligent Student Assistant uses the routing result and available campus records to return useful answers without pretending that an external AI provider is configured.

## Features

- Student CRUD with validation and search
- Upcoming event listing and search by title/category
- Important announcements and filtering
- Mentor discovery by expertise/department and availability
- Campus messages with pending status tracking
- Smart Routing Hub for academic, events, mentorship, administration, and announcement queries
- Intelligent Student Assistant with a rule-based fallback
- Responsive Thymeleaf dashboard with summary cards
- REST APIs and centralized error responses
- Built-in idempotent demo database seeder with 15 students, 8 faculty mentors, 10 events, 10 announcements, and 16 messages

## Novelty

The Smart Routing Hub is the core novelty. It gives a student one natural-language entry point and explains why a query was routed to a campus destination. The routing engine is isolated in `routing` so a real AI provider can be added later without rewriting controllers or the UI.

## Technology Stack

- Java 24 and Spring Boot 4.1.1
- Maven Wrapper
- Spring Web MVC and Thymeleaf
- Spring Data MongoDB
- Jakarta Bean Validation
- Lombok
- HTML and CSS

## Architecture

The code follows a beginner-friendly layered architecture:

`Controller -> Service -> Repository -> MongoDB`

Routing and assistant behavior are separate services. Models represent MongoDB documents, controllers expose REST and web views, services hold business rules, and repositories provide data access.

## Folder Structure

```text
src/main/java/com/campuspulse/campus_pulse/
├── config          Demo data configuration
├── controller      REST and Thymeleaf controllers
├── dto             Assistant response records
├── exception       Centralized API error handling
├── model           MongoDB documents
├── repository      Spring Data MongoDB repositories
├── routing         Smart Routing Hub
└── service         Business logic and assistant fallback
src/main/resources/
├── static/css      Responsive dashboard styles
├── templates       Thymeleaf views
└── application.properties
```

## MongoDB Setup

Install MongoDB locally and start it on the default port `27017`. Campus Pulse uses the database `campus_pulse` and does not require a username or password for the local development default.

Campus Pulse automatically seeds fictional sample records on startup when the database does not already contain each demo record. The seed uses stable demo keys, so restarting the application does not create duplicates. Demo mode is enabled by default:

```properties
campus-pulse.demo-data=true
```

For a secured or hosted MongoDB instance, replace `spring.data.mongodb.uri` with your own connection string. Do not commit credentials.

## How To Run

1. Install JDK 24 and make sure `java -version` reports Java 24.
2. Start MongoDB.
3. Keep `campus-pulse.demo-data=true` for the built-in demo database, or set it to `false` for an empty development database.
4. Run the application:

```powershell
./mvnw.cmd spring-boot:run
```

5. Open `http://localhost:8080/`.

## Demo Modules

- Dashboard
- Students
- Events
- Announcements
- Mentorship
- Messages
- Smart Routing Hub
- Intelligent Student Assistant

## Assistant Queries To Demonstrate

- How many students are there?
- Show me students interested in AI.
- Who are the available mentors?
- Who can mentor me in Data Science?
- Which mentor knows Java?
- Who can help me with cybersecurity?
- What events are coming up?
- What technical workshops are available?
- Are there any hackathons?
- What are the latest announcements?
- Show me high priority announcements.
- Do we have any placement events?
- How many upcoming events are there?
- Show my messages.

## API Overview

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET/POST | `/api/students` | List/search or create students |
| PUT/DELETE | `/api/students/{id}` | Update or remove a student |
| GET/POST | `/api/events` | List/search or create events |
| GET/POST | `/api/announcements` | List/search or create announcements |
| GET/POST | `/api/mentors` | Search or create mentors |
| GET/POST | `/api/messages` | List or create campus messages |
| GET | `/api/routing?query=...` | Route a natural-language query |
| POST | `/api/assistant` | Ask the rule-based student assistant |

## Demo Database Collections

The application uses the MongoDB collections `students`, `mentors`, `events`, `announcements`, and `messages`. Students and faculty names, message conversations, event organizers, and announcement authors are fictional but internally consistent.

## Future Enhancements

- Add authentication and role-based access for students and staff
- Add mentor request workflow and message notifications
- Add calendar export and event registration
- Add an optional provider adapter for a configured AI service
- Add MongoDB indexes and pagination for production-scale data
