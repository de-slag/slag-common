package de.slag.common.db.h2;

public class H2FileProperties extends AbstractH2Properties {

	private static final long serialVersionUID = 1L;
	
	public H2FileProperties(String url, String user, String pass) {
		super();
		setUrl(url);
		setUser(user);
		setPass(pass);
		
		// put(AvailableSettings.URL, "jdbc:h2:~/test");
	}

}
