package de.slag.common.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.Builder;
import org.hibernate.cfg.AvailableSettings;

import de.slag.common.db.hibernate.HibernateDbUpdateUtils;

public class HibernateSupportBuilder implements Builder<HibernateSupport> {

	private Collection<Class<?>> registerClasses = new ArrayList<Class<?>>();

	// hibernate.dialect
	private String hibernateDialect;

	private String url;

	private String user;

	private String password;

	private String driver;

	public HibernateSupportBuilder registerClasses(Collection<Class<?>> registerClasses) {
		this.registerClasses.addAll(registerClasses);
		return this;
	}

	public HibernateSupportBuilder hibernateDialect(String hibernateDialect) {
		this.hibernateDialect = hibernateDialect;
		return this;
	}

	@Override
	public HibernateSupport build() {
		if (registerClasses.isEmpty()) {
			throw new IllegalStateException("no registered classes");
		}
		if (StringUtils.isBlank(hibernateDialect)) {
			throw new IllegalArgumentException("not setted: hibernateDialect");
		}
		if (StringUtils.isBlank(url)) {
			throw new IllegalArgumentException("not setted: url");
		}
		if (StringUtils.isBlank(user)) {
			throw new IllegalArgumentException("not setted: user");
		}
		if (StringUtils.isBlank(password)) {
			throw new IllegalArgumentException("not setted: password");
		}
		if (StringUtils.isBlank(driver)) {
			throw new IllegalArgumentException("not setted: driver");
		}
		Properties props = new Properties();
		props.setProperty(AvailableSettings.DIALECT, hibernateDialect);
		props.setProperty(AvailableSettings.URL, url);
		props.setProperty(AvailableSettings.USER, user);
		props.setProperty(AvailableSettings.PASS, password);
		props.setProperty(AvailableSettings.DRIVER, driver);
		props.setProperty(AvailableSettings.HBM2DDL_AUTO, "validate");

		if (!HibernateDbUpdateUtils.isValid(props, registerClasses)) {
			HibernateDbUpdateUtils.update(props, registerClasses);
		}

		return new HibernateSupportImpl(registerClasses, props);
	}

	public HibernateSupportBuilder url(String url) {
		this.url = url;
		return this;
	}

	public HibernateSupportBuilder user(String user) {
		this.user = user;
		return this;
	}

	public HibernateSupportBuilder password(String password) {
		this.password = password;
		return this;
	}

	public HibernateSupportBuilder driver(String driver) {
		this.driver = driver;
		return this;
	}

}
