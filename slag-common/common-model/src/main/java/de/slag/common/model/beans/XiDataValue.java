package de.slag.common.model.beans;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import de.slag.common.model.EntityBean;

@Entity
public class XiDataValue extends EntityBean {

	@ManyToOne
	private XiData xiData;

	@Basic
	private String attribute;
	
	@Basic
	private String value;

	public XiData getXiData() {
		return xiData;
	}

	public void setXiData(XiData xiData) {
		this.xiData = xiData;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
