package de.slag.common.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.slag.common.model.beans.AdmParameter;

class EntityBeanIdSetterTest {

	EntityBean entityBean;

	@BeforeEach
	void setUp() {
		entityBean = new AdmParameter();
	}

	@Test
	void testAccept() {
		final EntityBeanIdSetter entityBeanIdSetter = new EntityBeanIdSetter();

		assertNull(entityBean.getId());
		entityBeanIdSetter.accept(entityBean, 4711L);
		assertEquals(4711L, entityBean.getId());
	}

}
