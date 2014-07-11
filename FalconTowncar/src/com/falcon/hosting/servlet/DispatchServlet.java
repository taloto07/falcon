package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;


public class DispatchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public static Map<String, String> pages = new HashMap<String, String>();
    
    public DispatchServlet() {
        super();
        pages.put("home", "Home");
		pages.put("about", "About Us");
		pages.put("contact", "Contact Us");
		pages.put("page404", "Page 404");
		pages.put("termsandconditions", "Terms & Conditions");
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
		
		String bodyPage = this.getPage(request);
		ST body = templates.getInstanceOf(bodyPage);
		body.add("contextPath", contextPath);
		
		if (!bodyPage.equalsIgnoreCase("page404") && !bodyPage.equalsIgnoreCase("termsandconditions"))
			page.add(bodyPage, "active");
		page.add("contextPath", contextPath);
		page.add("title", pages.get(bodyPage));
		page.add("body", body.render());
		
		print.print(page.render());
		
		print.flush();
	}
	
	protected String getPage(HttpServletRequest request){
		String url = request.getRequestURI();
		int beginIndex = url.lastIndexOf("/") + 1;
		int endIndex = url.lastIndexOf(".");
		endIndex = endIndex < 0 ? url.length() : endIndex;
		String page = url.substring(beginIndex, endIndex);
		
		if (page.equalsIgnoreCase("")){
			return "home";
		}
		
		if (pages.containsKey(page)){
			return page;
		}
		
		return "page404";
	}

}
