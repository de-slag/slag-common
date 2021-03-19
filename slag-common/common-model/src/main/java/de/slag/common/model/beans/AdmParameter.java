package de.slag.common.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

import de.slag.common.model.EntityBean;

@Entity
public class AdmParameter extends EntityBean {
	
	@Column(length = 1000)
	private String key;
	
	@Column(length = 4000)
	private String value;

}
