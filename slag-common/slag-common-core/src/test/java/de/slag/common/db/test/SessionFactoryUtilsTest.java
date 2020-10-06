package de.slag.common.db.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import de.slag.common.db.h2.InMemoryProperties;
import de.slag.common.db.hibernate.SessionFactoryUtils;

public class SessionFactoryUtilsTest {

	@Test
	public void test() {
		Assert.assertNotNull(SessionFactoryUtils.createSessionFactory(new InMemoryProperties()));
		Assert.assertNotNull(SessionFactoryUtils.createSessionFactory(new InMemoryProperties(), Collections.emptyList()));
		final Collection<Class<?>> registerClasses = new ArrayList<>();
		
		registerClasses.add(TestEntity.class);
		
		Assert.assertNotNull(SessionFactoryUtils.createSessionFactory(new InMemoryProperties(), registerClasses));
	}

}
