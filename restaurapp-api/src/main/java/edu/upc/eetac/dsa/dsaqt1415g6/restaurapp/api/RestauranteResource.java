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

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model.Opinion;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model.Restaurante;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.model.RestauranteCollection;




@Path("/restaurantes")
public class RestauranteResource {
	
	@Context
	private SecurityContext security;

	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String GET_RESTAURATES_QUERY = "select rest.*, u.username, op.* from restaurantes rest, users u, opiniones op where u.username=rest.creador and rest.idrestaurante=op.idrest order by  rest.idrestaurante desc limit ?";
	private String GET_RESTAURATES_QUERY_FROM_LAST = "select rest.*, u.username, op.* from restaurantes rest, users u, opiniones op where u.username=rest.creador and rest.idrestaurante=op.idrest and rest.idrestaurante > ? order by rest.idrestaurante desc";

	@GET
	@Produces(MediaType.RESTAURAPP_API_RESTAURANTE_COLLECTION)
	public RestauranteCollection getRestaurantes(@QueryParam("length") int length,
			@QueryParam("last") int before, @QueryParam("next") int after) {
		
		
		RestauranteCollection restaurantes = new RestauranteCollection();
		
		System.out.println("Dentro de la funcion GET....");

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		System.out.println("Conectado a la BD....");

		PreparedStatement stmt = null;
		try {
			boolean updateFromLast = after > 0;
			System.out.println("Dentro del try con valor updateFromLast...."+ updateFromLast);

			stmt = updateFromLast ? conn
					.prepareStatement(GET_RESTAURATES_QUERY_FROM_LAST) : conn
					.prepareStatement(GET_RESTAURATES_QUERY);
			if (updateFromLast) {
				stmt.setInt(1, after);
			}else{
				length = (length <= 0) ? 8 : length;// si lenght menor a 0 coge valor a 5 sino coge valor por defecto de lenght
				stmt.setInt(1, length);
			}
			ResultSet rs = stmt.executeQuery();
			boolean first = true;
			long oldestTimestamp = 0;
			System.out.println("Apunto de entrar en while...");

			while (rs.next()) {
				System.out.println("dentro del while");

				Restaurante restaurante;
				
				if (restaurantes.getRestaurante(rs.getInt("idrestaurante"))==null){
					
						restaurante = new Restaurante();
						
						restaurante.setNombre(rs.getString("nombre"));
						restaurante.setCategoria(rs.getString("categoria"));
						restaurante.setDireccion(rs.getString("direccion"));
						restaurante.setEmail(rs.getString("email"));
						
						System.out.println("Nombre del restaurante...."+ restaurante.getNombre());
						restaurante.setHorario(rs.getString("horario"));
						oldestTimestamp = rs.getTimestamp("creation_timestamp").getTime();
						restaurante.setCreationTime(oldestTimestamp);
						restaurante.setIdrestaurante(rs.getInt("idrestaurante"));
						restaurante.setProvincia(rs.getString("provincia"));
						restaurante.setTelefono(rs.getString("telefono"));
						restaurante.setCreador(rs.getString("username"));
						if (first) {
							first = false;
							restaurantes.setNewestTimestamp(restaurante.getCreationTime());
						}
				}
				else{
									
						restaurante = restaurantes.getRestaurante(rs.getInt("idrestaurante"));
									
					}
				Opinion opinion = new Opinion();
				
				opinion.setFecha_estancia(rs.getString("mes_estancia"));
				opinion.setCreation_timestamp(rs.getTimestamp("opinion_creation").getTime());
				opinion.setIdopinion(rs.getInt("idopinion"));
				opinion.setIdrestaurante(rs.getInt("idrest"));
				opinion.setNoutilidad(rs.getInt("cont_noutilidad"));
				opinion.setPuntuacion(rs.getInt("puntuacion"));
				opinion.setTexto(rs.getString("texto"));
				opinion.setTitulo(rs.getString("titulo"));
				opinion.setUsername(rs.getString("username"));
				opinion.setUtilidad(rs.getInt("cont_utilidad"));
				
				
				restaurante.addOpinion(opinion);
				
				if (restaurantes.getRestaurante(rs.getInt("idrestaurante"))==null){
					restaurantes.addRestaurantes(restaurante);
				}
			}
			restaurantes.setOldestTimestamp(oldestTimestamp);
			System.out.println("paso final");
			System.out.println("Anteriores del restaurante...."+ restaurantes.getOldestTimestamp());
			System.out.println("Siguientes del restaurante...."+ restaurantes.getNewestTimestamp());

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
		System.out.println("returning");

		return restaurantes;
	}

	
	
	@GET
	@Path("/{idrestaurante}")
	@Produces(MediaType.RESTAURAPP_API_RESTAURATE)
	public Response getRestaurante(@PathParam("idrestaurante") String idRestaurante,
			@Context Request request) {
		// Create CacheControl
		CacheControl cc = new CacheControl();

		Restaurante restaurante = getRestauranteFromDatabase(idRestaurante);

		EntityTag eTag = new EntityTag(Long.toString(restaurante.getCreationTime()));

		Response.ResponseBuilder rb = request.evaluatePreconditions(eTag);

		// If ETag matches the rb will be non-null;
		// Use the rb to return the response without any further processing
		if (rb != null) {
			return rb.cacheControl(cc).tag(eTag).build();
		}

		// If rb is null then either it is first time request; or resource is
		// modified
		// Get the updated representation and return with Etag attached to it
		rb = Response.ok(restaurante).cacheControl(cc).tag(eTag);

		return rb.build();
	}
	
	
	
	private String DELETE_RESTAURANTE_QUERY = "delete from restaurantes where idrestaurante=?";

	@DELETE
	@Path("/{idrestaurante}")
	public void deleteRestaurante(@PathParam("idrestaurante") String idrestaurante) { // DELETE
		
		validateCreadorOaDmin(idrestaurante);
		System.out.println("Borrando restaurnte....");
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		Restaurante restaurante = new Restaurante();
		restaurante = getRestauranteFromDatabase(idrestaurante);
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_RESTAURANTE_QUERY);
			stmt.setInt(1, Integer.valueOf(idrestaurante));

			int rows = stmt.executeUpdate();

			if (rows == 0)
				;// Deleting inexistent sting
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
	}
	
	
	
	private String UPDATE_RESTAURANTE_QUERY = "update restaurantes set nombre=ifnull(?, nombre), direccion=ifnull(?, direccion), telefono=ifnull(?, telefono), email=ifnull(?, email), horario=ifnull(?, horario), categoria=ifnull(?, categoria), provincia=ifnull(?, provincia) where idrestaurante=?";

	
	@PUT
	@Path("/{idrestaurante}")
	@Consumes(MediaType.RESTAURAPP_API_RESTAURATE)
	@Produces(MediaType.RESTAURAPP_API_RESTAURATE)
	public Restaurante updateRestaurante(@PathParam("idrestaurante") String idrestaurante, Restaurante restaurante) { // UPDATE
		
		validateCreadorOaDmin(idrestaurante);
		System.out.println("Actualizando restaurante....");

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE_RESTAURANTE_QUERY);
			stmt.setString(1, restaurante.getNombre());
			stmt.setString(2, restaurante.getDireccion());
			stmt.setString(3, restaurante.getTelefono());
			stmt.setString(4, restaurante.getEmail());
			stmt.setString(5, restaurante.getHorario());
			stmt.setString(6, restaurante.getCategoria());
			stmt.setString(7, restaurante.getProvincia());
			stmt.setString(8, idrestaurante);

			int rows = stmt.executeUpdate();
			if (rows == 1) {
				restaurante = getRestauranteFromDatabase(idrestaurante);
			}


			else {
				throw new NotFoundException("No existe ningun restaurante con id="
						+ idrestaurante);
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

		return restaurante;
	}
	
	
	
	private String GET_RESTAURATES_QUERY_BY_PARAMETROS = "select rest.*, u.username, op.* from restaurantes rest, users u, opiniones op where u.username=rest.creador and rest.idrestaurante=op.idrest  and rest.categoria=? and rest.provincia=? order by  rest.idrestaurante desc limit ?";
	private String GET_RESTAURATES_QUERY_FROM_LAST_BY_PARAMETROS = "select rest.*, u.username, op.* from restaurantes rest, users u, opiniones op where u.username=rest.creador and rest.idrestaurante=op.idrest  and rest.categoria=? and rest.provincia=? and rest.idrestaurante > ? order by rest.idrestaurante desc";

	
	/*@GET
	@Path("/search")
	@Produces(MediaType.RESTAURAPP_API_RESTAURANTE_COLLECTION)
	public RestauranteCollection buscarRestaurantes(
			@QueryParam("categoria") String categoria,
			@QueryParam("provincia") String provincia,
			@QueryParam("length") int length,
			@QueryParam("before") int before, 
			@QueryParam("after") int after){
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
			System.out.println("Dentro del try con valor updateFromLast...."+ updateFromLast);

			stmt = updateFromLast ? conn
					.prepareStatement(GET_RESTAURATES_QUERY_FROM_LAST_BY_PARAMETROS) : conn
					.prepareStatement(GET_RESTAURATES_QUERY_BY_PARAMETROS);
			if (updateFromLast) {
				stmt.setInt(1, after);
			}else{
				length = (length <= 0) ? 8 : length;// si lenght menor a 0 coge valor a 5 sino coge valor por defecto de lenght
				stmt.setInt(1, length);
			}
			
			ResultSet rs = stmt.executeQuery();
			boolean first = true;
			long oldestTimestamp = 0;
			while (rs.next()) {
				Libros libro = new Libros();
				libro.setLibroid(rs.getInt("libroid"));
				libro.setAutor(rs.getString("name"));
				libro.setIdautor(rs.getInt("idAuthor"));
				libro.setDateCreation(rs.getTimestamp("DateCreation").getTime());
				oldestTimestamp = rs.getTimestamp("DateImpresion").getTime();
				libro.setDateImpresion(oldestTimestamp);
				libro.setEdition(rs.getString("edition"));
				libro.setEditorial(rs.getString("editorial"));
				libro.setLanguage(rs.getString("language"));
				libro.setTitle(rs.getString("title"));
				if (first) {
					first = false;
					libros.setNewestTimestamp(libro.getDateImpresion());
				}
				libros.addLibros(libro);
			}
			libros.setOldestTimestamp(oldestTimestamp);
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

		return libros;
	}
	*/
	
	private String INSERT_RESTAURANTE_QUERY = "insert into restaurantes (nombre, direccion, telefono, email, horario, categoria, creador, provincia) values (?,?,?,?,?,?,?,?)";

	@POST
	@Consumes(MediaType.RESTAURAPP_API_RESTAURATE)
	@Produces(MediaType.RESTAURAPP_API_RESTAURATE)
	public Restaurante createRestaurante(Restaurante restaurante) { // CREATE

		validateRegistradoOaDmin();
		System.out.println("Creando restaurante....");
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_RESTAURANTE_QUERY,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, restaurante.getNombre());
			stmt.setString(2, restaurante.getDireccion());
			stmt.setString(3, restaurante.getTelefono());
			stmt.setString(4, restaurante.getEmail());
			stmt.setString(5, restaurante.getHorario());
			stmt.setString(6, restaurante.getCategoria());
			stmt.setString(7, security.getUserPrincipal().getName());
			stmt.setString(8, restaurante.getProvincia());



			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				System.out.println("antes de recuperar restaurante");
				int idrestaurante = rs.getInt(1);
				restaurante = getRestauranteFromDatabase(Integer.toString(idrestaurante));
				System.out.println("ola");


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
		return restaurante;
	}
	
	private String GET_RESTAURANTE_BY_ID = "select rest.*, u.username, op.* from restaurantes rest, users u, opiniones op where u.username=rest.creador and rest.idrestaurante=? order by rest.idrestaurante desc";

	private Restaurante getRestauranteFromDatabase(String idrestuarante) { // GET AUTHOR DATABASE

		Restaurante restaurante = new Restaurante();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_RESTAURANTE_BY_ID);
			stmt.setInt(1, Integer.valueOf(idrestuarante));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				if(restaurante.getIdrestaurante()== 0 ){
					
				
					
					restaurante.setNombre(rs.getString("nombre"));
					restaurante.setCategoria(rs.getString("categoria"));
					restaurante.setCreador(rs.getString("creador"));
					restaurante.setCreationTime(rs.getTimestamp("creation_timestamp").getTime());
					restaurante.setDireccion(rs.getString("direccion"));
					restaurante.setEmail(rs.getString("email"));
					restaurante.setHorario(rs.getString("horario"));
					
					System.out.println("antes de idrest");
					restaurante.setIdrestaurante(rs.getInt("idrestaurante"));
					System.out.println("despues de idrest");
	
					restaurante.setProvincia(rs.getString("provincia"));
					restaurante.setTelefono(rs.getString("telefono"));
				}

				Opinion opinion = new Opinion();
				
				opinion.setFecha_estancia(rs.getString("mes_estancia"));
				opinion.setCreation_timestamp(rs.getTimestamp("opinion_creation").getTime());
				opinion.setIdopinion(rs.getInt("idopinion"));
				opinion.setIdrestaurante(rs.getInt("idrest"));
				opinion.setNoutilidad(rs.getInt("cont_noutilidad"));
				opinion.setPuntuacion(rs.getInt("puntuacion"));
				opinion.setTexto(rs.getString("texto"));
				opinion.setTitulo(rs.getString("titulo"));
				opinion.setUsername(rs.getString("username"));
				opinion.setUtilidad(rs.getInt("cont_utilidad"));
				
				
				restaurante.addOpinion(opinion);
				
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
		return restaurante;

	}

	
	
	private void validateCreadorOaDmin(String idrestaurante) {// VALIDATEADMIN&USER
		Restaurante restaurante = getRestauranteFromDatabase(idrestaurante);
		String creadorRestaurante = restaurante.getCreador();
		System.out.println("id -->" + idrestaurante + "comparo " + creadorRestaurante + " y "
				+ security.getUserPrincipal().getName());
		if (!(security.getUserPrincipal().getName().equals(creadorRestaurante) || security
				.isUserInRole("administrador")))
			throw new ForbiddenException("Este Restaurante no es tuyo.");

	}
	private void validateRegistradoOaDmin() {// VALIDATEADMIN&USER
		System.out.println(security.getUserPrincipal().getName());
		if (!(security.isUserInRole("registered") || security
				.isUserInRole("administrador")))
			throw new ForbiddenException("Necesitas registrarte.");

	}
	
	
	
	
	
	
	
	
/*
 * 
 * 
 * 
 * 
 * 
 * OPINIONES
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
	
	private String INSERT_OPINION_QUERY = "insert into opiniones (idrest, titulo, puntuacion, texto, mes_estancia, username) values (?,?,?,?,?,?)";
	
	@POST
	@Path("/opinion/{idrestaurante}")
	@Consumes(MediaType.RESTAURAPP_API_OPINION)
	@Produces(MediaType.RESTAURAPP_API_OPINION)
	public Opinion createOpinion(@PathParam("idrestaurante") String idRestaurante, Opinion opinion) { // CREATE
		
		validateRegistradoOaDmin();
		System.out.println("Creando opinion....");
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_OPINION_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, Integer.parseInt(idRestaurante));
			stmt.setString(2, opinion.getTitulo());
			stmt.setInt(3,opinion.getPuntuacion());
			stmt.setString(4, opinion.getTexto());
			stmt.setString(5, opinion.getFecha_estancia());
			stmt.setString(6, security.getUserPrincipal().getName());


			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idopinion = rs.getInt(1);
				System.out.println("idopinion---"+idopinion);
				opinion = getOpinionFromDatabase(Integer.toString(idopinion));

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
		return opinion;
	}
	
	
	private String GET_OPINIONES_BY_IDRESTAURANTE = "select * from opiniones where idrest=?";
	
	@GET
	@Path("/opinion/{idrestaurante}")
	@Produces(MediaType.RESTAURAPP_API_OPINION)
	public Restaurante getOpiniones(@PathParam("idrestaurante") String idrestaurante) {
		
		Restaurante opiniones = new Restaurante();
		
		System.out.println("Dentro de la funcion GET....");

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		System.out.println("Conectado a la BD....");

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_OPINIONES_BY_IDRESTAURANTE);
			stmt.setInt(1, Integer.parseInt(idrestaurante));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Opinion opinion = new Opinion();
				
				opinion.setFecha_estancia(rs.getString("mes_estancia"));
				opinion.setCreation_timestamp(rs.getTimestamp("opinion_creation").getTime());
				opinion.setIdopinion(rs.getInt("idopinion"));
				opinion.setIdrestaurante(rs.getInt("idrest"));
				opinion.setNoutilidad(rs.getInt("cont_noutilidad"));
				opinion.setPuntuacion(rs.getInt("puntuacion"));
				opinion.setTexto(rs.getString("texto"));
				opinion.setTitulo(rs.getString("titulo"));
				opinion.setUsername(rs.getString("username"));
				opinion.setUtilidad(rs.getInt("cont_utilidad"));
				
				
				opiniones.addOpinion(opinion);
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
		System.out.println("returning");

		return opiniones;
	}
	
	
	
	
	
	
	
	
	
	private String GET_OPINION_BY_ID_QUERY = "select * from opiniones where idopinion=?";

	
	private Opinion getOpinionFromDatabase(String idopinion) { // GET AUTHOR DATABASE

		Opinion opinion = new Opinion();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_OPINION_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(idopinion));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				
				opinion.setIdopinion(rs.getInt("idopinion"));
				opinion.setCreation_timestamp(rs.getTimestamp("opinion_creation").getTime());
				opinion.setFecha_estancia(rs.getString("mes_estancia"));
				opinion.setIdrestaurante(rs.getInt("idrest"));
				opinion.setNoutilidad(rs.getInt("cont_noutilidad"));
				opinion.setUtilidad(rs.getInt("cont_utilidad"));
				opinion.setPuntuacion(rs.getInt("puntuacion"));
				opinion.setTexto(rs.getString("texto"));
				opinion.setTitulo(rs.getString("titulo"));
				opinion.setUsername(rs.getString("username"));
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
		return opinion;

	}
	
	
	
	
}