package de.slag.common.db.h2;

import java.util.Properties;

import org.hibernate.cfg.AvailableSettings;

public class AbstractH2Properties extends Properties {

	private static final long serialVersionUID = 1L;

	public static final String DIALECT = "org.hibernate.dialect.H2Dialect";
	public static final String DRIVER = "org.h2.Driver";

	public AbstractH2Properties() {
		put(AvailableSettings.DRIVER, DRIVER);
		put(AvailableSettings.DIALECT, DIALECT);
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
