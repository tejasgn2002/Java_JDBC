package com.jdbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	public static Connection createConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Java_upskill", "root", "Root123$");
		return cn;
	}
	
	public static void closeConnection(Connection cn, PreparedStatement ps, ResultSet rs) throws SQLException {
		
		if(cn != null) {
			cn.close();
		}
		
		if(ps != null) {
			ps.close();
		}
		
		if(rs != null) {
			rs.close();
		}
		
	}
}
