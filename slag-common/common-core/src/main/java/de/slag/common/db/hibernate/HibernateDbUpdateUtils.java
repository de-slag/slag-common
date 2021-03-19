package de.slag.common.db.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.tool.schema.spi.SchemaManagementException;

//import de.slag.common.context.SubClassesUtils;
import de.slag.common.model.EntityBean;

public class HibernateDbUpdateUtils {

	private static final Log LOG = LogFactory.getLog(HibernateDbUpdateUtils.class);

	@Deprecated
	public static void update(Properties prop) {
		throw new IllegalArgumentException("use update(Properties,Collection<Class<?>>) instead");
	}

	public static void update(Properties prop, Collection<Class<?>> beanClasses) {
		hbm2ddlAuto("update", prop, beanClasses);
	}

	public static boolean isValid(Properties properties) {
		throw new IllegalArgumentException("use isValid(Properties,Collection<Class<?>>) instead");
	}

	public static boolean isValid(Properties properties, Collection<Class<?>> beanClasses) {
		try {
			validate(properties, beanClasses);
		} catch (SchemaManagementException e) {
			LOG.debug(e);
			return false;
		}
		return true;
	}

	@Deprecated
	public static void validate(Properties prop) {
		throw new IllegalArgumentException("use validate(Properties,Collection<Class<?>>) instead");

	}

	public static void validate(Properties prop, Collection<Class<?>> beanClasses) {
		hbm2ddlAuto("validate", prop, beanClasses);
	}

	private static void hbm2ddlAuto(final String autoAction, Properties prop, Collection<Class<?>> beanClasses) {
		final Properties properties = new Properties();
		properties.putAll(prop);
		properties.put(AvailableSettings.HBM2DDL_AUTO, autoAction);
		final Collection<Class<?>> entityBeanClasses = new ArrayList<>();// SubClassesUtils.findAllSubclassesOf(EntityBean.class);
		LOG.info(autoAction + "...");
		HibernateUtils.access(HibernateUtils.EMPTY_SESSION_CONSUMER, properties, beanClasses);
		LOG.info(autoAction + "...done.");
	}

}
