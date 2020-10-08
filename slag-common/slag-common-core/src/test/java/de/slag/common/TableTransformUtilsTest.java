package de.slag.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.slag.common.base.BaseException;
import de.slag.common.util.TableTransformUtils;

public class TableTransformUtilsTest {

	private List<List<String>> source = source();

	@Test
	public void testRemoveColumns() {
		final List<List<String>> result = TableTransformUtils.removeColumns(source, 0, 1);

		assertThat(result.get(0).get(0), Matchers.is("C"));
		assertThat(result.get(1).get(0), Matchers.is("3"));
		assertThat(result.get(2).get(0), Matchers.is("4"));
		assertThat(result.get(3).get(0), Matchers.is("5"));
	}

	@Test
	public void testRemoveColumnsWithoutHeader() {
		final List<List<String>> renameHeader = TableTransformUtils.renameHeader(source, "B", null);
		final List<List<String>> result = TableTransformUtils.removeColumnsWithoutHeader(renameHeader);
		assertNotNull(result);
		assertThat(result.get(0).size(), Matchers.is(2));

		assertThat(result.get(0).get(0), Matchers.is("A"));
		assertThat(result.get(0).get(1), Matchers.is("C"));

		assertThat(result.get(1).get(0), Matchers.is("1"));
		assertThat(result.get(1).get(1), Matchers.is("3"));

		assertThat(result.get(2).get(0), Matchers.is("2"));
		assertThat(result.get(2).get(1), Matchers.is("4"));

		assertThat(result.get(3).get(0), Matchers.is("3"));
		assertThat(result.get(3).get(1), Matchers.is("5"));

	}

	@Test
	public void testRenameHeader_ToNull() {
		final List<List<String>> result = TableTransformUtils.renameHeader(source, "A", null);
		assertNotNull(result);
		assertNull(result.get(0).get(0));
	}

	@Test
	public void testRenameHeader() {
		final List<List<String>> result = TableTransformUtils.renameHeader(source, "A", "X");
		assertNotNull(result);
		assertThat(result.get(0).get(0), Matchers.is("X"));
	}

	@Test
	public void testRenameHeader_HeaderNotFound() {
		assertThrows(BaseException.class, () -> TableTransformUtils.renameHeader(source, null, "X"));
	}

	@Test
	public void testRenameHeader_HeaderExists() {
		assertThrows(BaseException.class, ()->TableTransformUtils.renameHeader(source, null, "A"));
	}

	@Test
	public void testCopyExcept_ColLine() {
		List<List<String>> copyExcept = TableTransformUtils.copyExcept(source, 2, 1);

		assertThat(copyExcept.get(0).get(0), Matchers.is("A"));
		assertThat(copyExcept.get(0).get(1), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(0).get(2), Matchers.is("C"));

		assertThat(copyExcept.get(1).get(0), Matchers.is("1"));
		assertThat(copyExcept.get(1).get(1), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(1).get(2), Matchers.is("3"));

		assertThat(copyExcept.get(2).get(0), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(2).get(1), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(2).get(2), Matchers.is(StringUtils.EMPTY));

		assertThat(copyExcept.get(3).get(0), Matchers.is("3"));
		assertThat(copyExcept.get(3).get(1), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(3).get(2), Matchers.is("5"));
	}

	@Test
	public void testCopyExcept_Col() {
		List<List<String>> copyExcept = TableTransformUtils.copyExcept(source, null, 1);

		assertThat(copyExcept.get(0).get(0), Matchers.is("A"));
		assertThat(copyExcept.get(0).get(1), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(0).get(2), Matchers.is("C"));

		assertThat(copyExcept.get(1).get(0), Matchers.is("1"));
		assertThat(copyExcept.get(1).get(1), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(1).get(2), Matchers.is("3"));

		assertThat(copyExcept.get(2).get(0), Matchers.is("2"));
		assertThat(copyExcept.get(2).get(1), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(2).get(2), Matchers.is("4"));

		assertThat(copyExcept.get(3).get(0), Matchers.is("3"));
		assertThat(copyExcept.get(3).get(1), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(3).get(2), Matchers.is("5"));
	}

	@Test
	public void testCopyExcept_Line() {
		List<List<String>> copyExcept = TableTransformUtils.copyExcept(source, 2, null);

		assertThat(copyExcept.get(0).get(0), Matchers.is("A"));
		assertThat(copyExcept.get(0).get(1), Matchers.is("B"));
		assertThat(copyExcept.get(0).get(2), Matchers.is("C"));

		assertThat(copyExcept.get(1).get(0), Matchers.is("1"));
		assertThat(copyExcept.get(1).get(1), Matchers.is("2"));
		assertThat(copyExcept.get(1).get(2), Matchers.is("3"));

		assertThat(copyExcept.get(2).get(0), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(2).get(1), Matchers.is(StringUtils.EMPTY));
		assertThat(copyExcept.get(2).get(2), Matchers.is(StringUtils.EMPTY));

		assertThat(copyExcept.get(3).get(0), Matchers.is("3"));
		assertThat(copyExcept.get(3).get(1), Matchers.is("4"));
		assertThat(copyExcept.get(3).get(2), Matchers.is("5"));
	}

	@Test
	public void testCopyExcept_NoSkips() {
		final List<List<String>> source = source();
		List<List<String>> copyExcept = TableTransformUtils.copyExcept(source, null, null);

		assertThat(copyExcept.get(0).get(0), Matchers.is("A"));
		assertThat(copyExcept.get(0).get(1), Matchers.is("B"));
		assertThat(copyExcept.get(0).get(2), Matchers.is("C"));

		assertThat(copyExcept.get(1).get(0), Matchers.is("1"));
		assertThat(copyExcept.get(1).get(1), Matchers.is("2"));
		assertThat(copyExcept.get(1).get(2), Matchers.is("3"));

		assertThat(copyExcept.get(2).get(0), Matchers.is("2"));
		assertThat(copyExcept.get(2).get(1), Matchers.is("3"));
		assertThat(copyExcept.get(2).get(2), Matchers.is("4"));

		assertThat(copyExcept.get(3).get(0), Matchers.is("3"));
		assertThat(copyExcept.get(3).get(1), Matchers.is("4"));
		assertThat(copyExcept.get(3).get(2), Matchers.is("5"));

	}

	@BeforeEach
	public void setup() {
		source = source();
	}

	private List<List<String>> source() {
		final List<List<String>> source = new ArrayList<>();
		source.add(Arrays.asList("A", "B", "C"));

		for (int i = 1; i < 4; i++) {
			final ArrayList<String> line = new ArrayList<>();
			source.add(line);
			for (int j = 0; j < 3; j++) {
				line.add(String.valueOf(i + j));
			}
		}
		return source;
	}

}
