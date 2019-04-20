package de.slag.common.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

public class LoggingUtils {

	private static final Log LOG = LogFactory.getLog(LoggingUtils.class);
	private static final String DE_SLAG = "de.slag";

	public static void activateLogging() {
		activateLogging(Level.INFO, DE_SLAG);
	}

	public static void activateLogging(final Level level) {
		activateLogging(level, DE_SLAG);
	}

	public static void activateLogging(final Level level, final String loggerName) {
		Configurator.setLevel(loggerName, level);
		LOG.fatal("logging activated: "  + level);

	}
}
