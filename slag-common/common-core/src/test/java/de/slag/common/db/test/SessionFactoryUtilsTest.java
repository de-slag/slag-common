package de.slag.common.db.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import de.slag.common.db.h2.InMemoryProperties;
import de.slag.common.db.hibernate.SessionFactoryUtils;

public class SessionFactoryUtilsTest {

	@Test
	public void test() {
		assertNotNull(SessionFactoryUtils.createSessionFactory(new InMemoryProperties()));
		assertNotNull(SessionFactoryUtils.createSessionFactory(new InMemoryProperties(), Collections.emptyList()));
		final Collection<Class<?>> registerClasses = new ArrayList<>();

		registerClasses.add(TestEntity.class);

		assertNotNull(SessionFactoryUtils.createSessionFactory(new InMemoryProperties(), registerClasses));
	}

}
