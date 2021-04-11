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

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt = new Date();

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
	 * 
	 * @param validUntil
	 */
	void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityBean other = (EntityBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

}
