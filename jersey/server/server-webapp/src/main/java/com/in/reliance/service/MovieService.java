package com.in.reliance.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.in.reliance.domain.Time;

@Component
@Path("/movie")
public class MovieService {

	private static String PATTERN = "MM.dd.yyyy HH:mm:ss";
	
	 @GET
	    @Produces("application/xml")
	    @Path("/{name}/")
	    public Time getTimeOfTheDayInXML(@PathParam("name") String name) {
	        SimpleDateFormat df = new SimpleDateFormat(PATTERN);
	        Time t = new Time();
	        t.setName(name);
	        t.setTime(df.format(Calendar.getInstance().getTime()));
	        return t;
	        
	        
	    }
}