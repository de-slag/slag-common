package de.slag.common.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import de.slag.common.base.BaseException;

public class ConfigSupport {

	private Properties properties;

	public ConfigSupport() {
		properties = new Properties();
		String configFileProperty = System.getProperty("config.file");
		if (StringUtils.isBlank(configFileProperty)) {
			return;
		}
		File file = new File(configFileProperty);
		if (!file.exists()) {
			return;
		}
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new BaseException(e);
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			throw new BaseException(e);
		}
	}

	public Optional<String> getValue(String key) {
		String property = properties.getProperty(key);
		if(!properties.containsKey(key)) {
			return Optional.empty();
		}
		return Optional.of(property);
	}

}
