package de.slag.common.base.event;

public interface Event {
	
	default String getInfo() {
		return getClass().getSimpleName();
	}
	
}
