package de.slag.basic.frontend;

import java.util.Properties;

public class BasicWebguiRunnerApp {
	
	public static void main(String[] args) {
		final Properties properties = new Properties();
		properties.put("frontend.backend.url", "http://localhost:18080/slag-basic-backend3");
		
		BasicWebguiRunner basicWebguiRunner = new BasicWebguiRunner(properties);
		basicWebguiRunner.run();
		out(basicWebguiRunner.getResult());
		
	}
	
	private static void out(Object o) {
		System.out.println(o);
	}

}
