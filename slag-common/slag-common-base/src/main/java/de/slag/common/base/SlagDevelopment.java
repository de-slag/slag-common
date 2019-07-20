package de.slag.common.base;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SlagDevelopment {

	private static final Log LOG = LogFactory.getLog(SlagDevelopment.class);

	public static final String SLAG_DEVELOPMENT_ENABLED = "SlagDevelopmentEnabled";
	
	public static final String SLAG_DEVELOPMENT_SHOW_SQL = "SlagDevelopmentShowSql";

	private static SlagDevelopment instance;

	public static SlagDevelopment instance() {
		if (instance == null) {
			instance = new SlagDevelopment();
		}
		return instance;
	}

	public static boolean isEnabled() {
		final boolean devEnabled = isEnabled(SLAG_DEVELOPMENT_ENABLED);
		if (devEnabled) {
			LOG.warn(SLAG_DEVELOPMENT_ENABLED);
		}
		return devEnabled;
	}
	
	public static boolean isEnabled(String propertyKey) {
		return BooleanUtils.isTrue(Boolean.valueOf(System.getProperty(propertyKey)));
	}

	public boolean getEnabled() {
		return isEnabled();
	}

}
