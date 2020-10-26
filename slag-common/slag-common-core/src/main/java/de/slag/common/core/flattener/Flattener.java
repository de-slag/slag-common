package de.slag.common.core.flattener;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;

public class Flattener implements BiConsumer<Object, Map<String, String>> {

	@Override
	public void accept(Object object, Map<String, String> attributeValueMap) {
		Collection<String> attributesNames = FlattenerFunctions.DETERMINE_ATTRIBUTES.apply(object);
		attributesNames.forEach(a -> attributeValueMap.put(a, FlattenerFunctions.GET_VALUE.apply(object, a)));
	}

}
