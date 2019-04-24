import java.util.Properties;

import de.slag.common.db.HibernateProperties;
import de.slag.common.db.HibernateSupport;
import de.slag.common.db.HibernateSupportFactory;

public class HibernateSupportTest {
	
	public void test() {
		final Properties properties = new Properties();		
		HibernateProperties hproperties = new HibernateProperties(properties);
		final HibernateSupport hibernateSupport = HibernateSupportFactory.create(hproperties);
	}

}
