package de.slag.common.model.beans;

import java.util.Arrays;
import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import de.slag.common.model.EntityBean;

@Entity
public class XiData extends EntityBean {

	@Column
	private String type;

	@Lob
	@Column
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Properties toProperties() {
		final Properties properties = new Properties();
		Arrays.stream(value.split(";")).forEach(keyAndValue -> {
			String[] split = keyAndValue.split("=");
			final String key = split[0];
			final String value = split[1];
			properties.setProperty(key, value);
		});
		return properties;
	}

	@Override
	public String toString() {
		return "XiData [type=" + type + ", value=" + value + "]";
	}

}
