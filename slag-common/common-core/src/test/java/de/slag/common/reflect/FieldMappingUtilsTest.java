package de.slag.common.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;

class FieldMappingUtilsTest {

	@Test
	void integrationTest() {
		ReflectionUtils2TestEntity source = new ReflectionUtils2TestEntity();
		source.setId(74L);
		source.setNumber(11);
		source.setValid(true);
		
		ReflectionUtils2TestEntity target = new ReflectionUtils2TestEntity();
		FieldMappingUtils.map(source, target, Collections.emptyList(), true);
		
		assertEquals(74L, target.getId());
		assertEquals(11, target.getNumber());
		assertEquals(true, target.isValid());
	}

}
