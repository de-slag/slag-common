package de.slag.common.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import de.slag.common.model.EntityBean;

@Entity
public class XiData extends EntityBean {

	@Column
	private String type;
	
	@OneToMany(mappedBy = "xiData", cascade = CascadeType.ALL)
	private List<XiDataValue> values = new ArrayList<>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<XiDataValue> getValues() {
		return values;
	}
}
