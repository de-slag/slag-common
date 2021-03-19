package de.slag.common.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;

public class MathUtils {

	public static final MathContext DEFAULT_MATH_CONTEXT = new MathContext(12,
			RoundingMode.HALF_UP);

	public static BigDecimal addAll(Collection<BigDecimal> values) {
		BigDecimal result = BigDecimal.ZERO;

		for (final BigDecimal bigDecimal : values) {
			result = result.add(bigDecimal);
		}

		return result;
	}

	public static BigDecimal divide(BigDecimal value, int divisor) {
		return value.divide(BigDecimal.valueOf(divisor), DEFAULT_MATH_CONTEXT);
	}
	
	public static BigDecimal average(Collection<BigDecimal> values) {
		return divide(addAll(values), values.size());
	}

}
