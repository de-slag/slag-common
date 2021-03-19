package de.slag.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SleepUtils {

	private static final Log LOG = LogFactory.getLog(SleepUtils.class);

	public static void sleepFor(int sleeptimeInMs) {
		sleepFor0(sleeptimeInMs);
	}
	
	private static void sleepFor0(int sleeptimeInMs) {
		try {
			Thread.sleep(sleeptimeInMs);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sleepAbout(int sleeptimeInMs) {
		LOG.trace("sleep for (ms):" + sleeptimeInMs);
		sleepFor0(sleeptimeInMs);
	}
}
