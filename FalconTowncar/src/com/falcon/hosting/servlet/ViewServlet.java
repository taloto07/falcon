package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class ViewServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public ViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter(); 
		
		String contextPath = this.getContextPath();

		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = templates.getInstanceOf("view");
		
		body.add("name", "Chamnap");
		body.add("year", 18);
		
		page.add("contextPath", contextPath);
		page.add("title", "View Page Test!");
		
		page.add("body", body.render());
		
		out.print(page.render());
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
