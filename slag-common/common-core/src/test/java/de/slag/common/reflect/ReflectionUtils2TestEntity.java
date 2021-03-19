package de.slag.common.reflect;

import org.apache.commons.lang3.BooleanUtils;

public class ReflectionUtils2TestEntity {
	
	private Long id;
	
	private Integer number;
	
	private Boolean valid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public boolean isValid() {
		return BooleanUtils.isTrue(valid);
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}
