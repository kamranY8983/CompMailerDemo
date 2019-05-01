package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=null;
		session=request.getSession(false);
		if(session!=null){
			session.invalidate();
			System.out.println("Successfully Logged out");
			request.setAttribute("msg", "Successfully Logged out");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else {
			System.out.println("Not Logged in");
			request.setAttribute("msg", "Not Logged in");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
