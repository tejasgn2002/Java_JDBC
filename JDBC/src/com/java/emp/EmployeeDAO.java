package com.java.emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO {
	
	public void insertEmployee(Employee emp) throws SQLException {
		Connection cn = Helper.createConnection();
		PreparedStatement pst = cn.prepareStatement("insert into employee(name,age,Designation,salary) values(?,?,?,?)");
		pst.setString(1, emp.getName());
		pst.setInt(2, emp.getAge());
		pst.setString(3, emp.getDesignation());
		pst.setDouble(4, emp.getSalary());
		int i = pst.executeUpdate();
		if(i == 1) {
			System.out.println("Inserted Succesfully.."); 
		}
		else {
			System.out.println("Failed to Insert..");
		}
		Helper.closeConnections(cn, pst, null);
		
	}
	
	public ArrayList<Employee> fetchAllEmployee() throws SQLException {
		Connection cn = Helper.createConnection();
		PreparedStatement pst = cn.prepareStatement("select * from employee");
		ResultSet rs = pst.executeQuery();
		ArrayList<Employee> arr = new ArrayList<Employee>();
		while(rs.next()) {
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String designation = rs.getString(4);
			double salary = rs.getDouble(5);
			arr.add(new Employee(name, age, designation, salary));
		}
		Helper.closeConnections(cn, pst, rs);
		return arr;
	}
	
	public Employee fetchEmployeeDetails(String updateName) throws SQLException {
		Connection cn = Helper.createConnection();
		PreparedStatement pst = cn.prepareStatement("select * from employee where name = ?");
		pst.setString(1, updateName);
		ResultSet rs = pst.executeQuery();
		Employee arr = null;
		while(rs.next()) {
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String designation = rs.getString(4);
			double salary = rs.getDouble(5);
			arr = new Employee(name, age, designation, salary);
		}
		Helper.closeConnections(cn, pst, rs);
		return arr;
	}
	
	public void updateEmployeeSalary(Employee emp) throws SQLException {
		Connection cn = Helper.createConnection();
//		if(fetchEmployeeDetails(emp.getName()) != null) {
		PreparedStatement pst = cn.prepareStatement("update employee set salary = ? where name = ?");
		pst.setDouble(1, emp.getSalary());
		pst.setString(2, emp.getName());
		int valid = pst.executeUpdate();
		if(valid == 1) {
			System.out.println("Salary Updated Succesfully...");
		}
		else {
			System.out.println("Failed to update Salary...");
		}
		
		Helper.closeConnections(cn, pst, null);
	}

}
