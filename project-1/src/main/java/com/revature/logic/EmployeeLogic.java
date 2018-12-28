package com.revature.logic;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.Session;
import com.revature.dao.DaoImplementation;
import com.revature.objects.Employee;
import com.revature.objects.Reimb;

/**
 * Servlet implementation class EmployeeLogic
 */
public class EmployeeLogic extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -7372814337036895083L;
	final static Logger log = Logger.getLogger(EmployeeLogic.class);
	
	
    public EmployeeLogic() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String method = req.getParameter("method");
		HttpSession session = req.getSession();
		
		System.out.println("employee");
		Employee emp = (Employee) session.getAttribute("user");
		System.out.println(emp.toString());
		System.out.println(emp.getEmail());
		//String retList = null;
		switch (method) {
		case "pendingReimb": 
			
			int id = emp.getId();
			List<Reimb> retList = new ArrayList<Reimb>();
			retList = pendingReimb(id);
		
			ObjectMapper mapper = new ObjectMapper();
	
			res.setHeader("Content-Type", "application/json");
			mapper.writeValue(res.getOutputStream(), retList);
			return;
		case "resolvedReimb": 
			
			int id1 = emp.getId();
			List<Reimb> retList1 = new ArrayList<Reimb>();
			retList1 = pendingReimbResolved(id1);
		
			ObjectMapper mapper1 = new ObjectMapper();
	
			res.setHeader("Content-Type", "application/json");
			mapper1.writeValue(res.getOutputStream(), retList1);
			return;
		case "newReimb": 	String amount = req.getParameter("amount");
							String reason = req.getParameter("reason");
							newReimb(emp, amount, reason);
			break;
		case "updateEmail": String newEmail = req.getParameter("email");
							System.out.println("switch emailupdate");
							updateEmail(emp, newEmail);
			break;
		case "viewInfo": 	
			
			Employee user = viewInfo(emp.getId());
			
			ObjectMapper mapper2 = new ObjectMapper();
			
			res.setHeader("Content-Type", "application/json");
			mapper2.writeValue(res.getOutputStream(), user);
			
		default:
			break;
		}
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private Employee viewInfo(int id) {
		try {
			Employee retVal = new DaoImplementation().getUserById(id);
			return retVal;
		}catch(SQLException e) {
			log.error("error viewing info", e);
		}
		return null;
	}

	private void updateEmail(Employee emp, String newEmail) {
		if(newEmail.contains("@") && newEmail.contains(".")) {
			try {
				boolean bob = new DaoImplementation().updateEmail(emp, newEmail);
			} catch (SQLException e) {
				log.error("error updating email", e);
				e.printStackTrace();
			}
		} else System.out.println("invalid Email");
	}

	private void newReimb(Employee emp, String amount, String reason) {
		boolean result = false;
		try {
			
			result = new DaoImplementation().insertReimb(emp, Integer.parseInt(amount), reason);
		}catch (Exception e){
			log.error("error inserting reimbursment", e);
		}
	}
	private List<Reimb> pendingReimb(int id) {
		List<Reimb> list = null;
		try {
			list = new DaoImplementation().reimbList(id);
			
		} catch (SQLException e) {
			log.error("error getting pending requests", e);
			e.printStackTrace();
		}
		
		if(list != null) {
			return list;
		}
		
		return null;
		
	}
	
	private List<Reimb> pendingReimbResolved(int id) {
		List<Reimb> list = null;
		try {
			list = new DaoImplementation().reimbListresolved(id);
			
		} catch (SQLException e) {
			log.error("error getting resolved requests", e);
			e.printStackTrace();
		}
		System.out.println(list.toString());
		if(list != null) {
			return list;
		}
		
		return null;
		
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
