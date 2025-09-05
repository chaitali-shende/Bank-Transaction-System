# 💳 Bank Transaction System (Java + JDBC + Oracle)

A simple **Banking System** project built with **Java (JDBC)** and **Oracle Database**.  
This project demonstrates **basic CRUD operations** and **transaction management** (Deposit & Withdraw) using **JDBC with transaction handling**.

---

## 🚀 Features
- Create new bank accounts (auto-generated account numbers using Oracle Sequence).
- View all existing bank accounts.
- Deposit and Withdraw as a single **transaction** (commit/rollback).
- Input validation (deposit > 0 and multiple of 100, withdrawal with sufficient balance).
- Console-based user-friendly menu.

---

## 🛠️ Tech Stack

- **Java** (Core + JDBC)  
- **Oracle Database** (SQL, Sequence, Table)  
- **JDBC Transaction Management**  
- **Eclipse IDE**  

---

## 📂 Project Structure

BankTransactionProject/

│── src/com/jdbc/transaction/

│ ├── Account.java

│ ├── AccountOperations.java

│ ├── Account_Operation.java (Main class with Menu)

│ ├── BankAccountTable.java (Table & Sequence creation)

│ ├── ConnectionProvider.java

│

│── connection.properties # DB connection details

│── README.md # Project Documentation


---

📸 Sample Output


--- Banking System Menu ---

Press 1 → Insert New Account

Press 2 → View All Accounts

Press 3 → Deposit & Withdraw (Transaction)

Press 4 → Exit



Enter your choice: 3

Enter Account Number: 1002

Enter the amount to deposit: 500

Amount Deposited Successfully!!

Enter the amount to withdraw: 200

Transaction Successful! Amount Deposited: Rs.500.0, Amount Withdrawn: Rs.200.0
