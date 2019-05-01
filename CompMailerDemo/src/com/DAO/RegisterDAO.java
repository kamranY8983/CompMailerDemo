package com.DAO;

import java.sql.DriverManager;
import java.util.Calendar;

import com.bean.User;
import com.util.Formatter;

import java.sql.PreparedStatement;

import java.sql.Connection;


public class RegisterDAO {

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
	
	public static int registerUser(User newUser) {
		System.out.println(newUser);
		int status=0;
		try{
			Connection con=getConnection();
			if(null!=con) {
				System.out.println("Successfully got the connection");
				PreparedStatement pst=con.prepareStatement("insert into COMPANY_MAILER_USER (NAME,EMAIL,PASSWORD,GENDER,DOB,ADDRESSLINE,CITY,STATE,COUNTRY,CONTACT,REGISTEREDDATE,AUTHORIZED) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, newUser.getName());
				pst.setString(2, newUser.getEmail());
				pst.setString(3, newUser.getPassword());
				pst.setString(4, newUser.getGender());
				pst.setDate(5, Formatter.getSqlDate(newUser.getDob()));
				pst.setString(6,newUser.getAddressLine() );
				pst.setString(7, newUser.getCity());
				pst.setString(8, newUser.getState());
				pst.setString(9, newUser.getCountry());
				pst.setLong(10, newUser.getContact());
				pst.setDate(11, Formatter.getCurrentSqlDte());
				pst.setString(12,"yes");
				
				status=pst.executeUpdate();
			}
		}catch (Exception e) {
			System.out.println("Cant insert data due to Exception:" +e);
		}
		return status;
	
		
	}
}
