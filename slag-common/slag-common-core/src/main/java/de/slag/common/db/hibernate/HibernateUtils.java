package de.slag.common.db.hibernate;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.function.Consumer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateUtils {
	
	public static final Consumer<Session> EMPTY_SESSION_CONSUMER = s -> {};
	
	public static void access(Consumer<Session> sessionConsumer, Properties properties) {
		access(sessionConsumer, properties, Collections.emptyList());
	}
	
	public static void access(Consumer<Session> sessionConsumer, Properties properties, Collection<Class<?>> registerClasses) {
		final SessionFactory sf = SessionFactoryUtils.createSessionFactory(properties,registerClasses);
		try (final Session session = sf.openSession()) {
			final Transaction tx = session.beginTransaction();
			sessionConsumer.accept(session);
			tx.commit();
		}
		sf.close();
	}
}
