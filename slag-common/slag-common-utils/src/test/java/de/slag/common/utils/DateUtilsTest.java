package de.slag.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

class DateUtilsTest {


	@Ignore
	@Test
	void testToCalendarLocalDate() {
		final Calendar instance = Calendar.getInstance();
		instance.set(2019, 3, 1, 0, 0, 0);
		final Calendar calendar = DateUtils.toCalendar(LocalDate.of(2019, 4, 1));
		Assert.assertThat(calendar, Matchers.is(instance));
		
	}

	@Ignore
	@Test
	void testToCalendarDate() {
		final Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(0);
		final Calendar calendar = DateUtils.toCalendar(new Date(0));
		
	}

	@Ignore
	@Test
	void testToDateLocalDateTime() {
		final Date date = DateUtils.toDate(LocalDateTime.of(1970, 1, 1, 0, 0, 0));
	}

	@Ignore
	@Test
	void testToDateLocalDate() {
		Assert.assertTrue(false); // not implemented
	}

	@Test
	void testToLocalDate() {
		Assert.assertTrue(false); // not implemented
	}

	@Test
	void testToLocalDateTime() {
		Assert.assertTrue(false); // not implemented
	}

	@Test
	void testGetEasterSundayInt() {
		Assert.assertThat(DateUtils.getEasterSunday(2019), Matchers.is(LocalDate.of(2019, 4, 21)));
	}

	@Test
	void testGetEasterSundayYear() {
		Assert.assertThat(DateUtils.getEasterSunday(Year.of(2019)), Matchers.is(LocalDate.of(2019, 4, 21)));
	}

}
