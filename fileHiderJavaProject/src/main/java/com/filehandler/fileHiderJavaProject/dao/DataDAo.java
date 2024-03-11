package com.filehandler.fileHiderJavaProject.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.filehandler.fileHiderJavaProject.Db.MyConnection;
import com.filehandler.fileHiderJavaProject.model.Data;

public class DataDAo {

	public static List<Data> getAllFiles(String email) throws SQLException{
		Connection connection = MyConnection.getConnection();
		// get dta where u mtches with email
		PreparedStatement ps= connection.prepareStatement("select * from data where email=?");

		ps.setString(1, email);
		ResultSet query = ps.executeQuery();
		List<Data> list = new ArrayList<>();

		while(query.next()) {
			int id = query.getInt(1);
			String name = query.getString(2);
			String path = query.getString(3);
			list.add(new Data(id,name,path));
		}
		return list;
	}



	public static int hideFile(Data file) throws SQLException, IOException {

		Connection conn = MyConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into data(name,path,email,bin_data) values(?,?,?,?)");
		ps.setString(1, file.getFileName());
		ps.setString(2, file.getPath());
		ps.setString(3, file.getEmail());
		File f = new File(file.getPath());
		FileReader fr=new FileReader(f); 
		ps.setCharacterStream(4, fr,f.length());
		int ans = ps.executeUpdate();
		// if we get 1 save 
		// if we get 0 error
		
		fr.close();
		f.delete(); 
		return ans;
		


	}
	
	
	public static void  unhide(int id) throws SQLException, IOException {
		Connection conn = MyConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select path,bin_data from data where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String path=rs.getString("path");
		Clob c=rs.getClob("bin_data");
		Reader rr=c.getCharacterStream();
		FileWriter fw=new FileWriter(path);
		int i;
		while((i=rr.read())!=-1) {
			fw.write((char)i);
		}
		fw.close();
		// to re move data from table
		conn.prepareStatement("delete from data where id=?");
		ps.setInt(1, id);
		ps.executeUpdate();
		System.out.println(" Successfully unhidden file");
	
	}


}
