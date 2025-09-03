package com.jdbc.transaction;

public class Account 
{
	private long account_Number;
	private String account_HolderName;
	private double account_balance;
	
	public Account(long account_Number, String account_HolderName, double account_balance) 
	{
		super();
		this.account_Number = account_Number;
		this.account_HolderName = account_HolderName;
		this.account_balance = account_balance;
	}

	public long getAccount_Number() {
		return account_Number;
	}

	public void setAccount_Number(long account_Number) {
		this.account_Number = account_Number;
	}

	public String getAccount_HolderName() {
		return account_HolderName;
	}

	public void setAccount_HolderName(String account_HolderName) {
		this.account_HolderName = account_HolderName;
	}

	public double getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}

	@Override
	public String toString() 
	{
		return "Account [account_Number=" + account_Number + ", account_HolderName=" + account_HolderName
				+ ", account_balance=" + account_balance + "]";
	}
}
