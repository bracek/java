package com.in.reliance.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.in.reliance.domain.Track;

public interface ITrackService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Track getTrackInJSON();

	@POST
	@Path("/post")
	@Produces("application/json")
	public abstract Response createTrackInJSON(Track track);

}