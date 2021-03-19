package de.slag.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CurrencyUtilsTest {
	
	CurrencyUnit eur;
	CurrencyUnit foreign;
	
	@BeforeEach
	void setUp() {
		eur = CurrencyUtils.currency("EUR");
		foreign = CurrencyUtils.currency("JPY");
	}
	
	@Test
	void addTest() {		
		MonetaryAmount a = CurrencyUtils.newAmount(5, "EUR");
		MonetaryAmount b = CurrencyUtils.newAmount(7,"EUR");
		MonetaryAmount result = CurrencyUtils.add(a, b);
		assertEquals(CurrencyUtils.newAmount(12, "EUR"), result);
	}
	
	@Test
	void getNewAmountTest() {
		MonetaryAmount newAmount = CurrencyUtils.newAmount();
		
		assertNotNull(newAmount);
		assertEquals(0.0, newAmount.getNumber().doubleValue());
		assertEquals(eur, newAmount.getCurrency());
		
		MonetaryAmount newEur = CurrencyUtils.newAmount("EUR");
		assertNotNull(newEur);
		assertEquals(0.0, newEur.getNumber().doubleValue());
		assertEquals(eur, newEur.getCurrency());

		MonetaryAmount new5Eur = CurrencyUtils.newAmount(5,"EUR");
		assertNotNull(new5Eur);
		assertEquals(5.0, new5Eur.getNumber().doubleValue());
		assertEquals(eur, new5Eur.getCurrency());
}

	@Test
	void getConversionsTest() {
		CurrencyConversion conversion = CurrencyUtils.getConversion(foreign);
		assertNotNull(conversion);
		
		MonetaryAmount newAmount = CurrencyUtils.newAmount(50, eur);
		ExchangeRate exchangeRate = conversion.getExchangeRate(newAmount);
		NumberValue factor = exchangeRate.getFactor();
		double doubleValue = factor.doubleValue();
		BigDecimal valueOf = BigDecimal.valueOf(doubleValue);
		assertNotEquals(BigDecimal.ONE, valueOf);		
	}
}
