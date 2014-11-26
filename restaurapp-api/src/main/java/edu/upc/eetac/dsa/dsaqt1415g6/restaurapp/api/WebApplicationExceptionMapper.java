package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.MediaType;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model.RestaurappError;

@Provider
public class WebApplicationExceptionMapper implements
		ExceptionMapper<WebApplicationException> {
	@Override
	public Response toResponse(WebApplicationException exception) {
		RestaurappError error = new RestaurappError(
				exception.getResponse().getStatus(), exception.getMessage());
		return Response.status(error.getStatus()).entity(error)
				.type(MediaType.RESTAURAPP_API_ERROR).build();
	}

}