# 🏋️ Gym Application - Backend API

This is a Spring Boot application for managing gym members, their profiles, health data, workout plans, and messaging. It features **robust exception handling**, **secure authentication using JWT**, and **clean REST API design**.

---

## 🌟 Key Features

- 🔐 User registration, login, logout
- 📝 Personal + health profile management
- 💪 Workout plan + exercise tracking
- 📬 In-app messaging system
- 🌍 Location master data (country, state, city)
- 📊 Admin reporting endpoints (filter by date)
- ⚠️ Centralized exception handling

---

## ⚠️ Exception Handling

All APIs implement consistent exception management using:

- **`BaseException`**: Custom business exceptions (e.g., validation errors, authorization issues)  
- **`SystemException`**: Covers unexpected system errors  
- Centralized handling in `BaseController` ensures:
  - Clear error codes + messages
  - Standard error response format
  - HTTP status mapped to error type (400, 401, 404, 500)

Sample error response:
```json
{
  "timestamp": "2025-06-10T12:34:56",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid login ID provided",
  "path": "/api/v1/user-login"
}
```
## 🔐 JWT Authentication Flow
Flow:
- POST /api/v1/user-sign-up → register user
- POST /api/v1/user-login → obtain JWT token

👉 All secured endpoints require:
```
Authorization: Bearer <JWT token>
```

The diagram below illustrates how JWT (JSON Web Token) authentication is implemented in this application:

- 1️⃣ User submits credentials via the login API (/api/v1/user-login).
- 2️⃣ The server validates credentials against stored data.
- 3️⃣ If valid, the server generates a JWT token signed with a secret key and returns it to the client.
- 4️⃣ The client includes the token in the Authorization header (Bearer <token>) for all protected API requests.
- 5️⃣ The server verifies the JWT on each request:
   - Checks the signature
   - Confirms token expiration
   - Validates claims (e.g., user roles, permissions)
- 6️⃣ If valid, the request proceeds; if not, the server returns an appropriate error (e.g., 401 Unauthorized).

👉 This approach ensures stateless, secure communication between client and server, without maintaining server-side session data.

![Image](https://github.com/user-attachments/assets/c1941c3f-b354-45a8-be72-2c7a02a89892)


## 📂 API Endpoints

### 🧑 User Management

| Method | Endpoint                                       | Purpose                                            |
| ------ | ---------------------------------------------- | -------------------------------------------------- |
| POST   | `/api/v1/user-sign-up`                         | Register a new user                                |
| POST   | `/api/v1/user-login`                           | Login + receive JWT                                |
| POST   | `/api/v1/user-logout`                          | Invalidate JWT session                             |
| POST   | `/api/v1/user-account-profile`                 | Save personal profile                              |
| PUT    | `/api/v1/user-account-profile`                 | Update personal profile                            |
| GET    | `/api/v1/user-account-profile?loginId=`        | Get profile by login ID                            |
| POST   | `/api/v1/user-account-profile-retrieve`        | Retrieve profile via request body (criteria-based) |
| POST   | `/api/v1/user-account-health-profile`          | Save health profile                                |
| PUT    | `/api/v1/user-account-health-profile`          | Update health profile                              |
| GET    | `/api/v1/user-account-health-profile?loginId=` | Get health profile by login ID                     |
| POST   | `/api/v1/user-health-profile-retrieve`         | Retrieve health profile via request body           |
| DELETE | `/api/v1/user-account-details-delete?loginId=` | Delete account by login ID                         |
| POST   | `/api/v1/user-forgot-password`                 | Trigger password reset                             |
| POST   | `/api/v1/user-forgot-loginid`                  | Recover forgotten login ID                         |
| POST   | `/api/v1/user-update-password`                 | Update user password                               |
| POST   | `/api/v1/membership-plan`                      | Select membership plan                             |


### 🏋️ Workout + Exercise

| Method | Endpoint                                       | Purpose                    |
| ------ | ---------------------------------------------- | -------------------------- |
| POST   | `/api/v1/workout-details-by-login-id?loginId=` | Get workouts for a user    |
| GET    | `/api/v1/exercise-details-by-id?id=`           | Get exercise details by ID |


### 📊 Admin Reporting

| Method | Endpoint                                          | Purpose                                    |
| ------ | ------------------------------------------------- | ------------------------------------------ |
| GET    | `/api/v1/all-user-details`                        | All user personal details                  |
| GET    | `/api/v1/all-user-personal-details-by-date?date=` | User personal details after a date         |
| GET    | `/api/v1/all-user-account-details-by-date?date=`  | User accounts created/updated after a date |
| GET    | `/api/v1/all-health-details-by-date?date=`        | Health profiles updated after a date       |

### 🌍 Location APIs

| Method | Endpoint                            | Purpose               |
| ------ | ----------------------------------- | --------------------- |
| GET    | `/api/v1/location/country`          | Get country list      |
| GET    | `/api/v1/location/state?countryId=` | Get states by country |
| GET    | `/api/v1/location/city?stateId=`    | Get cities by state   |

### 💬 Message Center

| Method | Endpoint                                     | Purpose                           |
| ------ | -------------------------------------------- | --------------------------------- |
| POST   | `/api/v1/message/send`                       | Send message                      |
| POST   | `/api/v1/message/by-loginid-status`          | Get messages by login ID + status |
| POST   | `/api/v1/message/by-loginid-metadata-read`   | Get read messages                 |
| POST   | `/api/v1/message/by-loginid-metadata-unread` | Get unread messages               |
| PUT    | `/api/v1/message/send`                       | Update message content            |
| PUT    | `/api/v1/message/update-metadata`            | Update message metadata           |
| GET    | `/api/v1/message/all-by-date?date=`          | Get all messages since date       |

## 📝 Example Requests

### User Sign Up
```
POST /api/v1/user-sign-up
Content-Type: application/json

{
  "loginId": "jane.doe",
  "password": "StrongPass@123",
  "email": "jane@example.com"
}
```

### Get User Account Profile
```
GET /api/v1/user-account-profile?loginId=jane.doe
Authorization: Bearer <JWT>
```
### Choose Membership Plan
```
POST /api/v1/membership-plan
Authorization: Bearer <JWT>
Content-Type: application/json

{
  "loginId": "jane.doe",
  "planId": 2
}
```
## 🛠 Setup & Run
### Build + Run
```
git clone https://github.com/your-org/gym-app.git
cd gym-app
mvn clean install
mvn spring-boot:run
```
### Configuration
Update application.properties:

- DB connection (H2, MySQL, PostgreSQL)
- JWT secret + expiry
- Email / messaging service configs

### Liquibase
Runs DB migrations automatically on startup.

## ⚙️ Database Entities

- ✅ users
- ✅ user_account
- ✅ user_health_details
- ✅ message_center
- ✅ user_workout_plan, workout, exercise
- ✅ country, state, city

## 🚀 Roadmap

- 📱 Mobile app integration
- 📈 Workout analytics dashboard
- 🔔 Notification system
- 🕒 Scheduled tasks for reminders

## 🤝 Contributing

- 1️⃣ Fork repo
- 2️⃣ Create branch feature/xyz
- 3️⃣ Submit PR with description

# 🏅 Badge Assignment System

## 📘 Overview

The **Badge Assignment System** is a gamification feature within the Gym App designed to enhance user engagement, motivation, and long-term retention. Users are rewarded with digital badges for completing specific actions or reaching fitness milestones.

This system follows the **Strategy Design Pattern**, making it easy to scale and maintain by decoupling the badge evaluation logic from the core business processes.

---

## 🎯 Purpose & Goals

The primary goals of the Badge Assignment System are:

- 🔁 **Encourage consistent activity** like daily or weekly workouts
- 🧩 **Reward profile completeness** to improve user data accuracy
- 🔥 **Motivate streak maintenance** for long-term commitment
- 🏋️ **Acknowledge training efforts**, such as finishing a workout
- 🎉 **Celebrate fitness milestones**, e.g., weight goals, workout count

---

## ⚙️ How It Works

When a user performs a tracked activity, the system evaluates whether any badges should be awarded. This evaluation is dynamic, and the logic for each badge is implemented using a modular strategy-based approach.

### Tracked Activities Include:

- ✅ Completing personal and health details
- 🏃 Completing a workout session
- 📆 Maintaining a streak of daily or weekly activity
- 🥇 Reaching total workout goals (e.g., 10 workouts, 50 workouts)
- 📉 Hitting target weight or health benchmarks
- 🎯 Completing challenges (like “7-Day Challenge” or “30-Day Plan”)

Each badge has a corresponding rule set (or "criteria"), and when a user qualifies, the badge is automatically assigned.

---

## 🧠 Strategy Pattern Design

The badge system uses the **Strategy Pattern** to evaluate eligibility:

- Every badge is associated with a **criteria key**.
- A matching **evaluation strategy** checks if a user meets the criteria.
- If eligible, the badge is awarded and stored.

This pattern provides flexibility and keeps the badge logic clean, allowing developers to add new badge types without altering existing functionality.

---

## 🔄 When Badge Evaluation Happens

Badges are evaluated during or after key user actions, including but not limited to:

- After a **workout completion**
- After **updating profile information**
- When **logging in** (to check streaks or progress)
- On **scheduled background jobs** (for time-based achievements)

---

## 🚀 Benefits and Impact

### ✅ Benefits:
- Increases user motivation and retention
- Encourages goal-setting and progress tracking
- Adds a social/reward-based dimension to fitness tracking
- Provides visible recognition for effort and achievements

### 🔮 Future Scalability:
- Easily extendable to new types of badges (e.g., community involvement, trainer feedback, app usage)
- Can integrate with notifications to alert users about newly earned badges
- Allows tracking badge history and building leaderboards or achievement pages

---

## 📌 Conclusion

The Badge Assignment System is a key engagement feature in the Gym App that brings structure and scalability to user rewards. By applying the Strategy Pattern, we ensure that the logic is modular and easily maintainable, while supporting a wide range of user achievements. This ultimately helps users stay committed to their fitness journey in a fun and interactive way.





