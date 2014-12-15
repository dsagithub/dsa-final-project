package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.MediaType;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestauranteResource;

public class Restaurante {

	private int idrestaurante;
	private String direccion;
	private String telefono;
	private String nombre;
	private String email;
	private String horario;
	private String categoria;
	private String provincia;
	private String creador;
	private long creationTime;

	@InjectLinks({
			@InjectLink(resource = RestauranteResource.class, style = Style.ABSOLUTE, rel = "create-restaurante", title = "Crear Restaurante", type = MediaType.RESTAURAPP_API_RESTAURATE),
			@InjectLink(value = "/restaurantes/opinion/{idrestaurante}", style = Style.ABSOLUTE, rel = "opiniones_id", title = "Todas opiniones del restaurante", type = MediaType.RESTAURAPP_API_RESTAURANTE_COLLECTION, bindings = { @Binding(name = "idrestaurante", value = "${instance.idrestaurante}") }),// $-->toda
				})
	private List<Link> links;
	private List<Opinion> opiniones;

	public Restaurante() {
		super();
		opiniones = new ArrayList<>();
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}

	public void addOpinion(Opinion opinion) {
		opiniones.add(opinion);
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getIdrestaurante() {
		return idrestaurante;
	}

	public void setIdrestaurante(int idrestaurante) {
		this.idrestaurante = idrestaurante;
	}

}
