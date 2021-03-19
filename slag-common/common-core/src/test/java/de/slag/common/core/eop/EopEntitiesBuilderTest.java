package de.slag.common.core.eop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.slag.common.base.BaseException;

class EopEntitiesBuilderTest {

	EopEntitiesBuilder eopEntitiesBuilder;

	Properties properties;

	@BeforeEach
	void setUp() throws IOException {
		eopEntitiesBuilder = new EopEntitiesBuilder();
		properties = new Properties();
		properties.load(this.getClass().getClassLoader().getResourceAsStream("core/eop/entities.properties"));
	}
	
	@Test
	void entitiesOfTest() {
		assertEquals(2, EopEntitiesBuilder.TYPES_OF.apply(properties).size());
	}

	@Test
	void it() {
		assertEquals(4, eopEntitiesBuilder.withProperties(properties).build().size());
	}

	@Test
	void failsTest() {
		assertThrows(BaseException.class, () -> eopEntitiesBuilder.build());
	}

}
