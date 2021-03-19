package de.slag.basic.backend.api;

public class BasicBackendServiceReturnValue {

	private final String value;

	private BasicBackendServiceReturnValue() {
		this(null);
	}
	
	private BasicBackendServiceReturnValue(String value) {
		this.value = value;
	}

	public static BasicBackendServiceReturnValue of(String message) {
		return new BasicBackendServiceReturnValue(message);
	}

	public String getValue() {
		return value;
	}
	
	

}
