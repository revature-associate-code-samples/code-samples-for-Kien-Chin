package com.revature.Project0;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.Project0DAOImplementation;
import com.revature.objects.Account;
import com.revature.objects.Member;

public class User {
	
	static Member user;
	static Account act;
	static Scanner in = new Scanner(System.in);
	
	final static Logger log = Logger.getLogger(Driver.class);
	
	public static void run(Member user1) {
		user = user1;
		
		
		System.out.println("Welcome User");
		
		boolean exit = false;
		while(!exit) {
			System.out.println("Options:\n"
					+ "1. Access Account\n"
					+ "2. Create Account\n"
					+ "3. View Accounts\n"
					+ "4. Exit");
			String s = in.nextLine();
			switch (s) {
				case "1" : accessAccount();
				break;
				case "2" : createAccount();
				break;
				case "3" : ViewAccounts();
				break;
				case "4" : exit = true;
				break;
				default : System.out.println("What?");
			}	
		}
		
		
	}
	
	private static void ViewAccounts() {
		System.out.println("Accounts");
		List<Account> ls = null;
		try {
			ls = new Project0DAOImplementation().getAccounts(user.getmID());
		} catch (SQLException e) {
			log.error("Error while accessing the accounts");
			e.printStackTrace();
		}
		
		if (ls != null) {
			for(Account m : ls) {
				System.out.println(m.toString());
			}
		}
		
		
	}


	private static void createAccount() {
		System.out.println("createAccount");
		
		boolean success = false;
		
		while(!success) {

			boolean valid = false;

			int type = 0;
			
			while(!valid) {
				System.out.println("Account Type\n"
						+ "0. Checking\n"
						+ "1. Savings\n"
						+ "2. Exit\n");
				String s = in.nextLine();
				switch (s) {
				case "0" : type = Integer.parseInt(s);
							valid = true;
				break;
				case "1" : type = Integer.parseInt(s);
							valid = true;
				break;
				case "2" : success = true;
							valid = true;
				break;
				default : System.out.println("Invalid Account Type");
				}
			}
			
			
			if (!success){
				boolean result = false;
				try {
					result = new Project0DAOImplementation().insertAccount(user, type);
				} catch (SQLException e) {
					log.error("error during account creation");
					e.printStackTrace();
				}
				if(!result) {
					System.out.println("Account created :)");
					success = true;
					break;
				}
			}
			
		}
			
	}

	private static void accessAccount() {
		System.out.println("accessAccount");

		boolean found = false;
		while(!found) {
			System.out.print("AccountID (e to exit):");
			String s = in.nextLine();
			
			if(s.equals("e")) break;
			
			try {
				act = new Project0DAOImplementation().getAccount(s, user);
			} catch (SQLException sqle) {
				log.error("Error accessing account");
			}
			
			if(act != null) {
				//found = true;
				System.out.println(act.toString());
				accountOptions();
			}
		}
		
		
		
	}

	private static void accountOptions() {
		
		
		boolean exit = false;
		while (!exit) {

			System.out.println("Options: \n"
					+ "1. Widthdraw\n"
					+ "2. Deposit\n"
					+ "3. Transfer\n"
					+ "4. View Accounts\n"
					+ "5. done");
			String s = in.nextLine();
			
			switch (s) {
			case "1" : withdraw();
			break;
			case "2" : deposit();
			break;
			case "3" : transfer();
			break;
			case "4" : ViewAccounts();
			break;
			case "5" : exit = true;
			break;
			default : System.out.println("What?");
		}	
		}	
	}

	private static void transfer() {
		System.out.println("Transfer");
		
		Account target = null;
		double amount = 0.00;
		boolean valid = false;
		boolean exit = false;
		String s;
		do {
			System.out.print("Account ID (e to exit):");
			
			s = in.nextLine();
			
			if(s.equals("e")) {
				valid = true;
				exit = true;
			} else {
				
				
				try {
					target = new Project0DAOImplementation().getAccount(s);
				} catch (SQLException e) {
					log.error("Error retrieving account");
					e.printStackTrace();
				}
				
				
			}
			
		} while(valid);
		
		while (!exit) {
			System.out.print("Transfer amount (e to exit):");
			
			s = in.nextLine();
			
			if(s.equals("e")) {
				exit = true;
				System.out.println("Exiting");
			} else {

				try {
					amount = Integer.parseInt(s);
					exit = true;
					
				}catch (Exception e){
					System.out.println("invalid number");
				}
				
				if(target != null && !valid) {
					
					try {
						Project0DAOImplementation dao = new Project0DAOImplementation();
						dao.withdraw(act, amount);
						dao.deposit(target, amount);
						dao.insertTransfer(act, target, amount);
						System.out.println("Transfer Complete");
					} catch (SQLException e) {
						log.error("Error during transfer");
						e.printStackTrace();
					}
				}
			}
			
		}
	}

	private static void deposit() {
		System.out.println("Deposit (e to exit)");
		double i = 0;
		boolean valid = false;
		boolean exit = true;
		do {
			System.out.print("Amount: ");
			
			String s = in.nextLine();
			
			if(s.equals("e")) {
				valid = true;
				exit = true;
			} else {
				
				try {
					i = Double.parseDouble(s);
					if (i < 0) {
						System.out.println("Amount cannot be negative");
						continue;
					}
					else {
						System.out.println("Valid amount - Initializing deposit");
						valid = true;
					}
					
				} catch (Exception e) {
					System.out.println("invalid input - Please enter a valid amount");
					
				}
			}
			
		} while(!valid);
		
		double result = 0;
		
		if(exit) {
			try {
				result = new Project0DAOImplementation().deposit(act, i);
			}catch (SQLException se) {
				log.error("Error is deposit");
			}
		
			System.out.println("Deposit successful,\n"
					+ "New balance: " + result);
	
		}
			
		
		
		
	}

	private static void withdraw() {
		System.out.println("Widthdraw");
		
		double i = 0;
		boolean valid = false;
		
		boolean exit = false;
		
		do {
			System.out.print("Amount (e to exit): ");
			
			String s = in.nextLine();
			
			if(s.equals("e")) {
				valid = true;
				exit = true;
			} else {
				
				try {
					i = Double.parseDouble(s);
					if (i < 0) System.out.println("Amount cannot be negative");
					else valid = true;
					
					double balance = 0;
					balance = new Project0DAOImplementation().getBalance(act);
					
					if(i > balance) {
						System.out.println("Insufficient funds - Current Balance = " + balance);
						valid = false;
						continue;
					}
									
				} catch (Exception e) {
					System.out.println("invalid input - Please enter a valid amount");
					
				}
			}
			
			
			
		} while(!valid);
		
		if(!exit) {
			double result = 0;
			
			try {
				result = new Project0DAOImplementation().withdraw(act, i);
			}catch (SQLException se) {
				log.error("Error is withdraw");
			}
			
			System.out.println("Withdraw successful,\n"
					+ "New balance: " + result);
		}
		
		
	}

	
	
}
