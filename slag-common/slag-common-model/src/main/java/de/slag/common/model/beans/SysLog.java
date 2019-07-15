package de.slag.common.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.slag.common.model.EntityBean;

@Entity
public class SysLog extends EntityBean implements SysLogEntry {

	@Column(length = 4000)
	private String info;

	@Enumerated(EnumType.STRING)
	private Severity severity;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public enum Severity {
		INFO,

		WARN,

		ERROR
	}

	@Override
	public String toString() {
		return "SysLog ["+getCreatedAt() + ", " + severity + ": " + info + "]";
	}
}
