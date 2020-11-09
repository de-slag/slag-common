package de.slag.basic.backend.impl.adm;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AdmConfigServiceImpl implements AdmConfigService {

	private static final Log LOG = LogFactory.getLog(AdmConfigServiceImpl.class);

	private static final String SLAG_MAIL_BACKEND_PROPERTIES = "slag-mail-backend.properties";

	private Properties properties = new Properties();

	@PostConstruct
	public void init() throws IOException {
		final InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(SLAG_MAIL_BACKEND_PROPERTIES);
		if (inputStream == null) {
			throw new RuntimeException(SLAG_MAIL_BACKEND_PROPERTIES + " not found.");
		}
		this.properties.load(inputStream);
		LOG.info("initializing complete");
	}

	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	@Override
	public Map<String, String> getProperties(String key) {
		List<String> selectedKeys = properties.keySet()
				.stream()
				.map(k -> (String) k)
				.filter(k -> k.startsWith(key))
				.collect(Collectors.toList());

		final Map<String, String> selectedProperties = new HashMap<>();
		selectedKeys.forEach(k -> selectedProperties.put(k, properties.getProperty(k)));
		return selectedProperties;
	}

	@Override
	public void putProperty(String key, String value) {
		if (value == null) {
			properties.remove(key);
		}
		properties.put(key, value);
	}

}
