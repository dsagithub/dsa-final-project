package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model;

public class User {
	
	private String username;
	private String password;
	private String nombre;
	private String email;
	private String provincia;
	private int cont_opinion;
	private String ubic_foto;
	private boolean loginSuccesful;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public int getCont_opinion() {
		return cont_opinion;
	}
	public void setCont_opinion(int cont_opinion) {
		this.cont_opinion = cont_opinion;
	}
	public String getUbic_foto() {
		return ubic_foto;
	}
	public void setUbic_foto(String ubic_foto) {
		this.ubic_foto = ubic_foto;
	}
	public boolean isLoginSuccesful() {
		return loginSuccesful;
	}
	public void setLoginSuccesful(boolean loginSuccesful) {
		this.loginSuccesful = loginSuccesful;
	}

}
