package de.slag.common.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.slag.common.model.EntityBean;

@Entity
public class SystemLog extends EntityBean {

	@Column
	private String info;

	@Enumerated(EnumType.STRING)
	private Severity severity;

	SystemLog() {
		super();
	}

	public SystemLog(String info) {
		this(Severity.INFO, info);
	}

	public SystemLog(Severity severity, String info) {
		this.info = info;
		this.severity = severity;

	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "SystemLog [info=" + info + ", severity=" + severity + ", getCreatedAt()=" + getCreatedAt() + "]";
	}

	public enum Severity {
		INFO,

		WARN,

		ERROR
	}




}
