package com.in.reliance.service;

import javax.ws.rs.PathParam;

import com.in.reliance.domain.Time;

public interface ITimeOfTheDayService {

	public abstract String getTimeOfTheDay(@PathParam("name") String name);

	public abstract Time getTimeOfTheDayInXML(@PathParam("name") String name);

	public abstract Time getTimeOfTheDayInJSON(@PathParam("name") String name);

}