package de.slag.common.base.event;

import org.apache.commons.lang3.StringUtils;

public abstract class InfoEvent implements Event {

	private String info;

	public InfoEvent() {
		this(StringUtils.EMPTY);
	}

	public InfoEvent(String info) {
		this.info = info;
	}

	@Override
	public String getInfo() {
		return Event.super.getInfo() + ": "+ info;
	}

}
