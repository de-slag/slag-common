package de.slag.common.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashUtilsTest {

	@Test
	void sha256Test() {
		String hash = HashUtils.hash("this is a test", HashUtils.Algorythm.SHA256);
		assertEquals("2e99758548972a8e8822ad47fa1017ff72f06f3ff6a016851f45c398732bc50c", hash);
	}

}
