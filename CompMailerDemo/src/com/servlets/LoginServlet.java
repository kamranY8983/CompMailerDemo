package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.LoginDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get Writer
		PrintWriter out=response.getWriter();
		//include header
		request.getRequestDispatcher("header.html").include(request, response);
		
		//get the parameters
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		//verify the details
		String name=LoginDAO.validate(email, password);
		if(name!=null) {
			request.getSession().setAttribute("login", true);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("name", name);
			out.println("<h1>Successfully Logged In</h1>");
			request.getRequestDispatcher("inbox").forward(request, response);
		}
		else {
			request.setAttribute("msg", "No Such User... Please Try again with valid credentials");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		//take descision
		out.close();
	}

}
