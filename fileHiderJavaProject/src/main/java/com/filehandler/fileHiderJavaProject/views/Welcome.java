package com.filehandler.fileHiderJavaProject.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import com.filehandler.fileHiderJavaProject.dao.UserDAO;
import com.filehandler.fileHiderJavaProject.model.User;
import com.filehandler.fileHiderJavaProject.service.GenerateOTP;
import com.filehandler.fileHiderJavaProject.service.SendOTPService;
import com.filehandler.fileHiderJavaProject.service.UserService;

public class Welcome {



	public void welcomeScreen() {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Wlcome to the app");
		System.out.println("Press 1 to login ");
		System.out.println("Press 2 to signup ");
		System.out.println("Press 0 to exit ");
		int choice=0;

		try {
			choice= Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		switch(choice) {
		case 1:login();
		break;
		case 2:signUp();
		break;
		case 0:System.exit(0);

		}


	}

	private void signUp() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name");
		String name=sc.nextLine();
		System.out.println("Enter email");
		String email=sc.nextLine();
		
		String genOTP=GenerateOTP.getOTP();
		SendOTPService.sendOTP(email, genOTP);
		System.out.println("enter the otp");
		String otp= sc.nextLine();
		
		if(otp.equals(genOTP)) {
			User user = new User(name, email);
			int respone=UserService.saveUser(user);
			switch(respone) {
			case 0:System.out.println("user registerd");
			case 1:System.out.println("User already exist");
			
			}
		}else {
			System.out.println("Wrong OTP");
		}
		
		
		
		
		// TODO Auto-generated method stub

	}

	private void login() {
		Scanner sc=new Scanner(System.in);
		String email=sc.nextLine();
		try {
			if(UserDAO.isExists(email)) {
				String genOTP=GenerateOTP.getOTP();
				SendOTPService.sendOTP(email, genOTP);
				System.out.println("enter the otp");
				String otp=sc.nextLine();
				if(otp.equals(genOTP)) {
					new UserView(email).home();
					
					
					System.out.println("welcome");
				}else {
					System.out.println("wrong otp");
				}
			}
			else {
				System.out.println("user not found");
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
		
		
		// TODO Auto-generated method stub

	}
}
