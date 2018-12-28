package com.revature.Project0;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.Project0DAOImplementation;
import com.revature.objects.Member;
public class Driver {
	
	final static Logger log = Logger.getLogger(Driver.class);
	
	static Member user;
	
	static Scanner in = new Scanner(System.in);
	
	public static void run() throws IOException {
		System.out.println("Welcome to Bank");
		
		boolean exit = false;
		
		while(!exit) {
			System.out.println("1. Login\n"
				+ "2. Create Account\n"
				+ "3. Exit");
		
			String s = in.nextLine();
			switch (s) {
				case "1" : login();
				break;
				case "2" : createAccount();
				break;
				case "3" : exit = true;
				break;
				default : System.out.println("What?");
			}	
		}
		
		
		
		
		System.out.println("GoodBye");
		
		in.close();
		
	}

	private static void createAccount() {
		String fName = "";
		String lName = "";
		String uName = "";
		String password = "";
		
		boolean valid = false;
		
		do {

			System.out.println("First name + Last name \"first last\"");
			String[] temp = in.nextLine().split(" ");
			if(temp.length < 2) {
				System.out.println("Invalid input");
			} else {
				fName = temp[0];
				lName = temp[1];
				valid = true;
			} 
		} while(!valid);
		
		
		do {

			System.out.print("Username: ");
			String s = in.nextLine();
			if(s.length() == 0) {
				System.out.println("Invalid input");
			} else {
				uName = s;
				valid = true;
			} 
		} while(!valid);
		
		do {

			System.out.print("Password: ");
			String s = in.nextLine();
			if(s.length() == 0) {
				System.out.println("Invalid input");
			} else {
				password = s;
				valid = true;
			} 
		} while(!valid);
		
		System.out.println("call insert_member('" + fName + "', '" + lName + "', '" + uName + "', '" + password + "')");
		
		
		do {
			boolean result = false;
			try {
				result = new Project0DAOImplementation().insertMember(fName, lName, uName, password);
			} catch (SQLException e) {
				//log.error("error during member creation");
				//e.printStackTrace();
				//This is broken fix me pls
				break;
			}
			if(!result) {
				System.out.println("Welcome new Member :)");
				break;
			}
		} while(true);
		
		
	}

	private static void login() {

		while(true) {
			
			System.out.print("Username (e to exit): ");
			
			
			String s = in.nextLine();
			
			if(s.equals("e")) break;
			
			try {
				user = new Project0DAOImplementation().getUser(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(user != null) {
				boolean logout = false;
				while(!logout) {
					

					System.out.print("Password (e to exit): ");
					
					s = in.nextLine();
					//Chint = 12b2311b1e102b8484c86d2c992456c7
					
					//Chink = 94e5660fd18bdd9099f251bf765388e8
					
					if(s.equals("e")) break;
					
					if(user.gethPass().equals(s)) {
						UserDriver.run(user);
						logout = true;
					} 
					
				}
				
			}
			
		}		
	}
	
	
	
}
