package com.jdbc.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Account_Operation 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		 // connection only once
        Connection con = ConnectionProvider.getConnection();

        if (con == null) {
            System.out.println("❌ Failed to establish DB connection!");
            return;
        }
		
		AccountOperations accOperation = new AccountOperations(sc);
		
		while(true)
		{
			    System.out.println("\u001B[31m"+"\n--- Banking System Menu ---");
	            System.out.println("\u001B[32m"+"Press 1 → Insert New Account");
	            System.out.println("\u001B[33m"+"Press 2 → View All Accounts");
	            System.out.println("\u001B[34m"+"Press 3 → Deposit & Withdraw(Transaction)");
	            System.out.println("\u001B[35m"+"Press 4 → Exit");
	            
	            System.out.print("Enter your choice: ");
	            int choice = sc.nextInt(); sc.nextLine();
	            
	            switch(choice)
	            {
	            case 1:
	            	// Insert New Account
                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.next(); sc.nextLine();
                    
                    System.out.print("Enter Your Initial Balance: ");
                    double balance = sc.nextDouble();
                    sc.nextLine();
                    
                    Account acc = new Account(0, name, balance);
                    if (accOperation.addAccountDetails(acc)) 
                    {
                        System.out.println("Account Inserted Successfully!");
                    } else {
                        System.out.println("Failed to Insert Account.");
                    }
                    break;	
	            	
	            case 2:
	            	accOperation.viewAccountDetails();
                    break;
	            	
	            case 3:
	            	// Transaction (deposit + withdraw)
	            	System.out.print("Enter Account Number: ");
	            	long accNumForTx=sc.nextLong();
	            	Account txAccount= new Account(accNumForTx, null, 0);
	            	accOperation.transactionOperation(txAccount);
	            	break;
	            	
                case 4:
                	 System.out.println("Exiting Application...");
                	    sc.close();
                	    try {
                	        ConnectionProvider.getConnection().close(); // close connection finally
                	    } catch (SQLException e) {
                	        e.printStackTrace();
                	    }
                	    System.exit(0);
                    
                default:
                    System.out.println("Invalid Choice!");
	            }	
		}
	}
}
