# 🚀 Study Planner API — Complete Run & Test Guide

A simple, clean, and clear guide to running and testing the project.

---

## 📌 Project Overview

- **Language:** Java 21+ (Java 26 detected)
- **Framework:** Spring Boot 3.5.16
- **Database:** Embedded H2 Database (saved automatically in `./data/study-planner-api`, no MySQL or XAMPP needed)
- **Port:** `http://localhost:8080`
- **H2 Console:** `http://localhost:8080/h2-console`

---

## ⚡ 1. How to Run the Project (Without PowerShell)

You don't need to type commands in PowerShell. Here are **4 graphical 1-click ways** to run it directly inside Antigravity:

### Option A: Press `F5` or Use "Run and Debug" (1-Click)
1. On the left sidebar of Antigravity, click the **Run and Debug** icon (or press <kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>D</kbd>).
2. At the top of the panel, you will see **▶ Run Study Planner API**.
3. Click the green **Play (▶)** button (or just press <kbd>F5</kbd> on your keyboard).
4. Antigravity will launch the Spring Boot app automatically.

### Option B: Click "Run" Above the Main Method in Java Code
1. In the file explorer on the left, open:  
   `src/main/java/com/alimi/studyplanner/StudyPlannerApplication.java`
2. Right above the line `public static void main(String[] args)`, click the small **Run** link or the green Play icon in the left gutter.

### Option C: Use Antigravity Task Runner (`Ctrl + Shift + B`)
1. Press <kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>B</kbd> anywhere in Antigravity (or go to menu **Terminal** → **Run Task...** → **Start Study Planner**).
2. The project will start up in a dedicated panel without needing manual typing.

### Option D: Just Ask Antigravity AI in Chat!
- You can simply type in this chat window:  
  > *"Run the project"* or *"Start the backend server"*  
- Antigravity will start it, monitor its health, and tell you when it is ready.

---

## 📮 2. How to Test Using Postman

You have two easy ways to test with Postman:

### Method 1: Instant Import (Easiest — 1 Click)

A ready-made Postman collection has already been generated for you:
`Study_Planner_API.postman_collection.json`

1. Open **Postman**.
2. Click the **Import** button (top-left in Postman).
3. Drag and drop the file `Study_Planner_API.postman_collection.json` from your project folder into Postman.
4. You will now see a collection named **Study Planner API** with all 9 requests pre-configured!
5. Just click on any request and hit **Send**.

---

### Method 2: Manual Request Setup in Postman

If you want to create requests manually in Postman:

#### 1️⃣ Create a Task (POST)
- **Method:** `POST`
- **URL:** `http://localhost:8080/api/tasks`
- **Headers:**
  - Key: `Content-Type` | Value: `application/json`
- **Body:** Select **raw**, then choose **JSON** from the dropdown:
  ```json
  {
    "title": "Study Spring Boot",
    "subject": "Java",
    "description": "Learn REST APIs and test endpoints",
    "priority": "HIGH",
    "status": "IN_PROGRESS",
    "dueDate": "2026-10-15"
  }
  ```
- Click **Send**.
- **Expected Status:** `201 Created`

#### 2️⃣ Get All Tasks (GET)
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/tasks`
- Click **Send**.
- **Expected Status:** `200 OK`

#### 3️⃣ Filter Tasks (GET)
- **Method:** `GET`
- **URL with query params:**
  - By status: `http://localhost:8080/api/tasks?status=IN_PROGRESS`
  - By priority: `http://localhost:8080/api/tasks?priority=HIGH`
  - By subject: `http://localhost:8080/api/tasks?subject=Java`
  - Combined: `http://localhost:8080/api/tasks?priority=HIGH&subject=Java`

#### 4️⃣ Get Task Summary (GET)
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/tasks/summary`
- Click **Send**.
- Returns total, active, completed, and overdue task counts.

#### 5️⃣ Mark Task as Completed (PATCH)
- **Method:** `PATCH`
- **URL:** `http://localhost:8080/api/tasks/1/complete`
- Click **Send**.
- **Expected Status:** `200 OK` (status changes to `COMPLETED`)

#### 6️⃣ Update a Task (PUT)
- **Method:** `PUT`
- **URL:** `http://localhost:8080/api/tasks/1`
- **Headers:** `Content-Type: application/json`
- **Body (raw JSON):**
  ```json
  {
    "title": "Master Spring Boot 3",
    "subject": "Java",
    "description": "Updated description",
    "priority": "HIGH",
    "status": "COMPLETED",
    "dueDate": "2026-10-20"
  }
  ```
- Click **Send**.

#### 7️⃣ Delete a Task (DELETE)
- **Method:** `DELETE`
- **URL:** `http://localhost:8080/api/tasks/1`
- Click **Send**.
- **Expected Status:** `204 No Content`

---

## 🤖 3. How to Run and Test Inside Antigravity

Antigravity gives you 3 built-in ways to work with this project:

### 1. Automated Tests via Built-in Terminal
Press <kbd>Ctrl</kbd> + <kbd>`</kbd> and run:
```powershell
.\mvnw.cmd test
```
Maven will compile and execute all tests and show `BUILD SUCCESS`.

### 2. Testing Directly inside Antigravity using `requests.http`
We created a file named `requests.http` in your workspace.
1. Open [requests.http](requests.http) in Antigravity.
2. If you have the REST Client extension installed, you will see a clickable **Send Request** link above each HTTP call.
3. You can click **Send Request** and the live JSON response will display right next to your code in a split tab!

### 3. Ask Antigravity AI
At any point, you can ask in the chat:
- *"Run the project for me"*
- *"Run tests and check for errors"*
- *"Send a POST request to add a new task for Chemistry"*
- *"Check what's causing port 8080 error"*

Antigravity can run commands, inspect logs, and fix code for you automatically.

---

## 🔍 4. Visual Database Inspection (H2 Web Console)

You can view the tables and rows in your browser:

1. Open your browser: **http://localhost:8080/h2-console**
2. Fill in:
   - **JDBC URL:** `jdbc:h2:file:./data/study-planner-api;AUTO_SERVER=TRUE`
   - **User Name:** `sa`
   - **Password:** *(leave empty)*
3. Click **Connect**.
4. Click on `TASKS` on the left panel, or run:
   ```sql
   SELECT * FROM TASKS;
   ```

---

## 🛠️ Troubleshooting Cheat Sheet

| Issue | Cause | Quick Fix |
| :--- | :--- | :--- |
| **Port 8080 already in use** | A previous instance is still running | In PowerShell run: <br>`Get-Process -Id (Get-NetTCPConnection -LocalPort 8080).OwningProcess \| Stop-Process -Force` |
| **`JAVA_HOME` error** | Incorrect Java path | `mvnw.cmd` automatically detects your JDK. If needed, run: <br>`$env:JAVA_HOME = 'C:\Program Files\Java\jdk-26'` |
| **`Connection refused` on `localhost:8080`** | Server is not running | Run `.\mvnw.cmd spring-boot:run` and verify that Tomcat has started. |
| **`400 Bad Request` in POST** | Invalid priority/status or past date | Check that `priority` is `LOW`/`MEDIUM`/`HIGH` and `dueDate` is not in the past. |
