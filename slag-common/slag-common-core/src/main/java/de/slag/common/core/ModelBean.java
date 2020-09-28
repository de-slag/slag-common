package de.slag.common.core;

import java.util.Date;

/**
 * Deprecated use package common-model instead
 * @author sebastian
 *
 */
@Deprecated
public abstract class ModelBean {

	private Long id;
	
	private Date validUntil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

}
