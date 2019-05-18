package de.slag.common.utils.test;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import org.junit.Assert;
import org.junit.Test;

import de.slag.common.utils.DateUtils;

public class DateUtilsTest {
	
	@Test
	public void test() {
		final LocalDate easterSunday = DateUtils.getEasterSunday(Year.of(2019));
		Assert.assertTrue(easterSunday.getMonth() == Month.APRIL);
		Assert.assertTrue(easterSunday.getDayOfMonth() == 21);
	}

}
