package de.slag.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import de.slag.common.base.BaseException;

public class TableTransformUtils {

	public static List<List<String>> removeColumns(List<List<String>> origin, Integer... columns) {
		final List<Integer> asList = Arrays.asList(columns);
		List<List<String>> preResult = origin;
		for (Integer columnIndex : asList) {
			preResult = copyExcept(preResult, null, columnIndex);
		}
		List<List<String>> removeColumnsWithoutHeader = removeColumnsWithoutHeader(preResult);
		return removeColumnsWithoutHeader;
	}

	public static List<List<String>> removeColumnsWithoutHeader(List<List<String>> origin) {
		final List<String> header = origin.get(0);
		final List<Integer> emptyHeaders = new ArrayList<>();

		for (int i = 0; i < header.size(); i++) {
			if (StringUtils.isNotEmpty(header.get(i))) {
				continue;
			}
			emptyHeaders.add(i);
		}

		List<List<String>> preResult = origin;
		int correctingValue = 0;
		for (Integer columnIdx : emptyHeaders) {
			preResult = copyExcept(preResult, null, columnIdx - correctingValue, false);
			correctingValue++;
		}
		return copyExcept(preResult, null, null);
	}

	public static List<List<String>> renameHeader(List<List<String>> origin, String headerFrom, String headerTo) {
		List<String> header = origin.get(0);
		if (header.contains(headerTo)) {
			throw new BaseException("header '" + headerTo + "' already exists");
		}
		if (!header.contains(headerFrom)) {
			throw new BaseException("header not found: '" + headerFrom + "'");
		}
		int indexOfHeaderToRename = header.indexOf(headerFrom);
		List<List<String>> returnValue = copyExcept(origin, null, null);
		returnValue.get(0).set(indexOfHeaderToRename, headerTo);
		return returnValue;
	}

	public static List<List<String>> copyExcept(List<List<String>> origin, Integer skipLine, Integer skipCol) {
		return copyExcept(origin, skipLine, skipCol, true);
	}

	public static List<List<String>> copyExcept(List<List<String>> origin, Integer skipLine, Integer skipCol,
			boolean fillWithNullValues) {
		final List<List<String>> list = new ArrayList<>();
		for (Integer line = 0; line < origin.size(); line++) {
			final ArrayList<String> newLine = new ArrayList<>();
			list.add(newLine);
			if (line.equals(skipLine)) {
				if (fillWithNullValues) {
					origin.get(line).forEach(l -> newLine.add(StringUtils.EMPTY));
				}
				continue;
			}
			for (Integer col = 0; col < origin.get(line).size(); col++) {
				if (col.equals(skipCol)) {
					if (fillWithNullValues) {
						newLine.add(StringUtils.EMPTY);
					}
					continue;
				}
				newLine.add(origin.get(line).get(col));
			}
		}
		return list;
	}

}
