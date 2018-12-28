package com.revature.Project0;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.Project0DAOImplementation;
import com.revature.objects.Account;
import com.revature.objects.Member;

public class Admin {
	
	static Member user;
	static Account act;
	static Scanner in = new Scanner(System.in);
	
	final static Logger log = Logger.getLogger(Driver.class);
	
	public static void run(Member user1) {
		user = user1;
		
		System.out.println("Welcome Admin");
		
		
		boolean exit = false;
		while(!exit) {
			
			System.out.println("Options:\n"
					+ "1. View New Users\n"
					+ "2. Approve Users\n"
					+ "3. View Transactions\n"
					+ "4. Exit");
			
			String s = in.nextLine();
			switch (s) {
				case "1" : viewNewUsers();
				break;
				case "2" : approveUsers();
				break;
				case "3" : ViewTransactions();
				break;
				case "4" : exit = true;
				break;
				default : System.out.println("What?");
			}	
		}
	}

	private static void ViewTransactions() {
		System.out.println("View Transactions");
	}

	private static void approveUsers() {
		System.out.println("Approve Users");
		System.out.print("Member ID (e to exit):");
		String s = in.nextLine();
		
		if (!s.equals("e")) {
			
			boolean result = false;
			try {
				result = new Project0DAOImplementation().approveUser(Integer.parseInt(s));
			} catch (SQLException e) {
				log.error("Error approving User");
			}
			if (result) {
				System.out.println("User Approved");
			} else System.out.println("oops");
		}
	}

	private static void viewNewUsers() {
		System.out.println("New Users");
		
		List<Member> ls = null;
		try {
			ls = new Project0DAOImplementation().getNewMembers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (ls != null) {
			for(Member m : ls) {
				System.out.println(m.toString());
			}
		}
	}
}
