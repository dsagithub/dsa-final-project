package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
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
				usuario = getUserFromDatabase(rs.getString("username"));


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

	private User getUserFromDatabase(String username) { // GET AUTHOR DATABASE

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
	
	
	@POST
	@Path("/{username}")
	@Consumes(MediaType.RESTAURAPP_API_USER)
	@Produces(MediaType.RESTAURAPP_API_USER)
	public Boolean logIn (@PathParam ("username") String username, User usuario){
		
		User user = new User();
	
		try {
			user = getUserFromDatabase(username);
			
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
