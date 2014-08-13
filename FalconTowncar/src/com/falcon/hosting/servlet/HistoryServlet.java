package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class HistoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final String errorMessage = "No current records at this time.";
    
    public HistoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HistoryProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HistoryProcess(request, response);
	}

	protected void HistoryProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String contextPath = this.getContextPath();
		
		PrintWriter out = response.getWriter();
		
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = null;
				
		body = templates.getInstanceOf("history");
		body.add("errorMessage", errorMessage);
		page.add("contextPath", contextPath);
		page.add("title", "History");
		page.add("body", body.render());
		
		out.print(page.render());
		out.flush();
	}
}
