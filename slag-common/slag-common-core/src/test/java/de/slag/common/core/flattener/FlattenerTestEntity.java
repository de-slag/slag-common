package de.slag.common.core.flattener;

import org.apache.commons.lang3.BooleanUtils;

public class FlattenerTestEntity {
	private Long id;
	
	private String name;
	
	private Integer number;
	
	private Boolean valid;
	
	public static FlattenerTestEntity create(Long id) {
		FlattenerTestEntity flattenerTestEntity = new FlattenerTestEntity();
		flattenerTestEntity.id = id;
		return flattenerTestEntity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getId() {
		return id;
	}
}