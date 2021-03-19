package de.slag.common.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

class SleepUtilsTest {

	@Test
	void test() {
		long sleeptimeInMs = 5000;
		long start = System.currentTimeMillis();
		SleepUtils.sleepFor((int) sleeptimeInMs);
		long end = System.currentTimeMillis();
		long duration = end - start;
		assertThat(sleeptimeInMs, lessThanOrEqualTo(duration));
		long toleranceMax = sleeptimeInMs / 20;
		assertThat(sleeptimeInMs + toleranceMax, greaterThan(duration));
	}

}
