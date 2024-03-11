package com.filehandler.fileHiderJavaProject.views;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.filehandler.fileHiderJavaProject.dao.DataDAo;
import com.filehandler.fileHiderJavaProject.model.Data;

public class UserView {
    
	private String email;
	
	
	public UserView(String email) {
		super();
		this.email = email;
	}


	public void home() {
		do {
			System.out.println("Welcome "+this.email);
			
			System.out.println("Press 1 to Show hiddent files");
			System.out.println("press  2 to hide new file");
			System.out.println("press 3 to unhide" );
			System.out.println("press 0 t0 exit");
     Scanner sc=new Scanner(System.in);
     int ch = Integer.parseInt( sc.nextLine());
     switch(ch){
     case 1:{
    	 try {
			List<Data> files=DataDAo.getAllFiles(this.email);
			System.out.println("ID _- File Name");
			for(Data file:files) {
				System.out.println(file.getId()+" -"+file.getFileName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
     case 2:
     {
    	 System.out.println("Enter the File Path");
    	 String path = sc.nextLine();
    	 File f = new File(path);
    	 Data file=new Data(0, f.getName(), path,this.email);
    	 
    	 try {
			DataDAo.hideFile(file);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
     case 3:{
    	 List<Data> files;
		try {
			files = DataDAo.getAllFiles(this.email);
			 for(Data file:files) {
	    		 System.out.println(file.getId()+" -"+file.getFileName());
	    	 }
	    	 System.out.println("Emter the Id Of File To unhide");
	    	 int id=Integer.parseInt(sc.nextLine());
	    	 boolean isValidID=false;
	    	 for(Data file:files) {
	    		 if(file.getId()==id) {
	    			 isValidID=true;
	    			 break;
	    		 }	 
	    	 }
	    	 if(isValidID) {
	    		 DataDAo.unhide(id);
	    	 }else {
	    		 System.out.println("Wrong ID");
	    	 }
	    	 
	    	 
	    	 
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 System.out.println("ID - File Name");
    	
    	 
     }
     case 0:{
    	 System.exit(0);
     }
     }
			
		}while(true);
	}
	
	
}
