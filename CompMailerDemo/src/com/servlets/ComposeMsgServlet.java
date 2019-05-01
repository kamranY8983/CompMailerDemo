package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.InboxDAO;


@WebServlet("/composemsg")
public class ComposeMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComposeMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		
		HttpSession session=null;
		session=request.getSession(false);
		if(null!=session) {
			
		//get  parameters
		String to=request.getParameter("to");
		String subject=request.getParameter("subject");
		String message=request.getParameter("message");
		
		String sender=(String)session.getAttribute("email");
		
		//check if the recipient exist
		if(InboxDAO.doesExist(to)) {
			//insert into db
			InboxDAO.newMessage(to, sender, subject, message);
			request.setAttribute("msg","<h2>Message sent successfully</h2>");
			request.getRequestDispatcher("inbox").include(request, response);
		}
		else {
			System.out.println("Recipient does not exist");
			request.setAttribute("msg","<h2>Recipient does not exist</h2>");
			request.getRequestDispatcher("inbox").forward(request, response);
			
		}
		
		
		//insert into db
		
		//redirect to inbox
		}
	
	}
}

