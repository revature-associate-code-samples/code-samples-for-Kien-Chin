package com.revature.Project0;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	try {
			Driver.run();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
    }
}
