package de.slag.basic.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BackendException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BackendException(String s) {
		super(s);
	}

}
