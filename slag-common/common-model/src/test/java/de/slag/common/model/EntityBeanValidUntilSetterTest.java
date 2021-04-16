package de.slag.common.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.slag.common.ModelConstants;
import de.slag.common.model.beans.AdmParameter;

class EntityBeanValidUntilSetterTest {

	Long DATE_2010_01_01_00_00_00 = 1609455600L;

	EntityBean entityBean;

	@BeforeEach
	void setUp() {
		entityBean = new AdmParameter();
	}

	@Test
	void test() {
		final EntityBeanValidUntilSetter entityBeanValidUntilSetter = new EntityBeanValidUntilSetter();

		assertEquals(new Date(ModelConstants.DATE_9999_12_31_23_59_59), entityBean.getValidUntil());
		entityBeanValidUntilSetter.accept(entityBean, new Date(DATE_2010_01_01_00_00_00));
		assertEquals(new Date(DATE_2010_01_01_00_00_00), entityBean.getValidUntil());
	}

}
