package com.in.reliance.service;

import javax.ws.rs.PathParam;

import com.in.reliance.domain.Time;

public interface ITimeOfTheDayService {

	 abstract String getTimeOfTheDay(final @PathParam("name") String name);

	 abstract Time getTimeOfTheDayInXML(final @PathParam("name") String name);

	 abstract Time getTimeOfTheDayInJSON(final @PathParam("name") String name);

}
