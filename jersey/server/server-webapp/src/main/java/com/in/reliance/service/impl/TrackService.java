package com.in.reliance.service.impl;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.in.reliance.domain.Track;
import com.in.reliance.service.ITrackService;

@Component
@Path("/track")
public class TrackService implements ITrackService {

	/* (non-Javadoc)
	 * @see com.in.reliance.service.impl.ITrackService#getTrackInJSON()
	 */
	@Override
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON() {

		final Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;

	}

	/* (non-Javadoc)
	 * @see com.in.reliance.service.impl.ITrackService#createTrackInJSON(com.in.reliance.domain.Track)
	 */
	@Override
	@POST
	@Path("/post")
	@Produces("application/json")
	public Response createTrackInJSON(final Track track) {

		final String result = "Track saved : " + track;
		return Response.status(201).entity(result).build();

	}

}