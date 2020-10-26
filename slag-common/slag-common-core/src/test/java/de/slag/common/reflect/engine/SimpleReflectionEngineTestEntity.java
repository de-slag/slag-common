package de.slag.common.reflect.engine;

import org.apache.commons.lang3.BooleanUtils;

public class SimpleReflectionEngineTestEntity {
	
	private String svar;
	
	private Long lvar;
	
	private Boolean bvar;

	public String getSvar() {
		return svar;
	}

	public void setSvar(String svar) {
		this.svar = svar;
	}

	public Long getLvar() {
		return lvar;
	}

	public void setLvar(Long lvar) {
		this.lvar = lvar;
	}

	public boolean isBvar() {
		return BooleanUtils.isTrue(bvar);
	}

	public void setBvar(Boolean bvar) {
		this.bvar = bvar;
	}

}
