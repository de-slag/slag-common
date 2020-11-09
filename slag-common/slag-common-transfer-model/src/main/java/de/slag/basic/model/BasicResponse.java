package de.slag.basic.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BasicResponse {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
