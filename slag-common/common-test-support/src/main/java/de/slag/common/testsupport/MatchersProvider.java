package de.slag.common.testsupport;

import java.time.LocalDate;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public interface MatchersProvider {

	default Matcher<LocalDate> is(LocalDate t) {
		return new BaseMatcher<LocalDate>() {

			@Override
			public boolean matches(Object item) {
				return t.equals(item);
			}

			@Override
			public void describeTo(Description description) {
				// TODO Auto-generated method stub

			}
		};
	}

}
