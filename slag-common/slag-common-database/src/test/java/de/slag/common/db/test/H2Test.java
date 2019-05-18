package de.slag.common.db.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.slag.common.db.hibernate.HibernateUtils;

public class H2Test {

	@Before
	public void setup() throws SQLException {
		Assert.assertNotNull(new org.h2.Driver());
		// Assert.assertNotNull(new com.mysql.cj.jdbc.Driver());
	}

	@Ignore
	@Test
	public void test() throws SQLException {
		try (final Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
			Assert.assertTrue(connection != null);
			final String schema = connection.getSchema();
			Assert.assertNotNull(schema);
		}
	}

	@Ignore
	@Test
	public void hibernateTest() {
		Properties p = new Properties();

		p.put("hibernate.connection.driver_class", "org.h2.Driver");
		p.put("hibernate.connection.url", "jdbc:h2:~/test");
		p.put("hibernate.connection.username", "sa");
		p.put("hibernate.connection.password", "sa");
		p.put("hibernate.dialect", " org.hibernate.dialect.H2Dialect");
		p.put("hibernate.show_sql", "true");
		p.put("hibernate.connection.pool_size", "10");

		final Configuration configuration = new Configuration();

		final Properties properties = configuration.getProperties();

		properties.put("hibernate.connection.driver_class", "org.h2.Driver");
		properties.put("hibernate.connection.url", "jdbc:h2:~/test");
		properties.put("hibernate.connection.username", "sa");
		properties.put("hibernate.connection.password", "sa");
		properties.put("hibernate.dialect", " org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.connection.pool_size", "10");

		properties.entrySet().forEach(e -> out(e));

		configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
		configuration.setProperty("hibernate.connection.username", "sa");
		configuration.setProperty("hibernate.connection.password", "sa");
		configuration.setProperty("hibernate.dialect", " org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.connection.pool_size", "10");

		configuration.addAnnotatedClass(TestEntity.class);

		configuration.configure();

		properties.entrySet().forEach(e -> out(e));

		final SessionFactory buildSessionFactory = configuration.buildSessionFactory();
	}

	private void out(Object o) {
		System.out.println(o);
	}

	@Test
	public void testSessionFactory() {

		StandardServiceRegistry registry;

		// Create registry builder
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

		// Hibernate settings equivalent to hibernate.cfg.xml's properties
		Map<String, String> settings = new HashMap<>();
		settings.put(Environment.DRIVER, "org.h2.Driver");
		settings.put(Environment.URL, "jdbc:h2:~/test");
		settings.put(Environment.USER, "sa");
		settings.put(Environment.PASS, "sa");
		settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
		settings.put("hibernate.show_sql", "true");
		settings.put("hibernate.connection.pool_size", "10");

		// Apply settings
		registryBuilder.applySettings(settings);

		// Create registry
		registry = registryBuilder.build();

		// Create MetadataSources
		MetadataSources sources = new MetadataSources(registry);

		// Create Metadata
		Metadata metadata = sources.getMetadataBuilder().build();

		// Create SessionFactory
		final SessionFactory build = metadata.getSessionFactoryBuilder().build();
		Assert.assertNotNull(build);

	}

	/**
	 * jdbc.driverClassName=org.h2.Driver
	 * jdbc.url=jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1
	 * 
	 * hibernate.dialect=org.hibernate.dialect.H2Dialect
	 * hibernate.hbm2ddl.auto=create
	 */

	@Test
	public void inMemoryTest() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DRIVER, "org.h2.Driver");
		properties.put(AvailableSettings.URL, "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1");
		properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect");
		properties.put(AvailableSettings.HBM2DDL_AUTO, "create");
		
		properties.put(Environment.USER, "sa");
		properties.put(Environment.PASS, "sa");
		
		HibernateUtils.access(HibernateUtils.EMPTY_SESSION_CONSUMER, properties);

	}

}
