package de.slag.common.model;

import java.util.function.BiConsumer;

public class EntityBeanIdSetter implements BiConsumer<EntityBean, Long> {

	@Override
	public void accept(EntityBean bean, Long id) {
		bean.setId(id);
	}
}
