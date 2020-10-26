package de.slag.common.util;

import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class DateUtilsTestFirstDayOfQuater {

	@Test
	void testFebruary() {
		LocalDate firstDayOfQuater = DateUtils.firstDayOfQuater(LocalDate.of(2010, 2, 12));
		assertThat(LocalDate.of(2010, 1, 1), Matchers.is(firstDayOfQuater));
	}

	@Test
	void testSeptember() {
		LocalDate firstDayOfQuater = DateUtils.firstDayOfQuater(LocalDate.of(2010, 9, 27));
		assertThat(LocalDate.of(2010, 7, 1), Matchers.is(firstDayOfQuater));
	}
	@Test
	void testApril() {
		LocalDate firstDayOfQuater = DateUtils.firstDayOfQuater(LocalDate.of(2010, 4, 4));
		assertThat(LocalDate.of(2010, 4, 1), Matchers.is(firstDayOfQuater));
	}

}
