package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model.RestaurappRootAPI;

@Path("/")
public class RestaurappRootAPIResource {
	@GET
	public RestaurappRootAPI getRootAPI() {
		RestaurappRootAPI api = new RestaurappRootAPI();
		return api;
	}
}
