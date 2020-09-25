package de.slag.common.core.flattener;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class UnFlattenerTest {

	@Test
	public void test() {
		Map<String, String> attributeValueMap = new HashMap<>();
		attributeValueMap.put("NAME", "Mr. X");
		attributeValueMap.put("NUMBER", "17");
		attributeValueMap.put("VALID", "TRUE");

		FlattenerTestEntity entity = new FlattenerTestEntity();
		new UnFlattener().accept(attributeValueMap, entity);
		assertEquals("Mr. X", entity.getName());
		assertEquals((Integer)17, entity.getNumber());
		assertEquals(true, entity.isValid());

	}

}
