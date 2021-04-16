package de.slag.common.model;

import java.util.Date;
import java.util.function.BiConsumer;

public class EntityBeanValidUntilSetter implements BiConsumer<EntityBean, Date> {

	@Override
	public void accept(EntityBean t, Date u) {
		t.setValidUntil(u);
	}
}
