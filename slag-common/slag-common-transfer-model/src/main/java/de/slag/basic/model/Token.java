package de.slag.basic.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {
	
	public static Token of(String tokenString) {
		final Token token = new Token();
		token.setTokenString(tokenString);
		return token;
	}
	
	private String tokenString;

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
	
	

}
