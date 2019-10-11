package de.slag.common.db.h2;

public class InMemoryProperties extends AbstractH2Properties {

	public static final long serialVersionUID = 1L;

	public static final String USER = "sa";
	
	public static final String PASSWORD = "sa";

	public static final String URL = "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1";

	public InMemoryProperties() {
		super();
		setUrl(URL);
		setUser(USER);
		setPass(PASSWORD);	
	}

}
