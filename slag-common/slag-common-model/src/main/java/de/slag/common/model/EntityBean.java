package de.slag.common.model;

import java.util.Random;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityBean {
	
	@Id
	private Long id = new Random().nextLong();
	
	

	public Long getId() {
		return id;
	}

}
