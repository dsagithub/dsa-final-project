package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model;

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
<<<<<<< HEAD
	private long creationTime;
	
	public long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
=======
	
>>>>>>> refs/remotes/origin/develop_html
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
	public int getIdrestaurante() {
		return idrestaurante;
	}
	public void setIdrestaurante(int idrestaurante) {
		this.idrestaurante = idrestaurante;
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

}
