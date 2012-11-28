package com.in.reliance.service;

import javax.ws.rs.PathParam;

import com.in.reliance.domain.Time;

public interface IMovieService {

	public abstract Time getTimeOfTheDayInXML(@PathParam("name") String name);

}