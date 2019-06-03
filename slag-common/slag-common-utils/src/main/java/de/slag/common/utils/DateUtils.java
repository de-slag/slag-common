package de.slag.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Calendar toCalendar(LocalDate ld) {
		return toCalendar(toDate(ld));
	}

	public static Calendar toCalendar(final Date date) {
		final Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		return instance;
	}

	public static Date toDate(final LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date toDate(final LocalDate localDate) {
		return toDate(LocalDateTime.of(localDate, LocalTime.MIDNIGHT));
	}

	public static LocalDate toLocalDate(final Date date) {
		if (date == null) {
			return null;
		}
		return toLocalDateTime(date).toLocalDate();
	}

	public static LocalDateTime toLocalDateTime(final Date date) {
		if (date == null) {
			return null;
		}
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public static LocalDate getEasterSunday(int year) {
		return getEasterSunday(Year.of(year));
	}

	public static LocalDate getEasterSunday(Year year) {
		final int y = year.getValue();
		final int a = y % 19;
		final int b = y / 100;
		final int c = y % 100;
		final int d = b / 4;
		final int e = b % 4;
		final int f = (b + 8) / 25;
		final int g = (b - f + 1) / 3;
		final int h = (19 * a + b - d - g + 15) % 30;
		final int i = c / 4;
		final int k = c % 4;
		final int m = (32 + 2 * e + 2 * i - h - k) % 7;
		final int n = (a + 11 * h + 22 * m) / 451;
		final int month = (h + m - 7 * n + 114) / 31;
		final int day = (((h + m - (7 * n) + 114) % 31) + 1);
		return LocalDate.of(y, month, day);
	}

}
