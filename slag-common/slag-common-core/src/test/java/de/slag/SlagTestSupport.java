package de.slag;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

public class SlagTestSupport {
	public static <T> void assertThat(T actual, Matcher<T> matcher) {
		 MatcherAssert.assertThat("", actual, matcher);
	}
	
	public static class AssertionException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public AssertionException(String s) {
			super(s);
		}
	}
}
