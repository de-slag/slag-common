package de.slag.common.model;

import java.util.Date;

public class EntityBeanUtils {
	
	public static void setDelete(EntityBean bean) {
		bean.setValidUntil(new Date());
	}

}
