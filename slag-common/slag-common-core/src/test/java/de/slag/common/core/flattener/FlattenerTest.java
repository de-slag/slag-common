package de.slag.common.core.flattener;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FlattenerTest {

	@Test
	public void test() {
		FlattenerTestEntity entity = FlattenerTestEntity.create(74L);
		entity.setName("Adalbert Smith");
		entity.setNumber(47);
		entity.setValid(true);
		Map<String, String> attributeValueMap = new HashMap<>();
		new Flattener().accept(entity, attributeValueMap);
		assertTrue(!attributeValueMap.isEmpty());
		String id = attributeValueMap.get("ID");
		assertEquals("74", id);
	}

}
