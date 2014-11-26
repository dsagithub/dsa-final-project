package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model;

public class Opinion {
	
	private int idopinion;
	private int idrestaurante;
	private String titulo;
	private int puntuacion;
	private String texto;
	private String fecha_estancia;
	private String username;
	private int utilidad;
	private int noutilidad;
	private long creation_timestamp;
	
	public int getIdopinion() {
		return idopinion;
	}
	public void setIdopinion(int idopinion) {
		this.idopinion = idopinion;
	}
	public int getIdrestaurante() {
		return idrestaurante;
	}
	public void setIdrestaurante(int idrestaurante) {
		this.idrestaurante = idrestaurante;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getFecha_estancia() {
		return fecha_estancia;
	}
	public void setFecha_estancia(String fecha_estancia) {
		this.fecha_estancia = fecha_estancia;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUtilidad() {
		return utilidad;
	}
	public void setUtilidad(int utilidad) {
		this.utilidad = utilidad;
	}
	public int getNoutilidad() {
		return noutilidad;
	}
	public void setNoutilidad(int noutilidad) {
		this.noutilidad = noutilidad;
	}
	public long getCreation_timestamp() {
		return creation_timestamp;
	}
	public void setCreation_timestamp(long creation_timestamp) {
		this.creation_timestamp = creation_timestamp;
	}

}
