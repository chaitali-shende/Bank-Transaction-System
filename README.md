# 💳 Bank Transaction System (Java + JDBC + Oracle)

A simple **Banking System** project built with **Java (JDBC)** and **Oracle Database**.  
This project demonstrates **basic CRUD operations** and **transaction management** (Deposit & Withdraw) using **JDBC with transaction handling**.
It’s a great hands-on example of integrating Java with Oracle for real-world database applications

---

## 🚀 key Features

✅ Create Accounts — Insert new bank accounts with auto-generated account numbers (via Oracle Sequence).

✅ View Accounts — Display all existing bank accounts in a neatly formatted list.

✅ Deposit & Withdraw (Atomic Transaction) — Perform deposit and withdrawal together as a single transaction with commit/rollback handling to maintain data integrity.

✅ Input Validation — Ensures deposit amount > 0 & multiples of 100, checks sufficient balance before withdrawal.

✅ User-Friendly Console Menu — Intuitive menu-driven interface for seamless interaction.

---

## 🛠️ Tech Stack

- **Java** (Core + JDBC) — For application logic and DB connectivity 
- **Oracle Database** (SQL, Sequence, Table) - For persistent storage, sequences and tables  
- **JDBC Transaction Management** — Handling commit & rollback 
- **Eclipse IDE** — For development and testing


---

## 📂 Project Structure

BankTransactionProject/

│── src/com/jdbc/transaction/

│ ├── Account.java                                              # Model class for account entity

│ ├── AccountOperations.java                                    # CRUD & transaction operations

│ ├── Account_Operation.java                                    # Main class with Menu-driven UI

│ ├── BankAccountTable.java                                     # Table & Sequence creation script

│ ├── ConnectionProvider.java                                   # JDBC connection provider

│

│── connection.properties # DB connection details              # DB connection details
 
│── README.md # Project Documentation                            # Project documentation


---

📝 How It Works

Insert New Account

User enters account details.

Account number auto-generated via Oracle sequence.

View All Accounts

Fetches and displays all records from the Oracle table.

Deposit & Withdraw (Transaction)

Takes deposit and withdrawal input.

Executes both as one transaction.

Rolls back if any step fails, ensuring consistency.

Exit

Safely closes the application and database connection.

---

**📸 Sample Output**


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

---

**🧑‍💻 Skills Demonstrated**

Java fundamentals and OOP design

JDBC setup and connection pooling

SQL DDL/DML with Oracle (Tables, Sequences)

Transaction handling with commit & rollback

Input validation and error handling

Console UI design

---

**🚧 Future Enhancements**

Add user authentication & roles (Admin/User).

Provide GUI using JavaFX or Swing.

Add interest calculation and statement generation.

Integrate with web layer using Spring Boot & REST APIs.
