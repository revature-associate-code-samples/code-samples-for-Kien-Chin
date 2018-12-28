package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.objects.Employee;
import com.revature.objects.Reimb;



public class DaoImplementation implements Dao {

	public Employee getUser(String username, String password) throws SQLException {
		
		Connection conn = ConnectionHelper.getConnection();
		
		
		
		String sql = "select * from employee where u_name = " + "'" 
					+ username + "' and pswd = '" + password + "'"; 
		
		System.out.println(sql);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		Employee curr = null;
		
		while (rs.next()) {
			curr = new Employee(rs.getInt("e_id"),
					rs.getString("f_name"),
					rs.getString("l_name"),
					rs.getString("u_name"),
					rs.getString("pswd"),
					rs.getString("email"),
					rs.getInt("auth")
					);
		}
		
		
		rs.close();
		stmt.close();
		conn.close();
		
		return curr;
		
	}
	
	public Employee getUserById(int id) throws SQLException {
		
		Connection conn = ConnectionHelper.getConnection();
		
		
		
		String sql = "select * from employee where e_id = '" + id + "'"; 
		
		System.out.println(sql);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		Employee curr = null;
		
		while (rs.next()) {
			curr = new Employee(rs.getInt("e_id"),
					rs.getString("f_name"),
					rs.getString("l_name"),
					rs.getString("u_name"),
					rs.getString("pswd"),
					rs.getString("email"),
					rs.getInt("auth")
					);
		}
		
		
		rs.close();
		stmt.close();
		conn.close();
		
		return curr;
		
	}
	
	public List<Employee> getEmployees() throws SQLException {
		
		Connection conn = ConnectionHelper.getConnection();

		String sql = "select * from employee where auth = '1'";
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		List<Employee> retList = new ArrayList<>();
		while(rs.next()) {
			System.out.println(rs.getString("email"));
			System.out.println(rs.getString(6));
			
			retList.add(new Employee(rs.getInt("e_id"),
					rs.getString("f_name"),
					rs.getString("l_name"),
					rs.getString("u_name"),
					rs.getString("pswd"),
					rs.getString("email"),
					rs.getInt("auth")
					));
		}
		rs.close();
		stmt.close();
		conn.close();
		System.out.println(retList.toString());
		return retList;
	}

	@Override
	public List<Reimb> reimbList(int empID) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		
		
		String sql = "select * from re_im where e_id = " + "'" 
					+ empID + "' and status = '0'"; 
		
		System.out.println(sql);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List<Reimb> retList = new ArrayList<>();
		while(rs.next()) {
			retList.add(new Reimb(rs.getInt("r_id"), 
					rs.getInt("e_id"), 
					rs.getInt("m_id"), 
					rs.getInt("amount"), 
					rs.getString("txt"), 
					rs.getInt("status"), 
					rs.getString("image_url")));
		}
		rs.close();
		stmt.close();
		conn.close();
		return retList;
	}
	

	@Override
	public List<Reimb> reimbListresolved(int empID) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		
		
		String sql = "select * from re_im where e_id = " + "'" 
					+ empID + "' and status <> '0'"; 
		
		System.out.println("resolved" + sql);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List<Reimb> retList = new ArrayList<>();
		while(rs.next()) {
			retList.add(new Reimb(rs.getInt("r_id"), 
					rs.getInt("e_id"), 
					rs.getInt("m_id"), 
					rs.getInt("amount"), 
					rs.getString("txt"), 
					rs.getInt("status"), 
					rs.getString("image_url")));
		}
		rs.close();
		stmt.close();
		conn.close();
		return retList;
	}

	@Override
	public List<Reimb> pendingReimbList() throws SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		
		
		String sql = "select * from re_im where status = '0'"; 
		
		System.out.println(sql);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List<Reimb> retList = new ArrayList<>();
		while(rs.next()) {
			retList.add(new Reimb(rs.getInt("r_id"), 
					rs.getInt("e_id"), 
					rs.getInt("m_id"), 
					rs.getInt("amount"), 
					rs.getString("txt"), 
					rs.getInt("status"), 
					rs.getString("image_url")));
		}
		rs.close();
		stmt.close();
		conn.close();
		return retList;
	}

	@Override
	public List<Reimb> resolvedReimbList() throws SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		
		
		String sql = "select * from re_im where status <> '0'"; 
		
		System.out.println(sql);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List<Reimb> retList = new ArrayList<>();
		while(rs.next()) {
			retList.add(new Reimb(rs.getInt("r_id"), 
					rs.getInt("e_id"), 
					rs.getInt("m_id"), 
					rs.getInt("amount"), 
					rs.getString("txt"), 
					rs.getInt("status"), 
					rs.getString("image_url")));
		}
		rs.close();
		stmt.close();
		conn.close();
		return retList;
	}

	@Override
	public boolean approveReimb(int rID, Employee emp) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();

		String sql = "update re_im set status = 1, m_id = " + emp.getId() + "  where r_id = " + rID;
		
		Statement stmt = conn.createStatement();
		System.out.println(sql);
		int result = stmt.executeUpdate(sql);
		if(result == 0) return false;
		return true;
	}

	@Override
	public boolean denyReimb(int rID, Employee emp) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();

		String sql = "update re_im set status = 2, m_id = " + emp.getId() + " where r_id = " + rID;
		
		Statement stmt = conn.createStatement();
		System.out.println(sql);
		int result = stmt.executeUpdate(sql);
		if(result == 0) return false;
		return true;
	}

	@Override
	public boolean insertReimb(Employee user, int amount, String reason) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();

		
		CallableStatement cs = conn.prepareCall("call insert_reimbursment('" + user.getId() + "', '" + amount + "', '" + reason + "')");
		cs.executeQuery();
		
		return true;
	}

	@Override
	public boolean updateEmail(Employee user, String newEmail) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();

		String sql = "update employee set email = '" + newEmail + "' where e_id = " + user.getId();
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		
		int result = stmt.executeUpdate(sql);
		if(result == 0) return false;
		return true;
	}

	@Override
	public List<Reimb> reimbEmpList(int empID) throws SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		
		
		String sql = "select * from re_im where e_id = '" 
					+ empID + "' and status = '0'"; 
		
		System.out.println(sql);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List<Reimb> retList = new ArrayList<>();
		while(rs.next()) {
			retList.add(new Reimb(rs.getInt("r_id"), 
					rs.getInt("e_id"), 
					rs.getInt("m_id"), 
					rs.getInt("amount"), 
					rs.getString("txt"), 
					rs.getInt("status"), 
					rs.getString("image_url")));
		}
		rs.close();
		stmt.close();
		conn.close();
		return retList;
	}
	
}
