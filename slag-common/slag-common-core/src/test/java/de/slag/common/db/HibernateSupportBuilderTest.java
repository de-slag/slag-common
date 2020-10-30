package de.slag.common.db;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import de.slag.common.db.h2.InMemoryProperties;

class HibernateSupportBuilderTest {

	@Test
	void integrationTest() {
		HibernateSupport hibernateSupport = new HibernateSupportBuilder()
				.registerClasses(Collections.singleton(TestEntity.class))
				.hibernateDialect(InMemoryProperties.DIALECT)
				.url(InMemoryProperties.URL)
				.user(InMemoryProperties.USER)
				.password(InMemoryProperties.PASSWORD)
				.driver(InMemoryProperties.DRIVER)
				.build();
		assertNotNull(hibernateSupport);
	}
	
	private class TestEntity {
		
	}

}
