package de.slag.common.utils;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

class FileHashUtilsTest {

	private String expectedMd5 = "900150983cd24fb0d6963f7d28e17f72";
	private String expectedSha256 = "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad";
	private File file1;
	private File file2;

	@Before
	void setUp() {
		this.file1 = ResourceUtils.getFileFromResources("file1.txt");
		this.file2 = ResourceUtils.getFileFromResources("file2.txt");
	}

	@Test
	void testMd5() {
		Assert.assertEquals(expectedMd5, FileHashUtils.md5(file1.getAbsolutePath()));
		Assert.assertEquals(expectedMd5, FileHashUtils.md5(file2.getAbsolutePath()));
	}

	@Test
	void testSha256() {

		Assert.assertEquals(expectedSha256, FileHashUtils.sha256(file1.getAbsolutePath()));
		Assert.assertEquals(expectedSha256, FileHashUtils.sha256(file2.getAbsolutePath()));
	}

}
