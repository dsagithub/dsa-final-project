package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestauranteResource;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestaurappRootAPIResource;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.MediaType;

public class RestaurappRootAPI {
	@InjectLinks({//(1)al ser absoluta veremos la uri completa;
		@InjectLink(resource = RestaurappRootAPIResource.class, style = Style.ABSOLUTE, rel = "self bookmark home", title = "Beeter Root API", method = "getRootAPI"),
		@InjectLink(resource = RestauranteResource.class, style = Style.ABSOLUTE, rel = "stings", title = "Latest stings", type = MediaType.RESTAURAPP_API_RESTAURANTE_COLLECTION),
		@InjectLink(resource = RestauranteResource.class, style = Style.ABSOLUTE, rel = "create-stings", title = "Latest stings", type = MediaType.RESTAURAPP_API_RESTAURATE) })
	private List<Link> links;

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}