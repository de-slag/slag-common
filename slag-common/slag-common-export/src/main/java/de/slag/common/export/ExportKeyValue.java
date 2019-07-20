package de.slag.common.export;

import de.slag.common.api.KeyValuePair;

public class ExportKeyValue implements KeyValuePair<String, Object> {

	private String key;
	
	private Object value;	

	public ExportKeyValue(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Object getValue() {
		return value;
	}

}
