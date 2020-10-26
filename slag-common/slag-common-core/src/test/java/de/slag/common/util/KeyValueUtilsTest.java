package de.slag.common.util;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KeyValueUtilsTest {
	
	private Map<String,String> map = new HashMap<>();
	
	@BeforeEach
	void setUp() {
		map.put("a", "7411");
		map.put("b", "0815");
	}

	@Test
	void integrationTest() {
		String join = KeyValueUtils.join(map);
		Map<String, String> split = KeyValueUtils.split(join);
		assertThat(map,is(split));
	}

}
