package de.slag.common.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class SlagProperties {

	public static Properties getConfigProperties() {
		final File userHome = org.apache.commons.lang3.SystemUtils.getUserHome();
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

}
