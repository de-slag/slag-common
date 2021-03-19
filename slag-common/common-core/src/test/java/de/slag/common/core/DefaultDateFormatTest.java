package de.slag.common.core;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

import de.slag.common.util.DateUtils;

class DefaultDateFormatTest {

	@Test
	void test() {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DefaultDateFormat.ISO_8601);
		final LocalDateTime of = LocalDateTime.of(2010, 01, 25, 17, 25);
		final Date date = DateUtils.toDate(of);
		final String format = simpleDateFormat.format(date);
		assertEquals("2010-25-25T17:01:00", format);
	}

}
