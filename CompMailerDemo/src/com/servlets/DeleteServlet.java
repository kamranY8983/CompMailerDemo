package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.InboxDAO;


@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		if(session!=null) {
			try {
				String email=(String)session.getAttribute("email");
				int id=Integer.parseInt(request.getParameter("id"));
				int result=InboxDAO.deleteMsg(email,id);
				if(result>0){
					request.setAttribute("Message", "Message with id"+id+" deleted Successfully");
					
				}
				else {
					request.setAttribute("Message", "Message with id"+id+" was unable to be deleted");
				}
			}catch (Exception e) {
				System.out.println(e);
			}
			
			request.getRequestDispatcher("inbox").forward(request, response);;
		}
		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}


}
