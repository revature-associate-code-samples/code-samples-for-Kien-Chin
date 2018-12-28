package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.objects.Employee;
import com.revature.objects.Reimb;

public interface Dao {
	public Employee getUser(String username, String password) throws SQLException;

	public List<Employee> getEmployees() throws SQLException;
	
	public List<Reimb> reimbList(int empID) throws SQLException;
	
	public List<Reimb> pendingReimbList() throws SQLException;

	public List<Reimb> resolvedReimbList() throws SQLException;
	
	public boolean approveReimb(int rID, Employee emp) throws SQLException;
	
	public boolean denyReimb(int rID, Employee emp) throws SQLException;
	
	public boolean updateEmail(Employee user, String newEmail) throws SQLException;
	
	public boolean insertReimb(Employee user, int amount, String reason) throws SQLException;

	public List<Reimb> reimbEmpList(int empID) throws SQLException;

	List<Reimb> reimbListresolved(int empID) throws SQLException;
}
