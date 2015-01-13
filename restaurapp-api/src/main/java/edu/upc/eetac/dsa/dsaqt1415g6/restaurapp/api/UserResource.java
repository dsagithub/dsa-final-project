package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.codec.digest.DigestUtils;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model.User;

@Path("/users")
public class UserResource {
	
	@Context
	private SecurityContext security;

	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	
	private String INSERT_USER_QUERY = "insert into users (username,userpass,nombre,email,provincia,foto)values(?, MD5(?),?,?,?,?)";

	@POST
	@Consumes(MediaType.RESTAURAPP_API_USER)
	@Produces(MediaType.RESTAURAPP_API_USER)
	
	public User createUser(User usuario) {

		System.out.println("Creando usuario....");
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_USER_QUERY,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, usuario.getUsername());
			stmt.setString(2, usuario.getPassword());
			stmt.setString(3, usuario.getNombre());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getProvincia());
			stmt.setString(6, usuario.getUbic_foto());
			

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				usuario = getUserFromDatabase(rs.getString("username"), true);


			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
		return usuario;
	}
	
	
	
	private String GET_USER_BY_USERNAME = "select * from users where username=?";

	private User getUserFromDatabase(String username, boolean password) { // GET AUTHOR DATABASE

		User usuario = new User();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_USER_BY_USERNAME);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				
					usuario.setUsername(rs.getString("username"));
					usuario.setEmail(rs.getString("email"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setPassword(rs.getString("userpass"));
					usuario.setProvincia(rs.getString("provincia"));
					usuario.setUbic_foto(rs.getString("foto"));
					usuario.setCont_opinion(rs.getInt("contador_O_U"));
				
				
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
		return usuario;

	}
	
	
	/*
	 * Manu parte de login
	 */
	
	@Path("/login")
	@POST
	@Produces(MediaType.RESTAURAPP_API_USER)
	@Consumes(MediaType.RESTAURAPP_API_USER)
	public User login(User user) {
		
		System.out.println("Entramos al metodo");
		if (user.getUsername() == null || user.getPassword() == null)
			throw new BadRequestException(
					"username and password cannot be null.");
		
		System.out.println(user.getPassword()+"pass");
		System.out.println(user.getUsername()+"name");

		String pwdDigest = DigestUtils.md5Hex(user.getPassword()); //calculamos el md5 de la contraseña
		String storedPwd = getUserFromDatabase(user.getUsername(), true) //nos devuelve el pasword en md5 y en hexadecimal y se puede comparar con el de la base de datos y que sean iguales
				.getPassword();
		
		user.setLoginSuccesful(pwdDigest.equals(storedPwd)); //ponemos el atributo de login si es true o false si coinciden
		
		System.out.println(user.isLoginSuccesful()+"logSUcces");

		 
		user.setPassword(null); //cuando nos pasan el user, de manera que la contraseña no aparece
		System.out.println("Usuario logueado");
		return user;
	}
	
	
	@POST
	@Path("/{username}")
	@Consumes(MediaType.RESTAURAPP_API_USER)
	@Produces(MediaType.RESTAURAPP_API_USER)
	public Boolean logIn (@PathParam ("username") String username, User usuario){
		
		User user = new User();
	
		try {
			user = getUserFromDatabase(username,true);
			
			if(user.getPassword()==usuario.getPassword()){
				user.setLoginSuccesful(true);
				return true;
			}
			else{
				user.setLoginSuccesful(false);
				return false;
			}
		} finally {
		}
	}
	
	
	
	
	
	

}
