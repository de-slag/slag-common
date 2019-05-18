package de.slag.common.db.hibernate.test;

import java.util.function.Consumer;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import de.slag.common.db.h2.InMemoryProperties;
import de.slag.common.db.hibernate.HibernateUtils;

public class HibernateUtilsTest {
	
	@Test
	public void test() {
		final Consumer<Session> sessionConsumer = session -> {
			Assert.assertNotNull(session);
		};
		HibernateUtils.access(sessionConsumer, new InMemoryProperties());
	}

}
