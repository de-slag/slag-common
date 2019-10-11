package de.slag.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.slag.common.ModelConstants;

@MappedSuperclass
public abstract class EntityBean {
	
	@Id
	@GeneratedValue
	@Column(updatable = false, nullable = false)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	private Date validUntil = new Date(ModelConstants.DATE_9999_12_31_23_59_59);

	public Long getId() {
		return id;
	}

	void setId(Long id) {
		// this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	/**
	 * only for deletion
	 * @param validUntil
	 */
	void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
