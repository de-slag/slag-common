package de.slag.common.db;

import java.util.Random;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityBean {
	
	@Id
	protected Long id = new Random().nextLong();

	public Long getId() {
		return id;
	}

}
