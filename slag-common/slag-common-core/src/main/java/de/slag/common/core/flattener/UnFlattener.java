package de.slag.common.core.flattener;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class UnFlattener implements BiConsumer<Map<String, String>, Object> {

	@Override
	public void accept(Map<String, String> attributeValueMap, Object object) {
		Collection<String> attributes = FlattenerFunctions.DETERMINE_ATTRIBUTES.apply(object);
		List<String> attributesInMap = attributes.stream().filter(a -> attributeValueMap.keySet().contains(a))
				.collect(Collectors.toList());
		attributesInMap.forEach(a -> FlattenerFunctions.SET_VALUE.accept(object, new AttributeValue() {

			@Override
			public String getValue() {
				return attributeValueMap.get(a);
			}

			@Override
			public String getAttribute() {
				return a;
			}
		}));

	}

}
