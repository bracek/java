package com.in.reliance.service;

import javax.ws.rs.PathParam;

import com.in.reliance.domain.Time;

public interface IMovieService {

	 abstract Time getTimeOfTheDayInXML(final @PathParam("name") String name);

}
