package de.slag.common.core.eop;

public class EopType {
	
	private final String name;

	EopType(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "EopType [name=" + name + "]";
	}
	
	

}
