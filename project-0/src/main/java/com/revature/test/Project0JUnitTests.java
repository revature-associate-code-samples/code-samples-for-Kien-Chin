package com.revature.test;

//import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import com.revature.dao.Project0DAOImplementation;
import com.revature.objects.Member;

public class Project0JUnitTests {
	Project0DAOImplementation dao = new Project0DAOImplementation();
	
	public void testDeposit() throws SQLException {
		Member test = new Member(1, "Kien", "Chin", "Chink", "94e5660fd18bdd9099f251bf765388e8", 2);
		Member database = dao.getUser("1");
		//assertEquals(test.getmID(), database.getmID());
	}

	
}
