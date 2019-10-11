package de.slag.common.core.event;

public interface Event {
	
	default String getInfo() {
		return getClass().getSimpleName();
	}
	
}
