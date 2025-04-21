# ☁️ CloudBalance – Secure User Management System

CloudBalance is a Spring Boot-based backend system that provides **secure user management** with role-based access control using **Spring Security**, **MySQL**, and **Basic Authentication**.

---

## 🚀 Features

- ✅ User Registration (Admin-only)
- 🔐 Role-based access (ADMIN, CUSTOMER, READ_ONLY)
- 📦 Passwords stored securely using BCrypt hashing
- 🛡️ Spring Security configuration with `HttpSecurity` and `DaoAuthenticationProvider`
- 🧾 RESTful API using Spring Web
- 💾 MySQL as the backend database
- 🔍 JWT for Postman & browser login
- 🔌 Ready for frontend (React/Redux) integration

---

## 🧱 Tech Stack

| Layer        | Tech                        |
|--------------|-----------------------------|
| Backend      | Spring Boot                 |
| Frontend     | REACT                       |
| Security     | Spring Security             |
| DB           | MySQL                       |
| Auth Type    | JWT                         |
| Build Tools  | GRADLE & NPM                |
| IDE          | IntelliJ IDEA & VSCode      |

---

## 🧑‍💻 User Roles

| Role       | Description                          |
|------------|--------------------------------------|
| `ADMIN`    | Can create/update/list users         |
| `CUSTOMER` | Access user-specific functionality   |
| `READ_ONLY`| Limited, view-only access            |

---
