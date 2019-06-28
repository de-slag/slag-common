package de.slag.common.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityBean {
	
	@Id
	private Long id;
	
	

	public Long getId() {
		return id;
	}
	
	void setId(Long id) {
		this.id = id;
	}
	
	

}
