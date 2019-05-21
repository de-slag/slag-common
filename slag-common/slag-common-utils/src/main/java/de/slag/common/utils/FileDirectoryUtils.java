package de.slag.common.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import de.slag.common.base.BaseException;

public class FileDirectoryUtils {

	public static Collection<File> allFilesOf(String absoluteDirectoryName) {
		final File file = new File(absoluteDirectoryName);
		if (!file.exists() || !file.isDirectory()) {
			throw new BaseException("not a valid directory: " + absoluteDirectoryName);
		}

		final List<File> asList = Arrays.asList(file.listFiles());
		return asList.stream()
				.filter(f -> !f.isDirectory())
				.collect(Collectors.toList());
	}
}
