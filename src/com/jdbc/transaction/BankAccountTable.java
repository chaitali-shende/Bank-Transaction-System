package com.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BankAccountTable 
{
	public static void main(String[] args)
	{
		try (
				Connection con =  ConnectionProvider.getConnection();
				Statement stmt =  con.createStatement();
				)
		   {	
			String createTable = 	
					"""
					CREATE TABLE Bank_Account
					(
					acc_no Number(10) PRIMARY KEY,
				    acc_holder VARCHAR2(50) NOT NULL,
				    balance NUMBER(12,2) DEFAULT 0
				    )				
					""" ;
			
			String createSequence=
					"""
					CREATE SEQUENCE account_seq 
					START WITH 1001 
					INCREMENT BY 1 
					NOCACHE
					""";
		   
				 //create Bank_Account table
		           stmt.executeUpdate(createTable);
		           System.out.println("Table Bank_Account created successfully.");
		                
		         // Create sequence
		            stmt.executeUpdate(createSequence);
		            System.out.println("Sequence 'account_seq' created successfully");
		   }         
				 catch (SQLException e) 
				     {
		                if (e.getErrorCode()==955)  //(already exists) 
		                {
		                    System.out.println("Table/Sequence already exists.");
		                } else {
		                    System.err.println("Error in creating table/sequence: "+e.getMessage());
		                    e.printStackTrace();
		                }
		             }
	}
}

