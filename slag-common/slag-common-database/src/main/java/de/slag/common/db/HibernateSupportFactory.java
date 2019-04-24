package de.slag.common.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSupportFactory {
	
	public static HibernateSupport create(final HibernateProperties properties) {
		final Configuration configuration = new Configuration();
		
		configuration.setProperty("hibernate.connection.driver_class", properties.getDriverClass());
		configuration.setProperty("hibernate.connection.url", properties.getUrl());
		configuration.setProperty("hibernate.connection.username", properties.getUsername());
		configuration.setProperty("hibernate.connection.password", properties.getPassword());
		configuration.setProperty("hibernate.dialect", properties.getDialect());
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.connection.pool_size", "10");
		
		
		// "update" or "validate"
//		if (StringUtils.isNotBlank(hbm2ddl)) {
//			configuration.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
//		}
		
		configuration.setPhysicalNamingStrategy(new DefaultNamingStrategy());
		final SessionFactory sf = configuration.configure().buildSessionFactory();
		return new HibernateSupport(sf);
	}
	


}
