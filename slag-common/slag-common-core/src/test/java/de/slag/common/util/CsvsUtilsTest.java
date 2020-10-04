package de.slag.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slag.common.util.CsvsUtils;
import de.slag.common.util.ResourceUtils;

public class CsvsUtilsTest {

	private static final String BASE_RESOURCE_DIR = "csv";

	private File baseDir;

	@Before
	public void setUp() throws Exception {
		baseDir = ResourceUtils.getFileFromResources(BASE_RESOURCE_DIR);
	}

	@Test
	public void test() {
		final Map<String, Collection<CSVRecord>> csvs = CsvsUtils.get(baseDir);
		Assert.assertNotNull(csvs);
		Assert.assertThat(csvs.size(), Matchers.is(1));
		csvs.forEach((s, c) -> Assert.assertTrue(!c.isEmpty()));

		final Set<String> keySet = csvs.keySet();
		final ArrayList<String> arrayList = new ArrayList<>();
		arrayList.addAll(keySet);

		final String string = arrayList.get(0);
		final Collection<CSVRecord> collection = csvs.get(string);
		Assert.assertThat(collection.size(), Matchers.is(3));

	}

}
