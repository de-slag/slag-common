package de.slag.commons.dbtools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigPropertiesBuilder {

	private static final Log LOG = LogFactory.getLog(ConfigPropertiesBuilder.class);

	private static final String PROPERTIES_FILE_NAME = "slideshow.properties";

	private String OS = System.getProperty("os.name").toLowerCase();

	public Properties build() {
		String defaultConfigFile = null;
		if (isWindows()) {
			defaultConfigFile = "c:/tmp/" + PROPERTIES_FILE_NAME;
		}
		if (isUnix()) {
			defaultConfigFile = "/tmp/" + PROPERTIES_FILE_NAME;
		}
		final Properties properties = new Properties();
		File file = new File(defaultConfigFile);
		if (file.exists()) {
			try {
				properties.load(new FileInputStream(file));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			LOG.warn("File not found: " + defaultConfigFile);
		}
		return properties;
	}

	public boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	public boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	}

	public boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

}
