package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.objects.Account;
import com.revature.objects.Member;

public class Project0DAOImplementation implements Project0DAO{

	@Override
	public Account getAccount(String s, Member user) throws SQLException{
		Connection conn = ConnectionHelper.getConnection();

		String sql = "select * from accounts where a_id = " + s + "and m_id = " + user.getmID();
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		Account curr = null;
		while(rs.next()){
			curr = new Account(rs.getInt("a_id"),
					rs.getInt("m_id"),
					rs.getInt("a_type"),
					rs.getDouble("balance"));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return curr;
	}
	
	public Account getAccount(String s) throws SQLException{
		Connection conn = ConnectionHelper.getConnection();

		String sql = "select * from accounts where a_id = " + s;
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		Account curr = null;
		while(rs.next()){
			curr = new Account(rs.getInt("a_id"),
					rs.getInt("m_id"),
					rs.getInt("a_type"),
					rs.getDouble("balance"));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return curr;
	}
	

	@Override
	public Member getUser(String s) throws SQLException {
		
		Connection conn = ConnectionHelper.getConnection();

		String sql = "select * from members where u_name = " + "'" + s + "'";
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		Member curr = null;
		while(rs.next()){
			curr = new Member(rs.getInt("m_id"),
					rs.getString("f_name"), 
					rs.getString("l_name"), 
					rs.getString("u_name"), 
					rs.getString("hash_pass"), 
					rs.getInt("auth"));
			
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return curr;
		
	}

	@Override
	public boolean insertAccount(Member user, int type) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();

		//String sql = "exec insert_account(" + user.mID + ", " + type + ")" ;
		
		CallableStatement cs = conn.prepareCall("call insert_account('" + user.getmID() + "', '" + type + "')");
		cs.executeQuery();
		//CallableStatement c_stmt = conn.prepareCall(sql);
		//boolean result = c_stmt.execute();
		
		return false;
	}

	@Override
	public List<Member> getNewMembers() throws SQLException {
		
		Connection conn = ConnectionHelper.getConnection();

		String sql = "select * from members where auth = 0";
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		List<Member> retList = new ArrayList<>();
		while(rs.next()) {
			retList.add(new Member(rs.getInt("m_id"),
					rs.getString("f_name"),
					rs.getString("l_name"),
					rs.getString("u_name"),
					rs.getString("hash_pass"),
					rs.getInt("auth")));
		}
		
		return retList;
	}

	@Override
	public boolean approveUser(int id) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();

		String sql = "update members set auth = 1 where m_id = " + id;
		
		Statement stmt = conn.createStatement();
		
		int result = stmt.executeUpdate(sql);
		if(result == 0) return false;
		return true;
	}

	@Override
	public boolean insertMember(String fName, String lName, String uName, String password) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();

		
		
		CallableStatement cs = conn.prepareCall("call insert_member('" + fName + "', '" + lName + "', '" + uName + "', '" + password + "')");
		cs.executeQuery();

		
		return true;
	}

	@Override
	public double deposit(Account act, double i) throws SQLException {
		double balance = getBalance(act);
		
		double newBalance = i + balance;
		System.out.println("NewBalance = " + newBalance);
		Connection conn = ConnectionHelper.getConnection();
		
		String sql = "Update accounts set balance = '" + newBalance
				+ "' where a_id = '" + act.getaID() + "'";
				
		Statement stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		
		if(result == 1) return newBalance;
		
		return -1;
	}
	
	@Override
	public double withdraw(Account act, double i) throws SQLException {
		double balance = getBalance(act);
		
		double newBalance = balance - i;
		
		Connection conn = ConnectionHelper.getConnection();
		
		String sql = "Update accounts set balance = '" + newBalance
				+ "' where a_id = '" + act.getaID() + "'";
				
		Statement stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		
		if(result == 1) return newBalance;
		
		return -1;
	}
	
	@Override
	public double getBalance(Account act) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();

		String sql = "select balance from accounts where a_id = " + act.getaID();
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		//return 123123;
		
		double retVal = -1;
		
		while(rs.next()) {
			
			retVal = rs.getDouble("balance");
		}
		
		return retVal;
	}

	@Override
	public List<Account> getAccounts(int i) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();

		String sql = "select * from accounts where m_id = " + i;
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		List<Account> retList = new ArrayList<>();
		while(rs.next()) {
			retList.add(new Account(rs.getInt("a_id"),
					rs.getInt("m_id"),
					rs.getInt("a_type"),
					rs.getDouble("balance")));
		}
		
		return retList;
	}

	@Override
	public boolean insertTransfer(Account from, Account to, double amount) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		CallableStatement cs = conn.prepareCall("call insert_transfer('" + from.getaID() + "', '" + to.getaID() + "', '" + amount + "')");
		cs.executeQuery();
		
		return true;
	}

	
	
	
	
}
