package com.filehandler.fileHiderJavaProject.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class MyConnection {

	
	public static  Connection conn;
	 
	
	public static Connection  getConnection() {
		  try {
			  Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/filehider?useSSL=false","root","12345");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println("Connection Done");
		  return conn;
	  }
	  
	  
	public static void closeConnection() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	  
}
	
	

