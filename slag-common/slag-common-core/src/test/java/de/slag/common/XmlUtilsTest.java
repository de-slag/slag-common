package de.slag.common;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.function.Consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

import de.slag.common.util.XmlUtils;
public class XmlUtilsTest {
	
	private static final Log LOG = LogFactory.getLog(XmlUtilsTest.class);

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
		assertNotNull(in);
	}
	
	
}
