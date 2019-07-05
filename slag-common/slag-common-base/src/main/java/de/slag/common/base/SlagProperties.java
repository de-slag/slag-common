package de.slag.common.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.SystemUtils;

public final class SlagProperties {

	public static Properties getConfigProperties() {
		final File userHome = SystemUtils.getUserHome();
		String absolutePath = userHome.getAbsolutePath();
		String file = "slag.cfg";
		String cfgFile = absolutePath + "/" + file;
	
		if (!new File(cfgFile).exists()) {
			try {
				new File(cfgFile).createNewFile();
			} catch (IOException e) {
				throw new BaseException(e);
			}
		}
	
		final Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(cfgFile)) {
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
