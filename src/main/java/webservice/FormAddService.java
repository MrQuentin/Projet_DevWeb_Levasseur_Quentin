package webservice;

import com.google.gson.Gson;
import entities.*;
import manager.CategorieLibrary;
import manager.VersionLibrary;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/form/add")
public class FormAddService {

    @POST
    @Path("/categorie")
    public Response addCategorie(@FormParam("categorie")String cat){
        System.out.println("WS add Categorie : "+cat);
        Gson jsonParser = new Gson();
        Categorie categorieToAdd = CategorieLibrary.getInstance().addCategorie(jsonParser.fromJson(cat, Categorie.class));
        return Response.ok().entity(categorieToAdd).build();
    }

    @POST
    @Path("/type")
    public Response addType(@FormParam("type")String type, @FormParam("idcategorie")Integer idcat){
        System.out.println("WS add Type : "+type+" id cat : "+idcat);
        Gson jsonParser = new Gson();
        Type typeToAdd = CategorieLibrary.getInstance().addType(jsonParser.fromJson(type, Type.class), idcat);
        return Response.ok().entity(jsonParser.toJson(typeToAdd)).build();
    }

    @POST
    @Path("/item")
    public Response addItem(@FormParam("item") String item, @FormParam("idtype") Integer idType){
        System.out.println("WS add Item : "+item+" id type : "+idType);
        Gson jsonParser = new Gson();
        Item itemToAdd = CategorieLibrary.getInstance().addItem(jsonParser.fromJson(item, Item.class), idType);
        return Response.ok().entity(jsonParser.toJson(itemToAdd)).build();
    }

    @POST
    @Path("/version")
    public Response addVersion (@FormParam("version") String vers){
        System.out.println("WS addVersion :" + vers);
        Gson jsonParser = new Gson();
        Version versionToAdd = VersionLibrary.getInstance().addVersion(jsonParser.fromJson(vers, Version.class));
        return Response.ok().entity(jsonParser.toJson(versionToAdd)).build();
    }

    @POST
    @Path("/sousversion")
    public Response addSousVersion (@FormParam("sousversion")String sv, @FormParam("idversion") Integer id){
        System.out.println("Ws add SV : "+sv+" id vers : "+id);
        Gson jsonParser = new Gson();
        SousVersion sousversionToAdd = VersionLibrary.getInstance().addSousVersion(jsonParser.fromJson(sv, SousVersion.class), id);
        return Response.ok().entity(jsonParser.toJson(sousversionToAdd)).build();
    }

}
