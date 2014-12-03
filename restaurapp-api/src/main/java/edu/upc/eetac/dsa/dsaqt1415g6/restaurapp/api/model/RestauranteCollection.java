package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

<<<<<<< HEAD
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
=======
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.MediaType;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestauranteResource;


public class RestauranteCollection {
	
	
	@InjectLinks({
		@InjectLink(resource = RestauranteResource.class, style = Style.ABSOLUTE, rel = "crear-restaurante", title = "Crear Restaurante", type = MediaType.RESTAURAPP_API_RESTAURATE),
		@InjectLink(value = "/restaurantes?last={last}", style = Style.ABSOLUTE, rel = "anterior", title = "Restaurantes anteriores", type = MediaType.RESTAURAPP_API_RESTAURANTE_COLLECTION, bindings = { @Binding(name = "last", value = "${instance.anterioresRestaurantes}") }),//$-->toda {}--> valor deseado
		@InjectLink(value = "/restaurantes?next={next}", style = Style.ABSOLUTE, rel = "siguiente", title = "Siguientes restaurantes", type = MediaType.RESTAURAPP_API_RESTAURANTE_COLLECTION, bindings = { @Binding(name = "next", value = "${instance.siguientesRestaurantes}") }) })
private List<Link> links;
private List<Restaurante> restaurantes;
private int siguientesRestaurantes;
private int anterioresRestaurantes;


public RestauranteCollection(){
	super();
	restaurantes = new ArrayList<>();
	
}
public List<Restaurante> getRestaurantes() {
	return restaurantes;
}
public void setRestaurantes(List<Restaurante> restaurantes) {
	this.restaurantes = restaurantes;
}

public void addRestaurante(Restaurante restaurante) {
	restaurantes.add(restaurante);
}

public List<Link> getLinks() {
	return links;
}
public void setLinks(List<Link> links) {
	this.links = links;
}
public List<Restaurante> getRestaurante() {
	return restaurantes;
}
public void setRestaurante(List<Restaurante> restaurante) {
	this.restaurantes = restaurante;
}
public long getSiguientesRestaurantes() {
	return siguientesRestaurantes;
}
public void setSiguientesRestaurantes(int siguientesRestaurantes) {
	this.siguientesRestaurantes = siguientesRestaurantes;
}
public long getAnterioresRestaurantes() {
	return anterioresRestaurantes;
}
public void setAnterioresRestaurantes(int anterioresRestaurantes) {
	this.anterioresRestaurantes = anterioresRestaurantes;
}
>>>>>>> refs/remotes/origin/develop_html

}
