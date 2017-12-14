package webservice;

import com.google.gson.Gson;
import entities.Categorie;
import entities.Version;
import manager.CategorieLibrary;
import manager.VersionLibrary;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/form/delete")
public class FormDeleteService {

    @DELETE
    @Path("/version/{id}")
    public Response deleteVersion(@PathParam("id")int idversion){
        System.out.println("WS delete Version : "+idversion);
        VersionLibrary.getInstance().removeVersion(idversion);
        return Response.ok().build();
    }

    @DELETE
    @Path("/sousversion/{id}")
    public Response deleteSousVersion(@PathParam("id")int idSV){
        System.out.println("WS delete SV : "+idSV);
        VersionLibrary.getInstance().removeSousVersion(idSV);
        return Response.ok().build();
    }

    @DELETE
    @Path("/categorie/{id}")
    public Response deleteCategorie(@PathParam("id")int idCat){
        System.out.println("WS delete Categorie : "+idCat);
        CategorieLibrary.getInstance().removeCategorie(idCat);
        return Response.ok().build();
    }


    @DELETE
    @Path("/type/{id}")
    public Response deleteType(@PathParam("id")int idType){
        System.out.println("WS delete Type : "+idType);
        CategorieLibrary.getInstance().removeType(idType);
        return Response.ok().build();
    }

    @DELETE
    @Path("/item/{id}")
    public Response deleteItem(@PathParam("id")int idItem){
        System.out.println("WS delete Item : "+idItem);
        CategorieLibrary.getInstance().removeItem(idItem);
        return Response.ok().build();
    }

}
