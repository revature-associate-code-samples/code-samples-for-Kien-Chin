package com.revature.objects;

public class Account {
	private int aID;
	private int uID;
	private int type;
	private double balance;
	

	@Override
	public String toString() {
		return "Account [aID=" + aID + ", uID=" + uID + ", type=" + type + ", balance=" + balance + "]";
	}

	public Account(int accountID, int userID, int accountType, double accountBalance) {
		aID = accountID;
		uID = userID;
		type = accountType;
		balance = accountBalance;
	}
	

	public int getaID() {
		return aID;
	}

	public void setaID(int aID) {
		this.aID = aID;
	}

	public int getuID() {
		return uID;
	}

	public void setuID(int uID) {
		this.uID = uID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
