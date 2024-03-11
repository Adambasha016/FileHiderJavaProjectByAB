package com.filehandler.fileHiderJavaProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.filehandler.fileHiderJavaProject.Db.MyConnection;
import com.filehandler.fileHiderJavaProject.model.User;

public class UserDAO {

	public static boolean isExists(String email) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select email from users");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String e=rs.getString(1);
			if(e.equals(email)) {
				return true;
			}
		}
		return false;
		
	}
	
	//if user not exixts so we need to inset the user into dB
	
	public static int saveUse(User user) throws SQLException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert int users values(default,?,?)"); 
	    ps.setString(1, user.getName());
	    ps.setString(2,  user.getEmail());
	    int update = ps.executeUpdate();
	    return update;
	
	
	}
	
	
	
	
	
	
}
