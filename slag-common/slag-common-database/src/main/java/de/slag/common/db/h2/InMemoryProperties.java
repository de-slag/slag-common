package de.slag.common.db.h2;

import java.util.Properties;

import org.hibernate.cfg.AvailableSettings;

public class InMemoryProperties extends Properties {

	private static final long serialVersionUID = 1L;

	public InMemoryProperties() {

		put(AvailableSettings.DRIVER, "org.h2.Driver");
		// put(AvailableSettings.URL, "jdbc:h2:~/test");
		put(AvailableSettings.URL, "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1");
		
		put(AvailableSettings.USER, "sa");
		put(AvailableSettings.PASS, "sa");
		put(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect");
		
		
	
	}

}
