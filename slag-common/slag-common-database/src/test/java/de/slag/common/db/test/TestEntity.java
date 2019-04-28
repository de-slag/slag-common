package de.slag.common.db.test;
import javax.persistence.Basic;
import javax.persistence.Entity;

import de.slag.common.db.EntityBean;



@Entity
public class TestEntity extends EntityBean {
	
	@Basic
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
