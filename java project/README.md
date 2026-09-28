# Study Planner API

A beginner-to-intermediate Java project for a resume. This REST API lets a student create study tasks, set priorities and deadlines, track completion, filter tasks, and see a progress summary.

## Tech stack

- Java 21
- Spring Boot 3.5
- Spring Web, Spring Data JPA, and Bean Validation
- H2 embedded database
- Maven

H2 is stored in the project's local `data` folder when the app runs. It uses a dedicated `study-planner-api` database file with H2 multi-process support, so an older locked database file cannot prevent the app from starting. **You do not need MySQL, XAMPP, or a separate database installation.**

## Run it

1. Open PowerShell in this folder.
2. Run the following command. The included `mvnw.cmd` downloads Maven into this project only on the first run; it does **not** install or configure Maven on Windows.

```powershell
.\mvnw.cmd spring-boot:run
```

3. The first run needs an internet connection to download Maven and Spring libraries.
4. The API is available at `http://localhost:8080`.

You can also open [pom.xml](pom.xml) in IntelliJ IDEA and run `StudyPlannerApplication` directly.

## API endpoints

| Method | Endpoint | Purpose |
| --- | --- | --- |
| `POST` | `/api/tasks` | Create a task |
| `GET` | `/api/tasks` | List tasks; optionally filter by `status`, `priority`, or `subject` |
| `GET` | `/api/tasks/{id}` | Get one task |
| `PUT` | `/api/tasks/{id}` | Replace a task's details |
| `PATCH` | `/api/tasks/{id}/complete` | Mark a task as completed |
| `DELETE` | `/api/tasks/{id}` | Delete a task |
| `GET` | `/api/tasks/summary` | View total, active, completed, and overdue task counts |

### Create a task

Send this request from Postman or IntelliJ's HTTP client:

```http
POST http://localhost:8080/api/tasks
Content-Type: application/json

{
  "title": "Finish Spring Boot controller",
  "subject": "Java",
  "description": "Create and test the task endpoints",
  "priority": "HIGH",
  "status": "IN_PROGRESS",
  "dueDate": "2026-10-05"
}
```

Valid priority values are `LOW`, `MEDIUM`, and `HIGH`. Valid statuses are `TODO`, `IN_PROGRESS`, and `COMPLETED`.

Example filters:

```text
GET http://localhost:8080/api/tasks?status=TODO
GET http://localhost:8080/api/tasks?priority=HIGH&subject=java
GET http://localhost:8080/api/tasks/summary
```

## H2 database console

After starting the app, visit `http://localhost:8080/h2-console`.

Use these values:

```text
JDBC URL: jdbc:h2:file:./data/study-planner-api;AUTO_SERVER=TRUE
User Name: sa
Password: [leave empty]
```

## Resume description

> Developed a Study Planner REST API using Java and Spring Boot. Implemented validated CRUD endpoints, task filtering, deadline tracking, global exception handling, and local persistent storage using Spring Data JPA with an embedded H2 database.

## Good next improvements

1. Add Spring Security with user accounts.
2. Add a React or HTML/CSS frontend.
3. Add pagination and automated controller tests.
4. Deploy the API and add a link to your GitHub README.
"# Study-Planner-Task-Manager-REST-API-using-Spring-Boot." 
