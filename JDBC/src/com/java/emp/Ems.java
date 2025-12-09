package com.java.emp;

import java.sql.SQLException;
import java.util.Scanner;
//	https://github.com/tejasgn2002/Java_JDBC.git
public class Ems {
	String name;
	int age;
	double salary;
	String designation;
	
	public boolean check() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name: ");
		name = sc.nextLine();
		EmployeeDAO empdao = new EmployeeDAO();
		Employee emp = empdao.fetchEmployeeDetails(name);
		
//		boolean result = emp!=null ? true : false;
		if(emp != null) {
			salary = emp.getSalary();
			return true;
		}
		return false;
	}
	
	public boolean check(String name) {
		int count = 0;
		for(int i = 0;i<name.length();i++) {
			if(name.charAt(i) == ' ') {
				count++;
			}
		}
		if(count > 2) {
			System.out.println("Name Can't have more than 2 Space...");
			return true;
		}
		return false;
	}
	
	public boolean check(int age) {
		if(age >= 20 && age <= 60) {
			return false;
		}
		System.out.println("Enter the valid age...");
		return true;
	}
	
	public void createDetails() {
		Scanner sc = new Scanner(System.in);
		boolean status = true;
		while(status) {
			System.out.print("Enter Name: ");
			name = sc.nextLine();
			status = check(name);
		}
		
		status = true;
		while(status) {
			System.out.print("Enter Age: ");
			age = sc.nextShort();
			status = check(age);
		}
		
		status = true;
		while(status) {
			System.out.print("Designation List: \n"
					+ "1.Programmer\n"
					+ "2.Manager\n"
					+ "3.Tester\n"
					+ "Enter Designation: ");
			int des = sc.nextInt();
			switch(des) {
			case 1:
				salary = 20000;
				designation = "Programmer";
				status = false;
				break;
			case 2:
				salary = 25000;
				designation = "Manager";
				status = false;
				break;
			case 3:
				salary = 15000;
				designation = "Tester";
				status = false;
				break;
			default:
				System.out.println("Enter the valid number:");
				break;
			}
		}
		
		//insert database
		Employee emp = new Employee(name, age, designation, salary);
		EmployeeDAO empDao = new EmployeeDAO();
		
		try {
			empDao.insertEmployee(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void display() throws SQLException {
		EmployeeDAO empdao = new EmployeeDAO();
		System.out.printf("%10s|%15s|%20s|%20s\n","Name","Age","Designation","Salary");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		for(Employee emp : empdao.fetchAllEmployee()) {
			System.out.printf("%10s|%15d|%20s|%20f\n",emp.getName(),emp.getAge(),emp.getDesignation(),emp.getSalary());
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
//		System.out.println("Your Name is: "+name
//				+"\nYour age is: "+age
//				+"\nYour Designation is: "+designation
//				+ "\nYour Salary is: "+salary);
	}
	
	public void raise_salary(double hike) {
		salary = salary + (salary * (hike/100));
		EmployeeDAO empdao = new EmployeeDAO();
		Employee emp = new Employee();
		emp.setName(name);
		emp.setSalary(salary);
		try {
			empdao.updateEmployeeSalary(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hike() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the percentage of salary hike(1 - 10): ");
		double hike = sc.nextDouble();
		if(hike >= 1.0 && hike <= 10.0) {
			raise_salary(hike);
		}
		else {
			System.out.println("Enter the valid number...");
		}
	}

	public static void main(String[] args) throws SQLException {
		
		Ems details = new Ems();
		
		while(true) {
			System.out.println("1.Create\n2.Display"
					+ "\n3.Raise Salary\n4.Exit");
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter the Choice: ");
			short check = sc.nextShort();
			
			switch(check) {
			case 1:
				char ch = 'y'; 
				while(ch != 'n') {
					details.createDetails();
					System.out.println("Do you want to continue: y/n");
					ch = sc.next().charAt(0);
				}
				break;
			case 2:
				try {
					details.display();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				if(details.check()) {
					details.hike();
				}
				else {
					System.out.println("Does not match any name");
				}
				break;
			case 4:
				System.out.println("Thank You for using application...");
				System.exit(0);
			default:
				System.out.println("Enter the valid Number....");
			}
		}
	}

}
