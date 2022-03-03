package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import errorhandling.PersonNotFoundException;
import facades.FacadeExample;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("person")
public class PersonResource
{
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id")int id) throws PersonNotFoundException{
        PersonDTO personDTO = FACADE.getPerson(id);
        return Response.ok().entity(GSON.toJson(personDTO)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPeople(){
        return Response.ok().entity(GSON.toJson(FACADE.getAllPersons())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String info){
        PersonDTO personDTO = GSON.fromJson(info, PersonDTO.class);
        PersonDTO latestPDTO = FACADE.addPerson(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getPhone());
        return Response.ok().entity(GSON.toJson(latestPDTO)).build();

    }

    @PUT
    @Path("/edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPerson(@PathParam("id")int id, String info) throws PersonNotFoundException
    {
        PersonDTO personDTO = GSON.fromJson(info, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO updatedPDTO = FACADE.editPerson(personDTO);

        return Response.ok().entity(GSON.toJson(updatedPDTO)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id")int id) throws PersonNotFoundException{
        PersonDTO deletedPDTO = FACADE.deletePerson(id);
        return Response.ok().entity(GSON.toJson(deletedPDTO)).build();
    }

}