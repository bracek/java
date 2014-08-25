package com.javacodegeeks.enterprise.rest.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jsonServices")
public class JerseyRestService {

    @GET
    @Path("/print/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student produceJSON(@PathParam("name") String name) {

        Student st = new Student(name, "Diaz", 22, 1);

        return st;

    }

//    @GET
//    @Path("/list")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public SearchListResponse getYoutubeSearchList() {
//        final SearchListResponse resultItems = new Search().searchListResponse("lenka");
//        return resultItems;
//    }
//
//    @GET
//    @Path("/list1")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public java.util.List<com.google.api.services.youtube.model.SearchResult> getYoutubeSearchList1() {
//        final SearchListResponse resultItems = new Search().searchListResponse("lenka");
//        return resultItems.getItems();
//    }


    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeJSON(Student student) {

        String output = student.toString();

        return Response.status(200).entity(output).build();
    }


}
