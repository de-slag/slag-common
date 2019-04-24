package de.slag.common.db;

import java.util.Properties;

public class HibernateProperties extends Properties {
	
	public static final String DRIVER_CLASS = "hibernate.driverclass";
	public static final String URL = "hibernate.url";
	public static final String USERNAME = "hibernate.username";
	public static final String PASSWORD = "hibernate.password";
	public static final String DIALECT = "hibernate.dialect";

	private static final long serialVersionUID = 1L;
	
	public HibernateProperties(Properties properties) {
		super(properties);
	}

	public String getDriverClass() {
		return getProperty(DRIVER_CLASS);
	}

	public String getUrl() {
		return getProperty(URL);
	}

	public String getUsername() {
		return getProperty(USERNAME);
	}

	public String getPassword() {
		return getProperty(PASSWORD);
	}

	public String getDialect() {
		return getProperty(DIALECT);
	}
	
}
