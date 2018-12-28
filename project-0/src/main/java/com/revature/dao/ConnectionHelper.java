package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
	

		//JDBC - Java Database Connectivity
		//DB - Database
		
		public static Connection getConnection() throws SQLException{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Class.forname worked");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String url = "jdbc:oracle:thin:@kienchin.co4cbltc301f.us-east-2.rds.amazonaws.com:1521:MYDB";
			String user = "kienchin";
			String pass = "123456789";
			return DriverManager.getConnection(url, user, pass);
		
	}
}
