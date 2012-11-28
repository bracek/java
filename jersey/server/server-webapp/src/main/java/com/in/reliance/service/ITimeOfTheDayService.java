package com.in.reliance.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.in.reliance.domain.Time;

public interface ITimeOfTheDayService {

	@GET
	@Produces("text/plain")
	@Path("/asplaintext/{name}")
	public abstract String getTimeOfTheDay(@PathParam("name") String name);

	@GET
	@Produces("application/xml")
	@Path("/asxml/{name}/")
	public abstract Time getTimeOfTheDayInXML(@PathParam("name") String name);

	@GET
	@Produces("application/json")
	@Path("/asjson/{name}/")
	public abstract Time getTimeOfTheDayInJSON(@PathParam("name") String name);

}