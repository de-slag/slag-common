package de.slag.common.db.hibernate;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import de.slag.common.base.SlagDevelopment;

public class SessionFactoryUtils {

	public static SessionFactory createSessionFactory(Properties properties, Collection<Class<?>> registerClasses) {
		final Configuration configuration = new Configuration();
		configuration.addProperties(properties);
		if (SlagDevelopment.isEnabled() && SlagDevelopment.isEnabled(AvailableSettings.SHOW_SQL)) {
			configuration.setProperty(AvailableSettings.SHOW_SQL, "true");
		}
		configuration.setPhysicalNamingStrategy(new DefaultNamingStrategy());

		registerClasses.forEach(cls -> configuration.addAnnotatedClass(cls));

		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
		ssrb.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = ssrb.build();
		SessionFactory sf = configuration.buildSessionFactory(serviceRegistry);

		return sf;
	}

	public static SessionFactory createSessionFactory(Properties properties) {
		return createSessionFactory(properties, Collections.emptyList());
	}

}
