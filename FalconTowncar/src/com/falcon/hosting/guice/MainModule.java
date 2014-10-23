package com.falcon.hosting.guice;

import com.falcon.hosting.servlet.ChamServlet;
import com.falcon.hosting.servlet.ControllerServlet;
import com.falcon.hosting.servlet.DashBoardServlet;
import com.falcon.hosting.servlet.DispatchServlet;
import com.falcon.hosting.servlet.LoginServlet;
import com.falcon.hosting.servlet.LogoutServlet;
import com.falcon.hosting.servlet.RegisterDriverTempServlet;
import com.falcon.hosting.servlet.RegistrationServlet;
import com.falcon.hosting.servlet.SignupServlet;
import com.falcon.hosting.servlet.TestServlet;
import com.falcon.hosting.servlet.ViewServlet;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class MainModule extends ServletModule{
	@Override
	protected void configureServlets(){
		install(new JpaPersistModule("FalconJPA"));
		filter("/*").through(PersistFilter.class);
		
		bind(TestServlet.class).in(Singleton.class);
		bind(DispatchServlet.class).in(Singleton.class);
		bind(ChamServlet.class).in(Singleton.class);
		bind(ControllerServlet.class).in(Singleton.class);
		bind(ViewServlet.class).in(Singleton.class);
		bind(SignupServlet.class).in(Singleton.class);
		bind(LoginServlet.class).in(Singleton.class);
		bind(LogoutServlet.class).in(Singleton.class);
		bind(RegisterDriverTempServlet.class).in(Singleton.class);
		bind(RegistrationServlet.class).in(Singleton.class);
		bind(DashBoardServlet.class).in(Singleton.class);
		
		serve("*.html").with(DispatchServlet.class);
		serve("/").with(DispatchServlet.class);
		serve("/login").with(LoginServlet.class);
		serve("/logout").with(LogoutServlet.class);
		serve("/secure").with(TestServlet.class);
		serve("/cham").with(ChamServlet.class);
		serve("/controller").with(ControllerServlet.class);
		serve("/view").with(ViewServlet.class);
		serve("/signup").with(SignupServlet.class);
		serve("/register-driver").with(RegisterDriverTempServlet.class);
		serve("/registration").with(RegistrationServlet.class);
		serve("/dashboard").with(DashBoardServlet.class);
	}
}
