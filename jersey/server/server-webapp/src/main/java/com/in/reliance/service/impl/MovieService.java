package com.in.reliance.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.in.reliance.domain.Time;
import com.in.reliance.service.IMovieService;

@Component
@Path("/movie")
public class MovieService implements IMovieService {

	private static String PATTERN = "MM.dd.yyyy HH:mm:ss";

	@Override
	@GET
	@Produces("application/xml")
	@Path("/{name}/")
	public Time getTimeOfTheDayInXML(@PathParam("name") final String name) {
		final SimpleDateFormat df = new SimpleDateFormat(PATTERN);
		final Time t = new Time();
		t.setName(name);
		t.setTime(df.format(Calendar.getInstance().getTime()));
		return t;

	}
}