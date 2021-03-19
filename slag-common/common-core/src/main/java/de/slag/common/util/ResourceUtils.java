package de.slag.common.util;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public final class ResourceUtils {

	private ResourceUtils() {

	}

	public static File getFileFromResources(String filename) {
		final URL resource = new ResourceUtils().getClass().getClassLoader().getResource(filename);
		return new File(Objects.requireNonNull(resource, "Resource not found: " +  filename).getFile());
	}

}
