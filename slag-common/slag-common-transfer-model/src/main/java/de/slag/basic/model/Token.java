package de.slag.basic.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {
	
	private String tokenString;

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
	
	

}
