package de.slag.common.core.event;

import java.util.ArrayList;
import java.util.Collection;

public final class EventBus {

	private static final Collection<EventAction> ACTIONS = new ArrayList<>();

	public static void addAction(EventAction action) {
		ACTIONS.add(action);
	}

	public static void occure(Event event) {
		synchronized (EventBus.class) {
			final Collection<EventAction> c = new ArrayList<>(ACTIONS);
			c.forEach(action -> action.run(event));
		}
	}
}
