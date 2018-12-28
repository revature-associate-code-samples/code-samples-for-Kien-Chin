package com.revature.objects;

public class Transfer {
	private int tID;
	private int fID;
	private int rID;
	private double amount;
	public Transfer(int tID, int fID, int rID, double amount) {
		super();
		this.tID = tID;
		this.fID = fID;
		this.rID = rID;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transfer [tID=" + tID + ", fID=" + fID + ", rID=" + rID + ", amount=" + amount + "]";
	}
	public int gettID() {
		return tID;
	}
	public void settID(int tID) {
		this.tID = tID;
	}
	public int getfID() {
		return fID;
	}
	public void setfID(int fID) {
		this.fID = fID;
	}
	public int getrID() {
		return rID;
	}
	public void setrID(int rID) {
		this.rID = rID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
