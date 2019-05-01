package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.InboxDAO;
import com.bean.Message;

/**
 * Servlet implementation class SentMessages
 */
@WebServlet("/sentMsgs")
public class SentMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session!=null) {
			//get the messages
			List<Message> msgList=InboxDAO.getSentMessage((String)session.getAttribute("email"));
			if(msgList!=null) {
				System.out.println("TOtal no.Sent  of msgs"+msgList.size());
				request.setAttribute("Messages", msgList);
			}
			else {
				request.setAttribute("Message", "No Sent Messages");
			}
			request.getRequestDispatcher("jsp/sent.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		 
	}



}
