package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class RestaurappApplication extends ResourceConfig {
	public RestaurappApplication(){
		super();
		register(DeclarativeLinkingFeature.class);
	}

}

