package com.in.reliance.service;

import javax.ws.rs.core.Response;

import com.in.reliance.domain.Track;

public interface ITrackService {

	 Track getTrackInJSON();

	 abstract Response createTrackInJSON(final Track track);

}
