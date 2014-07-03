package com.falcon.hosting.service;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class FalconPersistenceInitializer {
	@Inject
	FalconPersistenceInitializer(PersistService service){
		service.start();
	}
}
