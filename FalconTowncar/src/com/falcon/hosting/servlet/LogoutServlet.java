package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.falcon.hosting.doa.User;

public class LogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String contextPath = this.getContextPath();
		
		PrintWriter out = response.getWriter();
		
		String email = request.getRemoteUser();
		if (email == null) {
			response.sendRedirect(contextPath);
		}else{
	
			STGroup templates = this.getSTGroup();
	
			ST page = templates.getInstanceOf("template");
			ST body = templates.getInstanceOf("logmessage");
			
			request.logout();
				
			User user = service.getUserByEmail(email);
			
			String firstName = user.getFirstname();
			String lastName = user.getLastname();
			
			body.add("message", "See you soon " + firstName + " " + lastName);
			
			this.checkLogin(page, request);
			page.add("contextPath", contextPath);
			page.add("title", "Log Out");
			page.add("body", body.render());
	
			out.print(page.render());
			out.flush();
		}
	}
}
