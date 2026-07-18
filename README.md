# 💰 Finance Management System

A full-stack **Finance Management System** developed using **Spring Boot, Java, HTML, CSS,Thymeleaf, and MySQL (XAMPP)**. The application helps users efficiently manage their personal finances by tracking income, expenses, and generating financial reports through a secure and user-friendly dashboard.

---

## 📌 Features

### 👤 User Module
- User Registration
- User Login
- Secure Authentication
- User Dashboard
- View Financial Summary

### 💵 Income Management
- Add Income
- View Income History
- Update Income
- Delete Income
- Search Income Records

### 💸 Expense Management
- Add Expense
- View Expense History
- Update Expense
- Delete Expense
- Search Expense Records

### 📊 Dashboard
- Total Income
- Total Expenses
- Current Balance
- Financial Summary
- Recent Transactions

### 🔒 Security
- Secure Login System
- Session Management
- Form Validation

---

# 🛠️ Technologies Used

## Backend
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Maven

## Frontend
- HTML5
- CSS3
- JavaScript
- Thymeleaf

## Database
- MySQL
- XAMPP

## IDE
- IntelliJ IDEA / Eclipse / Spring Tool Suite (STS)

---

# 📁 Project Structure

```
FinanceManagementSystem
│
├── model
│   ├── UserModel.java
│   ├── IncomeModel.java
│   └── ExpenseModel.java
│
├── repository
│   ├── UserRepo.java
│   ├── IncomeRepository.java
│   └── ExpenseRepository.java
│
├── resource
│   ├── UserController.java
│   ├── IncomeController.java
│   └── ExpenseController.java
|   └── ReportController
│
├── templates
│   ├── AdminDashboard.html
│   ├── AdminLogin.html
│   ├── Register.html
│   ├── Login.html
│   ├── UserDashboard.html
│   ├── Income.html
│   ├── Expense.html
│   ├── ShowIncome.html
│   └── ShowExpence.html
|   └── UpdateExpence.html
|   └── UpdateIncome.html
|   └── Reports.html
|   └── ReportResult.html
│
└── FinanceApplication.java
```

---

# 🗄️ Database

Database Name

```
finance_db
```

Main Tables

- users
- income
- expense

---

# 🚀 Installation

## 1 Clone the Repository

```bash
git clone https://github.com/yourusername/FinanceManagementSystem.git
```

---

## 2 Open Project

Open the project in

- IntelliJ IDEA
- Spring Tool Suite
- Eclipse

---

## 3 Configure Database

Create a MySQL database.

```
finance_db
```

Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 4 Run XAMPP

Start

- Apache
- MySQL

---

## 5 Run Application

Run

```
FinanceApplication.java
```

Application URL

```
http://localhost:8080
```

---

# 📸 Screens

### Login

- User Login
- Secure Authentication

### Register

- Create New Account

### User Dashboard

- Total Income
- Total Expense
- Balance

### Income

- Add Income
- View Income

### Expense

- Add Expense
- View Expense

### Admin Dashboard

- Manage Users
- Financial Overview

---

# 📈 Modules

## User Module

- Register
- Login
- Dashboard
- Profile

## Income Module

- Add Income
- Edit Income
- Delete Income
- View Income

## Expense Module

- Add Expense
- Edit Expense
- Delete Expense
- View Expense

## Admin Module

- Admin Login
- Dashboard
- View Users
- Manage Financial Records

---

# 🏗️ Project Architecture

```
User
   │
   ▼
HTML • CSS • JavaScript
   │
   ▼
Thymeleaf
   │
   ▼
Spring Boot Controllers
   │
   ▼
Spring Service Layer
   │
   ▼
Spring Data JPA Repository
   │
   ▼
MySQL Database
```

---

# 💡 Future Enhancements

- Budget Management
- Savings Tracker
- PDF Report Generation
- Excel Export
- Monthly Analytics
- Email Notifications
- Expense Categories
- Charts using Chart.js
- AWS Deployment
- Mobile Application

---

# 🎯 Learning Outcomes

This project demonstrates:

- Spring Boot Development
- MVC Architecture
- CRUD Operations
- Spring Data JPA
- MySQL Database Integration
- Thymeleaf Template Engine
- Authentication System
- Financial Data Management
- Responsive Web Design

---

# 👨‍💻 Author

**Shubham Patel**

### Technologies

- Java
- Spring Boot
- Spring MVC
- Hibernate
- MySQL
- HTML
- CSS
- JavaScript
- Thymeleaf

---

# ⭐ Support

If you found this project useful, don't forget to **⭐ Star** the repository and contribute by creating issues or pull requests.

---

---

# URL 

[localhost:8080//](http://localhost:8080/finance/)

---
