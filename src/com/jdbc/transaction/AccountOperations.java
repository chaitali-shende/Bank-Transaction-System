package com.jdbc.transaction;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountOperations 
{
	private Scanner sc;

    public AccountOperations(Scanner sc) 
    {
        this.sc = sc;  // re-use scanner passed from main
    }
    
   // Adding new account(acc_no from sequence)
   public boolean addAccountDetails(Account account)
	{ 
	  String insertQuery = 
				"INSERT INTO Bank_Account(acc_no,acc_holder, balance)\n" +
		"VALUES(account_seq.nextVal,?,?)";
	   try( 
			   Connection con = ConnectionProvider.getConnection();
		       PreparedStatement pstmt = con.prepareStatement(insertQuery);
			   
		   )
	   {	   
		   pstmt.setString(1,account.getAccount_HolderName());
		   pstmt.setDouble(2,account.getAccount_balance());
		   
		   int  result=pstmt.executeUpdate();
		   if(result>0)
		   {
		   System.out.println("Account inserted successfully!!");
		   return true;
		   }
	   }
	   catch(Exception e)
	   {
		   System.err.println("Something went wrong, Failed to insert: "+e.getMessage());
	   }
	   return false;
	}
   
   //View all accounts
   public List<Account> viewAccountDetails() 
   {
	   List<Account> accounts = new ArrayList<>();
       String selectQuery = "SELECT * FROM Bank_Account";
       
       try (Connection con = ConnectionProvider.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(selectQuery)
           )
       {
           System.out.println("\nAcc_No\tHolder_Name\tBalance");
           System.out.println("-----------------------------------");
           
           while (rs.next())
           {
        	   long accNo=rs.getLong("acc_no");
        	   String accHolder=rs.getString("acc_holder");
        	   double balance=rs.getDouble("balance");
        	   
               accounts.add(new Account(accNo,accHolder,balance));
            // Print details
               System.out.printf("%d\t%s\t%.2f%n", accNo, accHolder, balance);
           }
           if (accounts.isEmpty()) {
               System.out.println("No accounts found!");
           }
      }
       catch (SQLException e) 
       {
           System.out.println("X:Error founded in retrieving accounts " + e.getMessage());
           e.printStackTrace();
       }
       return accounts;
   }

   // Deposit & Withdraw as Transaction
   public void transactionOperation(Account account) 
   {   
       try(Connection con = ConnectionProvider.getConnection())  
       {
           con.setAutoCommit(false); // start transaction

           // --- Deposit ---
           System.out.print("Enter the amount to deposit: ");
           double depositAmount = sc.nextDouble();
           
         // Validation for deposit amount
           if  (depositAmount <= 0 || depositAmount % 100 != 0) {
               System.out.println("Deposit Failed! Amount must be greater than 0 and in multiples of 100.");
               return;
           }
           
           String depositQuery = "UPDATE Bank_Account SET balance = balance + ? WHERE acc_no = ?";
           
           
           try (PreparedStatement deposit = con.prepareStatement(depositQuery))
            {
        	   deposit.setDouble(1, depositAmount);
        	   deposit.setLong(2, account.getAccount_Number());

               int depositResult = deposit.executeUpdate();
               if (depositResult > 0) 
               {
                   System.out.println("Amount Deposited Successfully!!");
               } 
               else
               {
                   System.out.println("Invalid Account!");
                   con.rollback();
                   return;
               }
               
               // --- Withdraw ---
               System.out.print("Enter the amount to withdraw: ");
               double withdrawAmount = sc.nextDouble();
               
               // Check current balance before withdrawal
               double currentBalance = 0;

               String balanceQuery = "SELECT balance FROM Bank_Account WHERE acc_no=?";
               
               try (PreparedStatement balanceStmt = con.prepareStatement(balanceQuery)) 
               {
            	   balanceStmt.setLong(1, account.getAccount_Number());
            	   
            	   try(ResultSet rs = balanceStmt.executeQuery())
            	   {
                     if (rs.next()) 
                     {
                        currentBalance = rs.getDouble("balance");
                     }else {
                    	 System.out.println("Account Not Found!!");
                    	 con.rollback(); // rollback deposit
                    	 return;
                     }
            	   }
               }
               if(currentBalance < withdrawAmount)
               {
                   System.out.println("Insufficient Balance!");
                   con.rollback();
                   System.out.println("Transaction Rolled Back");
                   return;
               }
            
         
                           String withdrawQuery = "UPDATE Bank_Account SET balance = balance - ? WHERE acc_no=?";
                           try (PreparedStatement withdrawStmt = con.prepareStatement(withdrawQuery)) 
                           {
                        	   withdrawStmt.setDouble(1, withdrawAmount);
                        	   withdrawStmt.setLong(2, account.getAccount_Number());
                      
                        	   int withdrawResult = withdrawStmt.executeUpdate();
                        	   
                           }
                               // Commit if both operations succeed
                                 con.commit(); //Transaction Committed
                                 System.out.println("Transaction Successful! Amount Deposited: Rs."+depositAmount+
                                		 ", Amount Withdrawn: Rs."+withdrawAmount);
            }
           catch(SQLException e)
           {
               try {
                   if (con != null) con.rollback();
                   System.out.println("Transaction Rolled Back due to Error!");
               } catch (SQLException ex) {
                   ex.printStackTrace();
               }
               e.printStackTrace();
           }
           
           
       }
           catch(Exception e)
           {
        	   e.printStackTrace();
           }
       
   }//method close
}  //class closed
