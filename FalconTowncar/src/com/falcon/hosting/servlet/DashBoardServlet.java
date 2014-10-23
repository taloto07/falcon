package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.falcon.hosting.doa.User;


public class DashBoardServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public DashBoardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter print = response.getWriter();
		
		String contextPath = this.getContextPath();
		
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = null;
		
		String email = request.getRemoteUser();
		User user = service.getUserByEmail(email);
		if (user.getCustomer() != null){ // customer 
			body = proccessCustomer(request, response);
			System.out.println(user.getEmail());
			System.out.println(user.getCustomer().getCreditCardNumber());
		}else if (user.getDriver() != null){// driver
			body = proccessDriver(request, response);
			System.out.println("processDriver is called!!!!!");
			System.out.println(user.getDriver().getBankName());
		}else{
			body = proccessOwner(request, response);
			page.add("owner", "owner user");
		}
		
		this.checkLogin(page, request);
		page.add("contextPath", contextPath);
		page.add("title", "Dash Board");
		page.add("body", body.render());
		print.print(page.render());
		
		print.flush();
	}
	
	// proccess driver's request
	protected ST proccessDriver(HttpServletRequest request, HttpServletResponse response){
		String contextPath = this.getContextPath();
		
		STGroup templates = this.getSTGroup();
		ST body = templates.getInstanceOf("dashboard_driver");
		body.add("contextPath", contextPath);
		
		String email = request.getRemoteUser();
		User user = service.getUserByEmail(email);
		
		if (user == null){
			body.add("errorMessage", "You are not authorized!");
		}else{
			body.add("user", user);
		}
		
		return body;
	}
	
	// proccess customer's request
	protected ST proccessCustomer(HttpServletRequest request, HttpServletResponse response){
		String contextPath = this.getContextPath();
		
		STGroup templates = this.getSTGroup();
		ST body = templates.getInstanceOf("dashboard_customer");
		body.add("contextPath", contextPath);
		
		String email = request.getRemoteUser(); 
		User user = service.getUserByEmail(email);
		
		if (user == null){
			body.add("errorMessage", "You are not authorized!");
		}else{
			body.add("user", user);
		}
		
		return body;
	}
	
	// proccess owner's request
	protected ST proccessOwner(HttpServletRequest request, HttpServletResponse response){
		String contextPath = this.getContextPath();
		
		STGroup templates = this.getSTGroup();
		ST body = templates.getInstanceOf("dashboard_owner");
		body.add("contextPath", contextPath);
		
		String email = request.getRemoteUser(); 
		User user = service.getUserByEmail(email);
		List<User> users = service.getAllUser();
		
		if (user == null){
			body.add("errorMessage", "You are not authorized!");
		}else{
			body.add("users", users);
		}
		
		return body;
	}
	
	// get user id from request object
	protected String getRequestId(HttpServletRequest request){
		String url = request.getRequestURI();
		int beginIndex = url.lastIndexOf("/") + 1;
		String id = url.substring(beginIndex);
		
		return id;
	}
}
