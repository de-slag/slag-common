package de.slag.common.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.function.BooleanSupplier;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DateUtilsTest {

	private static final long DATE_2010_01_01_00_00_MILLIS = 1262300400000L;

	private static final Date DATE_2010_01_01_00_00 = new Date(DATE_2010_01_01_00_00_MILLIS);

	private static final Long TOLERANCE_IN_MS = 999L;

	@BeforeEach
	void assumeTimeZone() {
		final ZoneId currentZoneId = ZoneId.of("Europe/Berlin");
		BooleanSupplier a = () -> {
			return ZoneId.systemDefault().equals(currentZoneId);
		};
		assumeTrue(a);
	}

	@Test
	public void testToCalendarLocalDate() {
		final Calendar instance = Calendar.getInstance();
		instance.set(2019, 3, 1, 0, 0, 0);
		final Calendar calendar = DateUtils.toCalendar(LocalDate.of(2019, 4, 1));
		long actualTimeInMillis = calendar.getTimeInMillis();
		assertThat(instance.getTimeInMillis(), Matchers.greaterThanOrEqualTo(actualTimeInMillis));
		assertThat(instance.getTimeInMillis(), Matchers.lessThanOrEqualTo(actualTimeInMillis + TOLERANCE_IN_MS));

	}

	@Test
	public void testToCalendarDate() {
		final Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(0);
		final Calendar calendar = DateUtils.toCalendar(new Date(0));
		assertNotNull(calendar);

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
		final int minutes5 = 60 * 1000 * 5;
		final int hours23 = 3600 * 1000 * 23;
		long date_2010_01_01_00_05_millis = DATE_2010_01_01_00_00_MILLIS + minutes5;
		long date_2010_01_01_23_00_millis = DATE_2010_01_01_00_00_MILLIS + hours23;

		final Date smallDifferentDate = new Date(date_2010_01_01_00_05_millis);
		final Date bigDifferentDate = new Date(date_2010_01_01_23_00_millis);
		final Date defaultDate = new Date(DATE_2010_01_01_00_00_MILLIS);

		final LocalDate defaultLocalDate = DateUtils.toLocalDate(defaultDate);
		final LocalDate smallDifferentLocalDate = DateUtils.toLocalDate(smallDifferentDate);
		final LocalDate bigDifferentLocalDate = DateUtils.toLocalDate(bigDifferentDate);

		assertEquals(defaultLocalDate, smallDifferentLocalDate);
		assertEquals(defaultLocalDate, bigDifferentLocalDate);

	}

	@Disabled
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
