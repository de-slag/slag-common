package de.slag.common.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

import de.slag.common.model.EntityBean;

@Entity
public class SystemLog extends EntityBean {

	@Column
	private String info;

	public SystemLog() {
		super();
	}

	public SystemLog(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "SystemLog [getCreatedAt()=" + getCreatedAt() + ", info=" + info + "]";
	}

}
