package com.revature.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.DaoImplementation;
import com.revature.objects.Employee;


public class Login extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4672956020589056L;

	final static Logger log = Logger.getLogger(Login.class);
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		String method = req.getParameter("method");
		
		if(method.equals("login")) {

			String uName = req.getParameter("username");
			String pswd = req.getParameter("password");
			
			System.out.println(uName + " this should be the username");
			System.out.println(pswd + " this should be the password");
			
			Employee emp = null;
			try {
				emp = new DaoImplementation().getUser(uName, pswd);
			} catch (SQLException e) {
				log.error("error logging in", e);
				e.printStackTrace();
			}
			if(emp != null) {
				System.out.println(emp.getfName());
			}
			
			
			HttpSession session = req.getSession();
			session.setAttribute("user", emp);
			System.out.println("session user = " + session.getAttribute("user").toString());
			
			ObjectMapper mapper = new ObjectMapper();

			res.setHeader("Content-Type", "application/json");
			
			mapper.writeValue(res.getOutputStream(), emp);
			
//			res.sendRedirect("employee.html");
			return;

		} else {
			HttpSession session = req.getSession();
			session.invalidate();
			return;
		}
		
		
	}
	
}
