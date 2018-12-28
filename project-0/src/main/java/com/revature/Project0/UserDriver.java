package com.revature.Project0;


import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.objects.Account;
import com.revature.objects.Member;

public class UserDriver {
	
	static Member user;
	static Account act;
	static Scanner in = new Scanner(System.in);
	
	final static Logger log = Logger.getLogger(Driver.class);
	
	public static void run(Member user1) {
		user = user1;
		if (user.getAuth() == 1) {
			User.run(user);//userLoggedIn();
		} else if (user.getAuth() == 2) {
			Admin.run(user);//adminLoggedIn();
		} else if (user.getAuth() == 0) {
			System.out.println("Account not verified");
		} 
	}
	
	
}
