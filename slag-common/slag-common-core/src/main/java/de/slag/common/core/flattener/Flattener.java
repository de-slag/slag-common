package de.slag.common.core.flattener;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;

import de.slag.common.base.BaseException;

public class Flattener implements BiConsumer<Object, Map<String, String>> {

	@Override
	public void accept(Object object, Map<String, String> attributeValueMap) {
		Collection<String> attributesNames = FlattenerFunctions.DETERMINE_ATTRIBUTES.apply(object);
		try {

			attributesNames.forEach(a -> attributeValueMap.put(a, FlattenerFunctions.GET_VALUE.apply(object, a)));
		} catch (Exception e) {
			throw new BaseException(String.format("error flatten '%s'", object.getClass().getName()));
		}
	}

}
