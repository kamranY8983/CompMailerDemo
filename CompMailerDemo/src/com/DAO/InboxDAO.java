package com.DAO;

import com.bean.Message;
import com.util.Formatter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InboxDAO {
	
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
	

	public static int newMessage(String to,String sender,String subject, String message) {
		int status=0;
		try {
			Connection con=getConnection();
			if(con!=null){
				PreparedStatement pst=con.prepareStatement("insert into COMPANY_MAILER_MESSAGE (sender,receiver,subject,message,messagedate) values(?,?,?,?,?)");
				pst.setString(1, sender);
				pst.setString(2, to);
				pst.setString(3, subject);
				pst.setString(4, message);
				pst.setDate(5, Formatter.getCurrentSqlDte());
				
				System.out.println("Query to inserrt is:"+pst);
				status=pst.executeUpdate();
				
			}
			
		}catch (Exception e) {		
			System.out.println(e);
		}
		
		return status;
	}
	
	public static boolean doesExist(String email) {
		boolean isExist=false;
		try {
			Connection con=getConnection();
			if(con!=null) {
				PreparedStatement pst=con.prepareStatement("select * from COMPANY_MAILER_USER where email=?");
				pst.setString(1, email);
				System.out.println("Query is:"+ pst);
				ResultSet rs=pst.executeQuery();
				if(rs.next()) {
					System.out.println("User Exist"+ rs.getInt(1)+"..."+rs.getString(2));
					isExist=true;
				}
				else {
					System.out.println("User not Found");
				}
			}
			
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return isExist;
	}
	
	public static List<Message> getMessage(String email) {
		List<Message> msgList=new ArrayList<Message>();
		try {
			Connection con=getConnection();
			if(con!=null) {
				PreparedStatement pst=con.prepareStatement("select m.id, u.name, m.subject,m.message from COMPANY_MAILER_MESSAGE as m inner join COMPANY_MAILER_USER as u on m.sender=u.email where  receiver=?;");
				pst.setString(1, email);
				ResultSet rs=pst.executeQuery();
				int i=0;
				System.out.println("Qquery to execute"+pst);
				while(rs.next()) {
					i++;
					System.out.println("Msg"+i);
					Message msg=new Message();
					msg.setId(rs.getInt(1));
					msg.setSender(rs.getString(2));
					msg.setSubject(rs.getString(3));
					msg.setMessage(rs.getString(4));
					msgList.add(msg);
					System.out.print(msg);
				}
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return msgList;
	}


	public static int deleteMsg(String email, int id) {
		int result=0;
		Connection con=getConnection();
		if(con!=null) {
			try {
			PreparedStatement pst=con.prepareStatement("delete from COMPANY_MAILER_MESSAGE where id=?;");
			pst.setInt(1, id);
			//pst.setString(2, email);
			System.out.println("Delete Query is:" +pst);
			result=pst.executeUpdate();
			}catch (Exception e) {
				System.out.println("Cannot delete msg due to "+e);
			}
		}
		else {
			System.out.println("Cannot connect to DB");
		}
		return result;
		
	}


	public static List<Message> getSentMessage(String email) {
		List<Message> msgList=new ArrayList<Message>();
		try {
			Connection con=getConnection();
			if(con!=null) {
				PreparedStatement pst=con.prepareStatement("select m.id, u.name, m.subject,m.message from COMPANY_MAILER_MESSAGE as m inner join COMPANY_MAILER_USER as u on m.receiver=u.email where  sender=?;");
				pst.setString(1, email);
				ResultSet rs=pst.executeQuery();
				int i=0;
				System.out.println("Qquery to execute"+pst);
				while(rs.next()) {
					i++;
					System.out.println("Msg"+i);
					Message msg=new Message();
					msg.setId(rs.getInt(1));
					msg.setSender(rs.getString(2));
					msg.setSubject(rs.getString(3));
					msg.setMessage(rs.getString(4));
					msgList.add(msg);
					System.out.print(msg);
				}
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return msgList;
	}
}
