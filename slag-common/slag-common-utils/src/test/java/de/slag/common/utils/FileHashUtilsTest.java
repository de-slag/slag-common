package de.slag.common.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

class FileHashUtilsTest {
	
	private String expectedMd5 = "900150983cd24fb0d6963f7d28e17f72";

	@Test
	void testMd5() {
		final File file1 = ResourceUtils.getFileFromResources("file1.txt");
		final File file2 = ResourceUtils.getFileFromResources("file2.txt");
		
		assertEquals(expectedMd5, FileHashUtils.md5(file1.getAbsolutePath()));
		assertEquals(expectedMd5, FileHashUtils.md5(file2.getAbsolutePath()));
		
	}

}
