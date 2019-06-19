package de.slag.common.app.context;

import de.slag.common.context.SlagContext;

public class ContextApp {
	
	public static void main(String[] args) {
		final XyService bean = SlagContext.getBean(XyService.class);
		bean.getClass();
		
	}

}
