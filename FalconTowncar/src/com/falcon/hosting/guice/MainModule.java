package com.falcon.hosting.guice;

import com.falcon.hosting.servlet.TestServlet;
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
		
		serve("/").with(TestServlet.class);
		serve("/index.html").with(TestServlet.class);
		serve("/secure").with(TestServlet.class);
	}
}
