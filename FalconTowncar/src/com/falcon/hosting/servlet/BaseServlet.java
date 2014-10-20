package com.falcon.hosting.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.falcon.hosting.doa.User;
import com.falcon.hosting.service.FalconService;
import com.google.inject.Inject;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	FalconService service;
	
    public BaseServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected String getContextPath(){
		String contextPath = getServletContext().getContextPath();
		if (!contextPath.endsWith("/"))
			contextPath = contextPath.concat("/");
		return contextPath;
	}
	
	protected STGroup getSTGroup(){
		String path = getServletContext().getRealPath("WEB-INF/templates/");
		return new STGroupDir(path, '$', '$');
	}
	
	protected String compressHTML(String content){
		HtmlCompressor compresor = new HtmlCompressor();
		return compresor.compress(content);
	}
	
	protected void checkLogin(ST page, HttpServletRequest request){
		if (request.getRemoteUser() != null){
			User user = service.getUserByEmail(request.getRemoteUser());
			String username = user.getFirstname() + " " + user.getLastname();
			page.add("username", username);
			page.add("login", "true");
			page.add("useremail", user.getEmail());
		}
	}
}
