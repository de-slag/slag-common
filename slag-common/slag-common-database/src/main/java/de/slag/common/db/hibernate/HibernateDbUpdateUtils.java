package de.slag.common.db.hibernate;

import java.util.Collection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.tool.schema.spi.SchemaManagementException;

import de.slag.common.context.SubClassesUtils;
import de.slag.common.db.EntityBean;

public class HibernateDbUpdateUtils {

	private static final Log LOG = LogFactory.getLog(HibernateDbUpdateUtils.class);

	public static void update(Properties prop) {
		hbm2ddlAuto("update", prop);
	}

	public static boolean isValid(Properties properties) {
		try {
			validate(properties);
		} catch (SchemaManagementException e) {
			LOG.debug(e);
			return false;
		}
		return true;
	}

	public static void validate(Properties prop) {
		hbm2ddlAuto("validate", prop);
	}

	private static void hbm2ddlAuto(final String autoAction, Properties prop) {
		final Properties properties = new Properties();
		properties.putAll(prop);
		properties.put(AvailableSettings.HBM2DDL_AUTO, autoAction);
		final Collection<Class<?>> entityBeanClasses = SubClassesUtils.findAllSubclassesOf(EntityBean.class);
		LOG.info(autoAction + "...");
		HibernateUtils.access(HibernateUtils.EMPTY_SESSION_CONSUMER, properties, entityBeanClasses);
		LOG.info(autoAction + "...done.");
	}

}
