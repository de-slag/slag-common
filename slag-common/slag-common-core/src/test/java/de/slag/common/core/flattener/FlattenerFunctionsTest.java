package de.slag.common.core.flattener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FlattenerFunctionsTest {

	@Test
	public void verySmallIntegrationTest() {
		FlattenerTestEntity testEntity = new FlattenerTestEntity();
		testEntity.setName("John Smith");
		Collection<String> apply = FlattenerFunctions.DETERMINE_ATTRIBUTES.apply(testEntity);
		Map<String, String> valueBag = new HashMap<>();
		apply.forEach(attribute -> {
			String value = FlattenerFunctions.GET_VALUE.apply(testEntity, attribute);
			valueBag.put(attribute, value);
		});

	}

	@Test
	public void getValueTest() {
		String name = "Jane Doe";
		FlattenerTestEntity testEntity = new FlattenerTestEntity();
		testEntity.setName(name);
		String value = FlattenerFunctions.GET_VALUE.apply(testEntity, "NAME");
		assertEquals(name, value);
	}

	@Test
	public void setValueTest() {
		FlattenerTestEntity entity = new FlattenerTestEntity();
		String attribute = "NAME";
		String value = "John Doe";
		AttributeValue attributeValue = new AttributeValue() {

			@Override
			public String getValue() {
				return value;
			}

			@Override
			public String getAttribute() {
				return attribute;
			}
		};
		FlattenerFunctions.SET_VALUE.accept(entity, attributeValue);
		assertEquals(value, entity.getName());
	}

	@Test
	public void setValue0Test() {
		FlattenerTestEntity entity = new FlattenerTestEntity();
		String attribute = "NAME";
		String value = "John Doe";
		FlattenerFunctions.SET_VALUE_0.accept(entity, attribute, value);
		assertEquals(value, entity.getName());
	}

	@Test
	public void determineAttributesTest() {
		Collection<String> apply = FlattenerFunctions.DETERMINE_ATTRIBUTES.apply(new FlattenerTestEntity());

		Collection<String> expected = Arrays.asList("NUMBER", "ID", "NAME", "VALID");
		assertTrue(expected.containsAll(apply));
		assertTrue(apply.containsAll(expected));
	}

	@Test
	public void setterTest() {
		Collection<Method> determineSetter = FlattenerFunctions.determineSetter(FlattenerTestEntity.class);
		assertTrue(!determineSetter.isEmpty());
	}

}
