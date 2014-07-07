package com.falcon.hosting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.falcon.hosting.doa.User;

public class ControllerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public ControllerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
//		out.print("<!DOCTYPE html>");
//		out.print("<html>");
//		out.print("<head>");
//		out.print("<title>Controller Page</title>");
//		out.print("</head>");
//		out.print("<body>");
		out.print("My name is chamnap.<br/>");
		out.print("I'm 7 years old.<br/>");
//		out.print("</body>");
//		out.print("</html>");
		
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
