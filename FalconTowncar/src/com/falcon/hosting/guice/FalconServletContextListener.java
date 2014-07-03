package com.falcon.hosting.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class FalconServletContextListener extends GuiceServletContextListener{

	@Override
	protected Injector getInjector() {
		InjectorGuice.injector = Guice.createInjector(new MainModule());
		return InjectorGuice.injector;
	}

}
