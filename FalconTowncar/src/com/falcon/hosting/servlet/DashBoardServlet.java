package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.falcon.hosting.doa.Comment;
import com.falcon.hosting.doa.Job;
import com.falcon.hosting.doa.User;
import com.falcon.hosting.doa.Vehicle;


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
		/*
		 * *******************************
		 *  customer
		 * ******************************* 
		 */
		if (user.getCustomer() != null){  
			
			body = proccessCustomer(request, response);
			
			ST script = templates.getInstanceOf("javascript");
			script.add("contextPath", contextPath);
			script.add("filename", "post_comment.js");
			page.add("script", script.render());
		/*
		 * *******************************
		 *  driver
		 * ******************************* 
		 */	
		}else if (user.getDriver() != null){
			
			body = proccessDriver(request, response);
			
			ST script = templates.getInstanceOf("javascript");
			script.add("contextPath", contextPath);
			script.add("filename", "post_comment.js");
			page.add("script", script.render());
		
		/*
		 * *******************************
		 *  owner
		 * ******************************* 
		 */
		}else{
			body = proccessOwner(request, response);
			page.add("owner", "owner user");
			
			ST script = templates.getInstanceOf("javascript");
			script.add("contextPath", contextPath);
			script.add("filename", "load-user-detail-info.js");
			page.add("script", script.render());
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
		
		// get email from logged in user
		String email = request.getRemoteUser();
		// get email from URL
		String requestEmail = this.getRequestEmail(request);
		
		// if no email from url or requested email and logged in email doesn't match
		if (requestEmail == null || !email.equalsIgnoreCase(requestEmail)){
			body.add("errorMessage", "You are not authorized!");
		}else{
			User user = service.getUserByEmail(email);

			// get current vehicle
			Vehicle currentVehicle = user.getDriver().getCurrentVehicle();
			
			// get all vehicles but not current vehicle
			List<Vehicle> otherVehicles = user.getDriver().getVehicles();
			otherVehicles.remove(currentVehicle);
			
			// get all jobs
			List<Job> jobs = user.getDriver().getJobs();
			for (Job job: jobs){
				List<Comment> comments = job.getComments();
				Collections.sort(comments, new Comparator<Comment>(){

					@Override
					public int compare(Comment o1, Comment o2) {
						// TODO Auto-generated method stub
						return o1.getDate().compareTo(o2.getDate());
					}
					
				});
			}
			
			body.add("user", user);
			body.add("currentVehicle", currentVehicle);
			body.add("otherVehicles", otherVehicles);
			body.add("jobs", jobs);
		}
		
		return body;
	}
	
	// proccess customer's request
	protected ST proccessCustomer(HttpServletRequest request, HttpServletResponse response){

		String contextPath = this.getContextPath();
		
		STGroup templates = this.getSTGroup();
		ST body = templates.getInstanceOf("dashboard_customer");
		body.add("contextPath", contextPath);
		
		// get email from logged in user
		String email = request.getRemoteUser();
		// get email from URL
		String requestEmail = this.getRequestEmail(request);
		
		// if no email from url or requested email and logged in email doesn't match
		if (requestEmail == null || !email.equalsIgnoreCase(requestEmail)){
			
			body.add("errorMessage", "You are not authorized!");
			
		}else{
			User user = service.getUserByEmail(email); // get user
			List<Job> jobs = user.getCustomer().getJobs();
			for (Job job: jobs){
				List<Comment> comments = job.getComments();
				Collections.sort(comments, new Comparator<Comment>(){

					@Override
					public int compare(Comment o1, Comment o2) {
						// TODO Auto-generated method stub
						return o1.getDate().compareTo(o2.getDate());
					}
					
				});
			}
			
			body.add("user", user);
			body.add("jobs", jobs);
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
	protected String getRequestEmail(HttpServletRequest request){
		String url = request.getRequestURI();
		int beginIndex = url.lastIndexOf("/") + 1;
		String email = url.substring(beginIndex);
		
		if (StringUtils.isAlphanumeric(email)){
			return null;
		}
		
		return email;
	}
}
