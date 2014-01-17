package com.mkyong;

public final class Track {

	private String title;
	private String singer;

	public final String getSinger() {
		return singer;
	}

	public String getTitle() {
		return title;
	}

	public void setSinger(final String singer) {
		this.singer = singer;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Track [title=" + title + ", singer=" + singer + "]";
	}

}
