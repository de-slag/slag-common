package de.slag.common.db.hibernate.test;

import java.util.function.Consumer;

import org.hibernate.Session;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import de.slag.common.db.h2.InMemoryProperties;
import de.slag.common.db.hibernate.HibernateUtils;

public class HibernateUtilsTest {
	
	@Test
	public void test() {
		final Consumer<Session> sessionConsumer = session -> {
			assertNotNull(session);
		};
		HibernateUtils.access(sessionConsumer, new InMemoryProperties());
	}

}
