package com.revature.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.DaoImplementation;
import com.revature.objects.Employee;
import com.revature.objects.Reimb;

/**
 * Servlet implementation class ManagerLogic
 */
public class ManagerLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log = Logger.getLogger(EmployeeLogic.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerLogic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = req.getParameter("method");
		HttpSession session = req.getSession();
		
		Employee emp = (Employee) session.getAttribute("user");
		ObjectMapper mapper = new ObjectMapper();
		List<Reimb> retList = new ArrayList<Reimb>();
		List<Employee> retListE = new ArrayList<Employee>();
		int id = 0;
		switch (method) {
		case "pendingReimb" :
			
			
			retList = pendingReimb();
		
			
	
			res.setHeader("Content-Type", "application/json");
			//String list = retList.replaceAll("\\\\", "");
			mapper.writeValue(res.getOutputStream(), retList);
			return;
		case "resReimb" :
			
			retList = resReimb();
	
			res.setHeader("Content-Type", "application/json");
			//String list = retList.replaceAll("\\\\", "");
			mapper.writeValue(res.getOutputStream(), retList);
			return;
			
		case "viewEmp" :
			
			retListE = viewEmp();
	
			res.setHeader("Content-Type", "application/json");
			//String list = retList.replaceAll("\\\\", "");
			mapper.writeValue(res.getOutputStream(), retListE);
			return;
		case "approve" :
			id = Integer.parseInt(req.getParameter("id"));
			approve(id, emp);
		case "decline" :
			id = Integer.parseInt(req.getParameter("id"));
			decline(id, emp);
		case "pendingEmpReimb" :
			id = Integer.parseInt(req.getParameter("id"));
			retList = pendingEmpReimb(id);
			res.setHeader("Content-Type", "application/json");
			mapper.writeValue(res.getOutputStream(), retList);
		}
	}

	private List<Reimb> pendingEmpReimb(int id) {
		List<Reimb> list = null;
		try {
			list = new DaoImplementation().reimbEmpList(id);
			
		} catch (SQLException e) {
			log.error("Error checking employees reimbursments", e);
			e.printStackTrace();
		}
		
		if(list != null) {
			return list;
		}
		
		return null;
	}

	private void decline(int id, Employee emp) {
		// TODO Auto-generated method stub
		try {
			System.out.println(emp.getId());
			boolean retVal = new DaoImplementation().denyReimb(id, emp);
		} catch (SQLException e) {
			log.error("Error declining reimbursment", e);
			e.printStackTrace();
		}
	}

	private void approve(int id, Employee emp) {
		// TODO Auto-generated method stub
		try {
			System.out.println(emp.getId());
			boolean retVal = new DaoImplementation().approveReimb(id, emp);
		} catch (SQLException e) {
			log.error("Error approving reimbursment", e);
			e.printStackTrace();
		}
	}

	private List<Employee> viewEmp() {
		List<Employee> list = null;
		try {
			list = new DaoImplementation().getEmployees();
			
		} catch (SQLException e) {
			log.error("Error getting employees", e);
			e.printStackTrace();
		}
		System.out.println(list.toString());
		if(list != null) {
			return list;
		}
		
		return null;
	}

	private List<Reimb> resReimb() {
		List<Reimb> list = null;
		try {
			list = new DaoImplementation().resolvedReimbList();
			
		} catch (SQLException e) {
			log.error("Error getting resolved reimbursments", e);
			e.printStackTrace();
		}
		
		if(list != null) {
			return list;
		}
		
		return null;
	}

	private List<Reimb> pendingReimb() {
		List<Reimb> list = null;
		try {
			list = new DaoImplementation().pendingReimbList();
			
		} catch (SQLException e) {
			log.error("Error getting pending reimbursments", e);
			e.printStackTrace();
		}
		
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
