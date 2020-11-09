package de.slag.basic.backend.impl.adm;

import java.util.Map;

public interface AdmConfigService {

	String getProperty(String key);

	void putProperty(String key, String value);

	Map<String, String> getProperties(String key);

}
