package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.objects.Account;
import com.revature.objects.Member;

public interface Project0DAO {
	public Account getAccount(String s, Member user) throws SQLException;
	public Member getUser(String s) throws SQLException;
	public boolean insertAccount(Member user, int type) throws SQLException;
	
	public List<Member> getNewMembers() throws SQLException;
	public boolean approveUser(int id) throws SQLException;
	
	public boolean insertMember(String fName, String lName, String uName, String password) throws SQLException;
	
	public double getBalance(Account act) throws SQLException;
	
	public double deposit(Account act, double i) throws SQLException;
	public double withdraw(Account act, double i) throws SQLException;
	
	public List<Account> getAccounts(int i) throws SQLException;
	
	public boolean insertTransfer(Account from, Account to, double i) throws SQLException;
	
	
	// Widthdraw and deposit both take an int for the m_id and a value
	
}
