package de.slag.common.model.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import de.slag.common.model.EntityBean;

@Entity
public class XiData extends EntityBean {

	@Column
	private String type;

	@OneToMany(mappedBy = "xiData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	public void addValue(String attribute, String value) {
		XiDataValue xiDataValue = new XiDataValue();
		xiDataValue.setXiData(this);
		xiDataValue.setAttribute(attribute);
		xiDataValue.setValue(value);
		values.add(xiDataValue);
	}

	public String getValue(String attribute) {
		final Optional<XiDataValue> findAny = values.stream().filter(v -> attribute.equals(v.getAttribute())).findAny();
		if (findAny.isEmpty()) {
			return null;
		}
		return findAny.get().getValue();
	}
}
