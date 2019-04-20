package de.slag.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import de.slag.common.exception.BaseException;

public class PropertyUtils {

	private static final String EMPTY = "";

	public static Properties fromFile(String filename) {
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(filename));
		} catch (IOException e) {
			throw new BaseException(e);
		}
		final String string;
		try {
			string = new String(encoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new BaseException(e);
		}
		List<String> lines = Arrays.asList(string.split("\n"));
		final Properties p = new Properties();
		lines.forEach(line -> {
			final String[] split = line.split("=");
			if (split.length == 1) {
				p.setProperty(split[0], EMPTY);

			} else {
				p.setProperty(split[0], split[1]);
			}

		});
		return p;
	}

}
