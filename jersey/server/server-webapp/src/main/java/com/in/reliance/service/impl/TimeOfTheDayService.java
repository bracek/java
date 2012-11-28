package com.in.reliance.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.in.reliance.domain.Time;
import com.in.reliance.service.ITimeOfTheDayService;

@Component
@Path("/timeoftheday")
public class TimeOfTheDayService implements ITimeOfTheDayService {
	private static String PATTERN = "MM.dd.yyyy HH:mm:ss";

	@GET
	@Path("/do")
	@Produces("application/json")
	public Time getTime() {
		final SimpleDateFormat df = new SimpleDateFormat(PATTERN);
		final Time t = new Time();
		t.setName("mirko");
		t.setTime(df.format(Calendar.getInstance().getTime()));
		return t;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.in.reliance.service.impl.ITimeOfTheDayService#getTimeOfTheDay(java
	 * .lang.String)
	 */
	@Override
	@Produces("text/plain")
	@Path("/asplaintext/{name}")
	public String getTimeOfTheDay(@PathParam("name") final String name) {
		final SimpleDateFormat df = new SimpleDateFormat(PATTERN);
		return name + "-" + df.format(Calendar.getInstance().getTime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.in.reliance.service.impl.ITimeOfTheDayService#getTimeOfTheDayInXML
	 * (java.lang.String)
	 */
	@Override
	@GET
	@Produces("application/xml")
	@Path("/asxml/{name}/")
	public Time getTimeOfTheDayInXML(@PathParam("name") final String name) {
		final SimpleDateFormat df = new SimpleDateFormat(PATTERN);
		final Time t = new Time();
		t.setName(name);
		t.setTime(df.format(Calendar.getInstance().getTime()));
		return t;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.in.reliance.service.impl.ITimeOfTheDayService#getTimeOfTheDayInJSON
	 * (java.lang.String)
	 */
	@Override
	@GET
	@Produces("application/json")
	@Path("/asjson/{name}/")
	public Time getTimeOfTheDayInJSON(@PathParam("name") final String name) {
		final SimpleDateFormat df = new SimpleDateFormat(PATTERN);
		final Time t = new Time();
		t.setName(name);
		t.setTime(df.format(Calendar.getInstance().getTime()));
		return t;
	}
}