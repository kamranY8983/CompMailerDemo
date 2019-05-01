package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class InboxServlet
 */
@WebServlet("/inbox")
public class InboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InboxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get writer
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		//include header
		request.getRequestDispatcher("header.html").include(request, response);
		
		//check for valid session
		HttpSession session=null;
		session=request.getSession(false);
		if(session!=null) {
			//get the messages
			List<Message> msgList=InboxDAO.getMessage((String)session.getAttribute("email"));
			if(msgList!=null) {
				System.out.println("TOtal no. of msgs"+msgList.size());
				request.setAttribute("Messages", msgList);
			}
			else {
				request.setAttribute("Message", "No Messages");
			}
			request.getRequestDispatcher("jsp/inbox.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
