package de.slag.common.reflect.engine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class SimpleReflectionEngineTest {

	@Test
	void testMapValuesObjectObject() {
		SimpleReflectionEngineTestEntity source = new SimpleReflectionEngineTestEntity();
		source.setBvar(true);
		source.setLvar(3L);
		source.setSvar("abc");
		
		SimpleReflectionEngineTestEntity target = new SimpleReflectionEngineTestEntity();
		new SimpleReflectionEngine().mapValues(source, target);
		
		assertEquals(true, target.isBvar());
		assertEquals(3L, target.getLvar());
		assertEquals("abc", target.getSvar());
	}

	@Test
	void testMapValuesObjectObjectCollectionOfString() {
		SimpleReflectionEngineTestEntity source = new SimpleReflectionEngineTestEntity();
		source.setBvar(true);
		source.setLvar(3L);
		source.setSvar("abc");
		
		SimpleReflectionEngineTestEntity target = new SimpleReflectionEngineTestEntity();
		Collection<String> ignoredAttributes = new ArrayList<>();
		ignoredAttributes.add("lvar");
		new SimpleReflectionEngine().mapValues(source, target,ignoredAttributes);
		
		assertEquals(true, target.isBvar());
		assertEquals(null, target.getLvar());
		assertEquals("abc", target.getSvar());
	}

}
