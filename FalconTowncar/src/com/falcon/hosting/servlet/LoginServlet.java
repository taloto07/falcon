package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final String errorMessage = "Incorrect email or password!";
	private final String loginSuccess = "Log in successfully!";
    
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loginProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loginProcess(request, response);
	}

	protected void loginProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String contextPath = this.getContextPath();
		
		PrintWriter out = response.getWriter();
		
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = null;
		
		if (request.getMethod().equalsIgnoreCase("post")){	// post request
			if (request.getRemoteUser() == null){	// login fail
				body = templates.getInstanceOf("login");
				body.add("errorMessage", errorMessage);
			}else{	// login success
				body = templates.getInstanceOf("logmessage");
				body.add("message", loginSuccess);
			}
		}else{	// get request
			if (request.getRemoteUser() == null){	// not yet login
				body = templates.getInstanceOf("login");
			}else{
				body = templates.getInstanceOf("logmessage");
				body.add("message", loginSuccess);
			}
		}
		
		
		page.add("contextPath", contextPath);
		page.add("title", "Login");
		page.add("body", body.render());
		
		out.print(page.render());
		out.flush();
	}
}
