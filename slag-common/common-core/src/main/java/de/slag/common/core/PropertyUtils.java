package de.slag.common.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import de.slag.common.base.BaseException;

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

	public static Properties from(String propertiesAsString) {
		final InputStream inputStream = IOUtils.toInputStream(propertiesAsString, StandardCharsets.UTF_8);
		final Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new BaseException(e);
		}
		return properties;
	}

	public static String from(Properties properties) {
		final List<String> entrys = new ArrayList<String>();
		final Set<Object> keySet = properties.keySet();
		keySet.stream()
			.map(key -> (String) key)
			.forEach(key -> entrys.add(key + "=" + properties.getProperty(key)));
		
		return String.join("\n", entrys);

	}

}
