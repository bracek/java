package com.aver.restful;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clock")
public class Time {
	@XmlElement
	private String time;

	@XmlElement
	private String name;

	public void setTime(final String time) {
		this.time = time;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public String getName() {
		return name;
	}

}
