package de.slag.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlTestEntity {
	
	private String name;
	
	public XmlTestEntity() {
		
	}
	
	public XmlTestEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
