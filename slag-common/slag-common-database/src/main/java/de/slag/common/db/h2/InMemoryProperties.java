package de.slag.common.db.h2;

public class InMemoryProperties extends AbstractH2Properties {

	private static final long serialVersionUID = 1L;

	public InMemoryProperties() {
		super();
		setUrl("jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1");
		setUser("sa");
		setPass("sa");	
	}

}
