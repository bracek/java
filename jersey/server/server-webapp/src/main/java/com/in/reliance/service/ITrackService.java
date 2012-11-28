package com.in.reliance.service;

import javax.ws.rs.core.Response;

import com.in.reliance.domain.Track;

public interface ITrackService {

	public Track getTrackInJSON();

	public abstract Response createTrackInJSON(Track track);

}