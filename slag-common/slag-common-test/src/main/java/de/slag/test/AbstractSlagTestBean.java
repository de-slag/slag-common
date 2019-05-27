package de.slag.test;

public abstract class AbstractSlagTestBean {
	
	private final Long id;
	
	public AbstractSlagTestBean(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
