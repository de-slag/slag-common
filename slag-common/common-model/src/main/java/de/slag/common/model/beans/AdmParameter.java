package de.slag.common.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

import de.slag.common.model.EntityBean;

@Entity
public class AdmParameter extends EntityBean {
	
	@Column(length = 1000)
	private String configKey;
	
	@Column(length = 4000)
	private String configValue;

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
	

}
