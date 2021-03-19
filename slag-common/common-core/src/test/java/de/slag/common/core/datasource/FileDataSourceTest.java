package de.slag.common.core.datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileDataSourceTest {

	private String readonlyFolderName;

	private String readWriteFolderName;
	
	Map<String, String> valueMap = new HashMap<>();

	@BeforeEach
	public void init() {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource("datasource");
		readonlyFolderName = resource.getFile();

		readWriteFolderName = System.getProperty("java.io.tmpdir") + "/" + this.getClass().getSimpleName() + "_"
				+ System.currentTimeMillis();
		new File(readWriteFolderName).mkdir();

		valueMap.put("ID", "1");
		valueMap.put("TYPE", "Device");
	}
	
	@AfterEach
	public void teardown() {
		new File(readWriteFolderName).delete();
	}

	@Test
	public void createEmptyFileDataSourceTest() {
		OldFileDataSource fileDataSource = new OldFileDataSource(readWriteFolderName);
		assertEquals(Long.valueOf(0L), fileDataSource.getMaxId());
	}

	@Test
	public void createFileDataSourceTest() {
		OldFileDataSource fileDataSource = new OldFileDataSource(readonlyFolderName);
		assertEquals(Long.valueOf(4L), fileDataSource.getMaxId());
	}

	@Test
	public void getFileNameTest() {
		Map<String, String> valueMap = new HashMap<>();
		valueMap.put("TYPE", "Device");
		String fileName = new OldFileDataSource(readWriteFolderName).getFileName(valueMap);
		assertEquals(readWriteFolderName + "/Device.csv", fileName);
	}
	
	@Test
	public void getFileTest() {
		File file = new OldFileDataSource(readWriteFolderName).getFile(valueMap);
		assertNotNull(file);
		assertTrue(file.exists());
	}
	
	@Test
	public void test() {
		new OldFileDataSource(readWriteFolderName).create(valueMap);
	}

}
