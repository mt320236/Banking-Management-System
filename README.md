# 🏦 Banking Management System

A console-based **Banking Management System** built with Java, JDBC, and MySQL — supporting account creation, secure transactions, and PIN-based authentication with full transaction integrity using commit/rollback.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

---

## ✨ Features

- 🔐 User registration & login authentication
- 🏦 Open a new bank account
- 💰 Credit money to an account
- 💸 Debit money from an account
- 🔁 Transfer money between accounts
- 📊 Check account balance
- 🔢 Security PIN verification
- 🗄️ MySQL database integration
- ⚙️ Transaction management using **commit & rollback**

---

## 🛠️ Technologies Used

| Category | Stack |
|---|---|
| Language | Java |
| Database Connectivity | JDBC |
| Database | MySQL |
| Paradigm | Object-Oriented Programming (OOP) |

---

## 📂 Project Structure

```
Banking-Management-System/
│
├── BankingApp.java        # Entry point — handles user interaction
├── User.java              # User entity (registration, login)
├── Accounts.java          # Account entity (balance, PIN, account no.)
└── AccountManager.java    # Core business logic — credit, debit, transfer
```

---

## 🗄️ Database Schema

### `User` Table

| Column | Description |
|---|---|
| Full Name | User's full name |
| Email | Used for login |
| Password | Authentication credential |

### `Accounts` Table

| Column | Description |
|---|---|
| Account Number | Unique account identifier |
| Full Name | Account holder's name |
| Email | Linked user email |
| Balance | Current account balance |
| Security PIN | Required for transactions |

---

## 🧠 Key Concepts Implemented

- JDBC connectivity (`Connection`, `DriverManager`)
- `PreparedStatement` for safe, parameterized SQL queries
- `ResultSet` for reading query results
- SQL CRUD operations (Create, Read, Update, Delete)
- Exception handling for robust error management
- **Transaction management** — ensuring atomicity with commit/rollback during transfers
- Authentication & input validation

---

## 🚀 How to Run

**1. Clone the repository**
```bash
git clone https://github.com/mt320236/Banking-Management-System.git
cd Banking-Management-System
```

**2. Create the MySQL database**
```sql
CREATE DATABASE banking_system;
```

**3. Update database credentials** in `BankingApp.java`
```java
private static String url = "jdbc:mysql://127.0.0.1:3306/banking_system";
private static String username = "your_username";
private static String password = "your_password";
```

**4. Compile and run**
```bash
javac *.java
java BankingApp
```

---

## 🧪 Sample Operations

| Operation | Description |
|---|---|
| Register User | Create a new user with name, email & password |
| Login User | Authenticate using email & password |
| Create Account | Open a new bank account linked to the user |
| Credit Money | Add funds to an account |
| Debit Money | Withdraw funds from an account |
| Transfer Money | Move funds between two accounts (atomic transaction) |
| Check Balance | View current account balance |

---

## 🔮 Future Improvements

- Add interest calculation for savings accounts
- Generate transaction history / mini statements
- Add a GUI using JavaFX or Swing
- Migrate to Spring Boot with REST APIs

---

## 👤 Author

**Ashish Tiwari**
[GitHub](https://github.com/mt320236) · [LinkedIn](https://linkedin.com/in/ashish-tiwari-49056238b)
