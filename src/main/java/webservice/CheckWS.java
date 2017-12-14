package webservice;



import dao.impl.DataSourceProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

@Path("/check")
public class CheckWS {

	@GET
	@Path("/appserver")
	public Response checkAppserver(){
		return Response.status(200).entity("App server is up").build();
	}
	
	@GET
	@Path("/database")
	public Response checkDatabase(){
		List<String> tables = Arrays.asList("admin","categorie","item","sousversion","type","version");
		try{
			Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("show tables");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String table = rs.getString("Tables_in_projetdevweb");
				if(!tables.contains(table)){
					System.err.println("La table "+table+" n'est pas valide");	
					return Response.status(200).entity("Database Error !!!").build();
				}
				
			}
			connection.close();
			
			return Response.status(200).entity("Database is up").build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Database Error !!!").build();
		}
	}
	
}
