package com.mkyong.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mkyong.Track;

@Path("/json/metallica")
public class JSONService {

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public final Response createTrackInJSON(final Track track) {

		final String result = "Track saved : " + track;
		return Response.status(201).entity(result).build();

	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public final Track getTrackInJSON() {

		final Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;

	}

}