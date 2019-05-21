package de.slag.common.db.h2;

import java.util.Properties;

import org.hibernate.cfg.AvailableSettings;

class AbstractH2Properties extends Properties {

	private static final long serialVersionUID = 1L;

	public AbstractH2Properties() {
		put(AvailableSettings.DRIVER, "org.h2.Driver");
		put(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect");
	}

	protected void setPass(final String string) {
		put(AvailableSettings.PASS, string);
	}

	protected void setUser(final String string) {
		put(AvailableSettings.USER, string);
	}

	protected void setUrl(final String string) {
		put(AvailableSettings.URL, string);
	}

}
