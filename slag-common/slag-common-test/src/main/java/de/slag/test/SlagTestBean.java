package de.slag.test;

public class SlagTestBean extends AbstractSlagTestBean {

	private String name;
	
	SlagTestBean(Long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
