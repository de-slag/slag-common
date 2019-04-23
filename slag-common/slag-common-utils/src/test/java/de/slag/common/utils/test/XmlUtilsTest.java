package de.slag.common.utils.test;

import java.util.function.Consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slag.common.logging.LoggingUtils;
import de.slag.common.utils.XmlUtils;

public class XmlUtilsTest {
	
	private static final Log LOG = LogFactory.getLog(XmlUtilsTest.class);
	
	@Before
	public void setup() {
		LoggingUtils.activateLogging();
	}

	@Test
	public void testConsumer() {
		final Consumer<String> consumer = string -> LOG.info(string);
		XmlUtils.out(newXmlTestEntity(), consumer);
	}

	private XmlTestEntity newXmlTestEntity() {
		return new XmlTestEntity("xml-test");
	}
	
	@Test
	public void test() {
		final String out = XmlUtils.out(newXmlTestEntity());
		LOG.info(out);
		final XmlTestEntity in = XmlUtils.in(XmlTestEntity.class, out);
		Assert.assertNotNull(in);
	}
	
	
}
