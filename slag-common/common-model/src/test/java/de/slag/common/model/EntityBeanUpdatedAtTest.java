package de.slag.common.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.slag.common.model.beans.AdmParameter;

class EntityBeanUpdatedAtTest {

	EntityBean entityBean;

	@BeforeEach
	void setUp() {
		entityBean = new AdmParameter();
	}

	@Test
	void test() {
		final EntityBeanUpdatedAtSetter entityBeanUpdatedAtSetter = new EntityBeanUpdatedAtSetter();
		
		assertTrue(entityBean.getUpdatedAt().before(new Date()));

		final Date updatedAt = new Date();
		entityBeanUpdatedAtSetter.accept(entityBean, updatedAt);
		assertEquals(updatedAt, entityBean.getUpdatedAt());
	}
}
