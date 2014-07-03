package com.falcon.hosting.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.falcon.hosting.guice.InjectorGuice;
import com.falcon.hosting.service.FalconService;

@Path("v1")
public class V1 {
	FalconService service;
	
	public V1(){
		service = InjectorGuice.injector.getInstance(FalconService.class);
	}
	
	@Path("version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getStatus(){
		return "Restful service version 1.0";
	}
}
