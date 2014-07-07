package com.falcon.hosting.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.falcon.hosting.doa.User;
import com.falcon.hosting.doa.UsersGroup;

public class ChamServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public ChamServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		List<String> images = new ArrayList<String>();
		File folder = new File(getServletContext().getRealPath("/img/"));
		File[] files = folder.listFiles();
		for (File file: files){
			if (file.isFile())
				images.add(file.getName());
		}
		
		String contextPath = this.getContextPath();
		
		PrintWriter out = response.getWriter();
		
		STGroup templates = this.getSTGroup();
		ST page = templates.getInstanceOf("template");
		ST body = templates.getInstanceOf("cham");
		
//		int indexParameter = Integer.parseInt(request.getParameter("index"));
//		String file = request.getParameter("file");
//		System.out.println("indexParameter: " + indexParameter);
//		System.out.println("file: " + file);
		
		List<User> users = service.getAllUser();		
		
		Random randomGenerator = new Random();
		int imageIndex = randomGenerator.nextInt(images.size());
		int userIndex = randomGenerator.nextInt(users.size());
		
		User u = users.get(userIndex);
		
		body.add("user", u);
		body.add("users", users);
		body.add("contextPath", contextPath);
		body.add("image", images.get(imageIndex));
		page.add("contextPath", contextPath);
		page.add("title", "Cham Image");
		page.add("body", body.render());
		
		out.print(page.render());
		
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
