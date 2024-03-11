package com.filehandler.fileHiderJavaProject.service;
// to check it inserting or not

import java.sql.SQLException;

import com.filehandler.fileHiderJavaProject.dao.UserDAO;
import com.filehandler.fileHiderJavaProject.model.User;

public class UserService
  {

 public static  Integer saveUser(User user) {
	 
	 try {
		if(UserDAO.isExists(user.getEmail())) {
			 return 0;
		 }
		else {
			 return UserDAO.saveUse(user);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 return null;
 }
	
	
  }
