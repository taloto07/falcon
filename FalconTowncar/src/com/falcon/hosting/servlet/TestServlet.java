package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.falcon.hosting.doa.User;
import com.falcon.hosting.service.FalconService;
import com.google.inject.Inject;


public class TestServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public TestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("Hello Falcon.<br/>");
		
		String contextPath = this.getContextPath();
		List<User> users = service.getAllUser();
		
		out.print("ContextPath: " + contextPath + "<br/>");
		for (User u: users){
			out.println("Firstname: " + u.getFirstname() + " ");
			out.println("Lastname: " + u.getLastname() + " ");
			out.println("Email: " + u.getEmail() + "<br/>");
		}
		
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
