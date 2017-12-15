package webservice;

import com.google.gson.Gson;
import entities.SousVersion;
import manager.VersionLibrary;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/edit")
public class EditionService {

    @POST
    @Path("/sousversion")
    public Response addSousVersion (@FormParam("sousversion")String sv, @FormParam("idSVersion")int id){
        System.out.println("Ws update SV : "+sv+"on id= "+id);
        Gson jsonParser = new Gson();
        SousVersion sousversionToAdd = jsonParser.fromJson(sv, SousVersion.class);
        VersionLibrary.getInstance().updateSousVersion(id, sousversionToAdd.getTitle(), sousversionToAdd.getDescription());
        return Response.ok().entity(jsonParser.toJson(sousversionToAdd)).build();
    }

}
