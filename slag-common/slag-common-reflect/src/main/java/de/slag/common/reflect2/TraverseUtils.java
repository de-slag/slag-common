package de.slag.common.reflect2;

import java.util.Set;
import java.util.function.Consumer;

public class TraverseUtils {

	public void traverse(Object objectToTraverse, Class<?> cls, Set<?> visited, Consumer<Object> consumer) {
		if (!cls.isAssignableFrom(objectToTraverse.getClass())) {
			return;
		}
		if (visited.contains(objectToTraverse)) {
			return;
		}
		consumer.accept(objectToTraverse);
		ReflectionUtils.determineGetters(objectToTraverse.getClass());

	}
}
