package com.jdbc.DatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	
    	Scanner sc = new Scanner(System.in);
    	short num;
    	DAO dao = new DAO();
    	while(true) {
    		System.out.println("Enter the number:\n"
        			+ "1.Create\n"
        			+ "2.Insert\n"
        			+ "3.Update\n"
        			+ "4.Delete\n"
        			+ "5.Select\n"
        			+ "6.Exit");
    		num = sc.nextShort();
    		switch(num) {
    		case 1:
    			dao.createTable();
    			break;
    		case 2:
    			dao.insertIntoTable();
    			break;
    		case 3:
    			dao.updateData();
    			break;
    		case 4:
    			dao.DeleteData();
    			break;
    		case 5:
    			dao.selectData();
    			break;
    		case 6:
    			System.out.println("Thank You...");
    			System.exit(0);
    			break;
    		default:
    			System.out.println("Enter the valid number.......");
    			break;
    		}
    	}
    }
}
