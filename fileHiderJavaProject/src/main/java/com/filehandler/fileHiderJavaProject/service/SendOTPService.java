package com.filehandler.fileHiderJavaProject.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendOTPService {
public static void sendOTP(String email,String genOTP) {
	// recipient email id to be mentioned
	String to =email;
    
	// sender email id need to be mention
	String from="samcurren@gmail.com";
	
	// assumimg we are sending email from through gamil smpt
	String host="smtp.gamil.com";
	
	// get system properties
       Properties properties = System.getProperties();
       
       
       // set mail server
       
       properties.put("mail.smtp.host", host);
       properties.put("mail.smtp.host", 465);
       properties.put("mail.smtp.ssl.enable", "true");
       properties.put("mail.smtp.auth", "true");
       
       
       // get the session object  and pass username and password
       
         
     Session session=  Session.getInstance(properties,getPasswordAuthentication()
    		 -> {
    	  return new PasswordAuthentication(from,"shdhdnsjsk");
    	   
       });
     
     
     // used to debug smtp issues
     session.setDebug(true);
     try {
     // create a default mimemessege object 
    MimeMessage message= new MimeMessage(session);
      // set from: header field of the header
    message.setFrom(new InternetAddress(from));

    // set to:header field of the header
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
    
    //set subject :header filed
    message.setSubject("file enc ka otp");
     // now set the actual message
    message.setText("your one time passowrd for file enc app is "+genOTP);
    System.out.println("Sending...");
    // send message
    Transport.send(message);
    System.out.println(" sent message successfullt...");
     }catch(MessagingException mex) {
    	 mex.printStackTrace();
     }
}
}
