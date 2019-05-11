package de.slag.common.utils.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slag.common.base.BaseException;
import de.slag.common.utils.TableTransformUtils;

public class TableTransformUtilsTest {

	private List<List<String>> source = source();
	
	@Test
	public void testRemoveColumns() {
		final List<List<String>> result = TableTransformUtils.removeColumns(source, 0,1);
		
				
		Assert.assertThat(result.get(0).get(0), Matchers.is("C"));				
		Assert.assertThat(result.get(1).get(0), Matchers.is("3"));				
		Assert.assertThat(result.get(2).get(0), Matchers.is("4"));				
		Assert.assertThat(result.get(3).get(0), Matchers.is("5"));
	}

	@Test
	public void testRemoveColumnsWithoutHeader() {
		final List<List<String>> renameHeader = TableTransformUtils.renameHeader(source, "B", null);
		final List<List<String>> result = TableTransformUtils
				.removeColumnsWithoutHeader(renameHeader);
		Assert.assertNotNull(result);
		Assert.assertThat(result.get(0).size(), Matchers.is(2));
		
		Assert.assertThat(result.get(0).get(0), Matchers.is("A"));		
		Assert.assertThat(result.get(0).get(1), Matchers.is("C"));

		Assert.assertThat(result.get(1).get(0), Matchers.is("1"));		
		Assert.assertThat(result.get(1).get(1), Matchers.is("3"));

		Assert.assertThat(result.get(2).get(0), Matchers.is("2"));		
		Assert.assertThat(result.get(2).get(1), Matchers.is("4"));

		Assert.assertThat(result.get(3).get(0), Matchers.is("3"));		
		Assert.assertThat(result.get(3).get(1), Matchers.is("5"));
		
	
	}
	
	@Test
	public void testRenameHeader_ToNull() {
		final List<List<String>> result = TableTransformUtils.renameHeader(source, "A", null);
		Assert.assertNotNull(result);
		Assert.assertNull(result.get(0).get(0));
	}

	@Test
	public void testRenameHeader() {
		final List<List<String>> result = TableTransformUtils.renameHeader(source, "A", "X");
		Assert.assertNotNull(result);
		Assert.assertThat(result.get(0).get(0), Matchers.is("X"));
	}

	@Test(expected = BaseException.class)
	public void testRenameHeader_HeaderNotFound() {
		TableTransformUtils.renameHeader(source, null, "X");
	}

	@Test(expected = BaseException.class)
	public void testRenameHeader_HeaderExists() {
		TableTransformUtils.renameHeader(source, null, "A");
	}

	@Test
	public void testCopyExcept_ColLine() {
		List<List<String>> copyExcept = TableTransformUtils.copyExcept(source, 2, 1);

		Assert.assertThat(copyExcept.get(0).get(0), Matchers.is("A"));
		Assert.assertThat(copyExcept.get(0).get(1), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(0).get(2), Matchers.is("C"));

		Assert.assertThat(copyExcept.get(1).get(0), Matchers.is("1"));
		Assert.assertThat(copyExcept.get(1).get(1), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(1).get(2), Matchers.is("3"));

		Assert.assertThat(copyExcept.get(2).get(0), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(2).get(1), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(2).get(2), Matchers.is(StringUtils.EMPTY));

		Assert.assertThat(copyExcept.get(3).get(0), Matchers.is("3"));
		Assert.assertThat(copyExcept.get(3).get(1), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(3).get(2), Matchers.is("5"));
	}

	@Test
	public void testCopyExcept_Col() {
		List<List<String>> copyExcept = TableTransformUtils.copyExcept(source, null, 1);

		Assert.assertThat(copyExcept.get(0).get(0), Matchers.is("A"));
		Assert.assertThat(copyExcept.get(0).get(1), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(0).get(2), Matchers.is("C"));

		Assert.assertThat(copyExcept.get(1).get(0), Matchers.is("1"));
		Assert.assertThat(copyExcept.get(1).get(1), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(1).get(2), Matchers.is("3"));

		Assert.assertThat(copyExcept.get(2).get(0), Matchers.is("2"));
		Assert.assertThat(copyExcept.get(2).get(1), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(2).get(2), Matchers.is("4"));

		Assert.assertThat(copyExcept.get(3).get(0), Matchers.is("3"));
		Assert.assertThat(copyExcept.get(3).get(1), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(3).get(2), Matchers.is("5"));
	}

	@Test
	public void testCopyExcept_Line() {
		List<List<String>> copyExcept = TableTransformUtils.copyExcept(source, 2, null);

		Assert.assertThat(copyExcept.get(0).get(0), Matchers.is("A"));
		Assert.assertThat(copyExcept.get(0).get(1), Matchers.is("B"));
		Assert.assertThat(copyExcept.get(0).get(2), Matchers.is("C"));

		Assert.assertThat(copyExcept.get(1).get(0), Matchers.is("1"));
		Assert.assertThat(copyExcept.get(1).get(1), Matchers.is("2"));
		Assert.assertThat(copyExcept.get(1).get(2), Matchers.is("3"));

		Assert.assertThat(copyExcept.get(2).get(0), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(2).get(1), Matchers.is(StringUtils.EMPTY));
		Assert.assertThat(copyExcept.get(2).get(2), Matchers.is(StringUtils.EMPTY));

		Assert.assertThat(copyExcept.get(3).get(0), Matchers.is("3"));
		Assert.assertThat(copyExcept.get(3).get(1), Matchers.is("4"));
		Assert.assertThat(copyExcept.get(3).get(2), Matchers.is("5"));
	}

	@Test
	public void testCopyExcept_NoSkips() {
		final List<List<String>> source = source();
		List<List<String>> copyExcept = TableTransformUtils.copyExcept(source, null, null);

		Assert.assertThat(copyExcept.get(0).get(0), Matchers.is("A"));
		Assert.assertThat(copyExcept.get(0).get(1), Matchers.is("B"));
		Assert.assertThat(copyExcept.get(0).get(2), Matchers.is("C"));

		Assert.assertThat(copyExcept.get(1).get(0), Matchers.is("1"));
		Assert.assertThat(copyExcept.get(1).get(1), Matchers.is("2"));
		Assert.assertThat(copyExcept.get(1).get(2), Matchers.is("3"));

		Assert.assertThat(copyExcept.get(2).get(0), Matchers.is("2"));
		Assert.assertThat(copyExcept.get(2).get(1), Matchers.is("3"));
		Assert.assertThat(copyExcept.get(2).get(2), Matchers.is("4"));

		Assert.assertThat(copyExcept.get(3).get(0), Matchers.is("3"));
		Assert.assertThat(copyExcept.get(3).get(1), Matchers.is("4"));
		Assert.assertThat(copyExcept.get(3).get(2), Matchers.is("5"));

	}

	@Before
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
