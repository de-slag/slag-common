package de.slag.common.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class DateUtilsTest {

	private static final Date DATE_2010_01_01_00_00 = new Date(1262300400000L);

	@Test
	public void testToCalendarLocalDate() {
		final Calendar instance = Calendar.getInstance();
		instance.set(2019, 3, 1, 0, 0, 0);
		final Calendar calendar = DateUtils.toCalendar(LocalDate.of(2019, 4, 1));
		assertThat(instance.getTimeInMillis(), Matchers.is(calendar.getTimeInMillis()));
		assertThat(calendar, Matchers.is(instance));

	}

	@Test
	public void testToCalendarDate() {
		final Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(0);
		final Calendar calendar = DateUtils.toCalendar(new Date(0));

	}

	@Test
	public void testToDateLocalDateTime() {
		final Date date = DateUtils.toDate(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
		assertThat(DATE_2010_01_01_00_00, Matchers.is(date));
	}

	@Test
	public void testToDateLocalDate() {
		LocalDate of = LocalDate.of(2010, 1, 1);
		Date date = DateUtils.toDate(of);
		assertThat(DATE_2010_01_01_00_00, Matchers.is(date));
	}

	@Test
	public void testToLocalDate() {
		
		assertTrue(false, "not implemented");
	}

	@Test
	public void testToLocalDateTime() {
		
		assertTrue(false, "not implemented");
	}

	@Test
	public void testGetEasterSundayInt() {
		assertThat(DateUtils.getEasterSunday(2019), Matchers.is(LocalDate.of(2019, 4, 21)));
	}

	@Test
	public void testGetEasterSundayYear() {
		assertThat(DateUtils.getEasterSunday(Year.of(2019)), Matchers.is(LocalDate.of(2019, 4, 21)));
	}

}
