package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
	
public static Connection getConnection() {
		
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kam", "root", "mysql");
		}catch(Exception e) {
			System.out.println("Error occured in getting connection");
			e.printStackTrace();
		}
		return con;
	}
	
	public static String validate(String email, String password) {
		boolean isValidate=false;
		try {
			Connection con=getConnection();
			if(con!=null) {
				PreparedStatement pst=con.prepareStatement("select * from COMPANY_MAILER_USER where email=? and password=?;");
				pst.setString(1, email);
				pst.setString(2, password);
				System.out.println("Query is:"+ pst);
				ResultSet rs=pst.executeQuery();
				if(rs.next()) {
					System.out.println("Valid User"+ rs.getInt(1)+"..."+rs.getString(2));
					isValidate=true;
					return rs.getString(2);
				}
				else {
					System.out.println("User not Found");
				}
			}
			
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
