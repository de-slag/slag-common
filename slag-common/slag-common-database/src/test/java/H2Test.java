import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.Driver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class H2Test {
	
	@Before
	public void setup() {
		Assert.assertNotNull(new Driver());
	}
	
	@Test
	public void test() throws SQLException {		
		try (final Connection connection = DriverManager.getConnection("jdbc:h2:~/test","sa","sa")) {
			Assert.assertTrue(connection != null);		
		}
	}

}
