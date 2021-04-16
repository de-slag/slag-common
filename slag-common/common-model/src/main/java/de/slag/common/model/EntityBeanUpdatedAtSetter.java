package de.slag.common.model;

import java.util.Date;
import java.util.function.BiConsumer;

public class EntityBeanUpdatedAtSetter implements BiConsumer<EntityBean, Date> {

	@Override
	public void accept(EntityBean t, Date u) {
		t.setUpdatedAt(u);		
	}
}
