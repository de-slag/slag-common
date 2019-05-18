package de.slag.common.utils;

import java.io.File;

public final class ResourceUtils {

	private ResourceUtils() {

	}

	public static File getFileFromResources(String filename) {
		return new File(new ResourceUtils().getClass().getClassLoader().getResource(filename).getFile());
	}

}
