package com.book.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnectionUtil {
	
	private static final String DB_DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
	private static final String DB_USERNAME="root";
	private static final String DB_PASSWORD="root";
	private static final String DB_URL="jdbc:mysql://localhost:3306/demo";
	
	private static Connection connection=null;
	
	//DriverManager will give 1 connection at a time.100 connection required,1 st con will be finished remaining wait...
	//so far go to connection pool..in tomcat
	
	static {
		
		try {
			Class.forName(DB_DRIVER_CLASS);
			connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
		} catch (ClassNotFoundException | SQLException  e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		return connection;
	}
	
	public static void DbCleanUp(Connection con,Statement st,ResultSet rs) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	

}
