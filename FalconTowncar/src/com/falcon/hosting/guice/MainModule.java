package com.falcon.hosting.guice;

import com.falcon.hosting.servlet.ChamServlet;
import com.falcon.hosting.servlet.ControllerServlet;
import com.falcon.hosting.servlet.DispatchServlet;
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
		
		serve("/").with(DispatchServlet.class);
		serve("*.html").with(DispatchServlet.class);
		serve("/secure").with(TestServlet.class);
		serve("/cham").with(ChamServlet.class);
		serve("/controller").with(ControllerServlet.class);
		serve("/view").with(ViewServlet.class);
	}
}
