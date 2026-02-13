package com.jdbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DAO {
	
	public void createTable() throws ClassNotFoundException, SQLException {
		Connection cn = DBUtil.createConnection();
		String sql = "create table student(id int primary key auto_increment,name varchar(20))";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.execute();
		System.out.println("Class Created");
		DBUtil.closeConnection(cn, ps, null);
	}
	
	public void insertIntoTable() throws ClassNotFoundException, SQLException {
		Connection cn = DBUtil.createConnection();
		String sql = "insert into student(name) values(?)";
		PreparedStatement ps = cn.prepareStatement(sql);
		System.out.print("Enter the Student name: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		ps.setString(1,name);
		int i = ps.executeUpdate();
		if(i == 0)
			System.out.println("Failed to insert");
		else
			System.out.println("Inserted Successfully");
		DBUtil.closeConnection(cn, ps, null);
	}
	
	public void updateData() throws ClassNotFoundException, SQLException {
		Connection cn = DBUtil.createConnection();
		String sql = "update student set name = ? where id = ?;";
		PreparedStatement ps = cn.prepareStatement(sql);
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Student Id: ");
		int id = sc.nextInt();sc.nextLine();
		System.out.print("Enter the Updated Student name: ");
		String name = sc.nextLine();
		ps.setString(1,name);
		ps.setInt(2, id);
		int i = ps.executeUpdate();
		if(i == 0)
			System.out.println("Failed to udpate");
		else
			System.out.println("Updated Successfully");
		DBUtil.closeConnection(cn, ps, null);
	}
	
	public void selectData() throws ClassNotFoundException, SQLException {
		Connection cn = DBUtil.createConnection();
		String sql = "select * from student";
		PreparedStatement ps = cn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int flag = 0;
		while(rs.next()) {
			flag = 1;
			System.out.println(rs.getInt(1)+" : "+rs.getString(2));
		}
		if(flag  == 0) {
			System.out.println("No Data in Database");
		}
		DBUtil.closeConnection(cn, ps, rs);
	}
	
	public void DeleteData() throws ClassNotFoundException, SQLException {
		Connection cn = DBUtil.createConnection();
		String sql = "delete from student where id = ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Student Id: ");
		int id = sc.nextInt();
		ps.setInt(1, id);
		int i = ps.executeUpdate();
		if(i == 0)
			System.out.println("Failed to delete");
		else
			System.out.println("Deleted Successfully");
		DBUtil.closeConnection(cn, ps, null);
	}
}
