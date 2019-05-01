package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.RegisterDAO;
import com.bean.User;

import javafx.scene.chart.PieChart.Data;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get Writer
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
				
		//include header
		request.getRequestDispatcher("header.html").include(request, response);
		
		//get request parameters
		try {
			
		
		User user=new User();
		user.setName(request.getParameter("name").trim());
		user.setEmail(request.getParameter("email")+"@cmail".trim());
		user.setGender(request.getParameter("gender").trim());
		user.setDob(request.getParameter("dob").trim());
		user.setPassword(request.getParameter("password").trim());
		user.setAddressLine(request.getParameter("addressLine").trim());
		user.setCity(request.getParameter("city").trim());
		user.setCountry(request.getParameter("country").trim());
		user.setContact(Long.parseLong(request.getParameter("contact").trim()));
		
		//save to DB
		
		
		int status=RegisterDAO.registerUser(user);
		if(status>0) {
			System.out.println("Successfully inserted the data...");
			pw.println("<h1>Successfully Reqgisterd</h1>");
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
		else {
			System.out.println("Cant Insert the Data..");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		}catch (NumberFormatException e) {
			System.out.println(e);
			pw.println("<h1>Enter the Valid Data</h1>");
		}catch(Exception e) {
			System.out.println("Error occured in setting parameters..." +e);
			request.getRequestDispatcher("error.jsp").include(request, response);
		}
		
		
		//return the response
		
		pw.close();
	}

}
