package de.slag.common.db.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.function.Supplier;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.tool.schema.spi.SchemaManagementException;

//import de.slag.common.context.SubClassesUtils;
import de.slag.common.db.h2.InMemoryProperties;
import de.slag.common.model.EntityBean;

public interface HibernateResource {

	@Deprecated
	SessionFactory getSessionFactory();

	Properties getDbProperties();

	default Supplier<Session> getSessionSupplier() {
		return () -> getSessionFactory().openSession();
	}

	default void validate() {
		execute("validate");
	}

	default void update() {
		execute("update");
	}

	default void execute(final String auto) {
		final Properties properties = new Properties();
		properties.putAll(getDbProperties());
		properties.put(AvailableSettings.HBM2DDL_AUTO, auto);
		final Collection<Class<?>> entityBeanClasses = new ArrayList<>();// SubClassesUtils.findAllSubclassesOf(EntityBean.class);
		HibernateUtils.access(HibernateUtils.EMPTY_SESSION_CONSUMER, properties, entityBeanClasses);
	}

	default boolean isValid() {
		try {
			validate();
		} catch (SchemaManagementException e) {
			return false;
		}
		return true;
	}

}
