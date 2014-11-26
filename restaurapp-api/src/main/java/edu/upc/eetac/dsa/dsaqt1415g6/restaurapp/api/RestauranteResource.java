package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.codec.digest.DigestUtils;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model.Restaurante;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model.RestauranteCollection;


@Path("/restaurantes")
public class RestauranteResource {
	
	@Context
	private SecurityContext security;

	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String GET_RESTAURATES_QUERY = "select rest.*, u.username from restaurantes rest, users u where u.username=rest.creador and rest.idrestaurante < ifnull(?, rest.idrestaurante) order by  idrestaurante desc limit ?";
	private String GET_RESTAURATES_QUERY_FROM_LAST = "select rest.*, u.username from restaurantes rest, users u where u.username=rest.creador and rest.idrestaurante > ? order by idrestaurante desc";

	@GET
	@Produces(MediaType.RESTAURAPP_API_RESTAURANTE_COLLECTION)
	public RestauranteCollection getRestaurantes(@QueryParam("length") int length,
			@QueryParam("last") int before, @QueryParam("next") int after) {
		RestauranteCollection restaurantes = new RestauranteCollection();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			boolean updateFromLast = after > 0;
			stmt = updateFromLast ? conn
					.prepareStatement(GET_RESTAURATES_QUERY_FROM_LAST) : conn
					.prepareStatement(GET_RESTAURATES_QUERY);
			if (updateFromLast) {
				stmt.setInt(1, after);
			} else {
				if (before > 0)
					stmt.setInt(1, before);
				else
					stmt.setInt(1, Integer.parseInt(null));
				length = (length <= 0) ? 3 : length;// si lenght menor a 0 coge valor a 5 sino coge valor por defecto de lenght
				stmt.setInt(2, length);
			}
			ResultSet rs = stmt.executeQuery();
			boolean first = true;
			int ultimoidrestaurante = 0;
			
			while (rs.next()) {
				Restaurante restaurante = new Restaurante();
				restaurante.setNombre(rs.getString("nombre"));
				restaurante.setCategoria(rs.getString("categoria"));
				restaurante.setDireccion(rs.getString("direccion"));
				restaurante.setEmail(rs.getString("email"));
				restaurante.setHorario(rs.getString("horario"));
				ultimoidrestaurante = rs.getInt("idrestaurante");
				restaurante.setIdrestaurante(ultimoidrestaurante);
				restaurante.setProvincia(rs.getString("provincia"));
				restaurante.setTelefono(rs.getString("telefono"));
				restaurante.setCreador(rs.getString("username"));
				if (first) {
					first = false;
					restaurantes.setSiguientesRestaurantes(restaurante.getIdrestaurante());
				}
				restaurantes.addRestaurante(restaurante);
			}
			restaurantes.setAnterioresRestaurantes(ultimoidrestaurante);
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

		return restaurantes;
	}


}
