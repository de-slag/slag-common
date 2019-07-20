package de.slag.common.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

public final class SlagProperties {

	private static final String SLAG_CFG = "SlagCfg";

	public static Properties getConfigProperties() {
		final String slagCfg = System.getProperty(SLAG_CFG);
		final String cfgFileName;

		if (StringUtils.isBlank(slagCfg)) {
			final File userHome = SystemUtils.getUserHome();
			String absolutePath = userHome.getAbsolutePath();
			String file = "slag.cfg";
			cfgFileName = absolutePath + "/" + file;
		} else {
			cfgFileName = slagCfg;
		}

		if (!new File(cfgFileName).exists()) {
			throw new BaseException(String.format("cfg-file not found: '%s'", cfgFileName));
		}

		final Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(cfgFileName)) {
			properties.load(fis);
		} catch (IOException e) {
			throw new BaseException(e);
		}
		return properties;
	}

	public static String get(String propertyKey) {
		return getConfigProperties().getProperty(propertyKey);
	}
}
