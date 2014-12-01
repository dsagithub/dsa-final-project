package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestauranteResource;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.MediaType;



public class RestauranteCollection {
	
	
	@InjectLinks({
		@InjectLink(resource = RestauranteResource.class, style = Style.ABSOLUTE, rel = "create-restaurante", title = "Crear Restaurante", type = MediaType.RESTAURAPP_API_RESTAURATE),
		@InjectLink(value = "/restaurantes?last={last}", style = Style.ABSOLUTE, rel = "anterior", title = "Restaurantes Anteriores", type = MediaType.RESTAURAPP_API_RESTAURANTE_COLLECTION, bindings = { @Binding(name = "last", value = "${instance.oldestTimestamp}") }),//$-->toda {}--> valor deseado
		@InjectLink(value = "/restaurantes?next={next}", style = Style.ABSOLUTE, rel = "siguiente", title = "Siguientes Restaurantes", type = MediaType.RESTAURAPP_API_RESTAURANTE_COLLECTION, bindings = { @Binding(name = "next", value = "${instance.newestTimestamp}") }) })
	private List<Link> links;
	private List<Restaurante> restaurantes;
	private long newestTimestamp;
	private long oldestTimestamp;
	
	public RestauranteCollection() {
		super();
		restaurantes = new ArrayList<>();
	}
	
	public void addRestaurantes(Restaurante restaurante){
		restaurantes.add(restaurante);
	}
	
	
	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}
	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
	public long getNewestTimestamp() {
		return newestTimestamp;
	}
	public void setNewestTimestamp(long newestTimestamp) {
		this.newestTimestamp = newestTimestamp;
	}
	public long getOldestTimestamp() {
		return oldestTimestamp;
	}
	public void setOldestTimestamp(long oldestTimestamp) {
		this.oldestTimestamp = oldestTimestamp;
	}

}
