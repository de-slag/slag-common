package de.slag.common.model;

import java.util.HashMap;
import java.util.Map;

public final class HackyIdAllocator {

	private static long id = 0;

	private static Map<Class<?>, Long> idMap = new HashMap<>();

	static Long next() {
		return id++;
	}

	public static void allocateId(EntityBean eb) {
		if (!idMap.containsKey(eb.getClass())) {
			idMap.put(eb.getClass(), 0L);
		}
		final Long long1 = idMap.get(eb.getClass());
		final Long id = long1 + 1;
		idMap.put(eb.getClass(), id);

		eb.setId(id);
	}

}
