package de.slag.common.export;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.slag.common.api.ReflectionService;

@RunWith(MockitoJUnitRunner.class)
public class CsvExportServiceImplTest {

	@Mock
	ReflectionService reflectionService;

	@InjectMocks
	CsvExportServiceImpl csvExportServiceImpl;

	File tmpFile;

	Pojo pojo1;

	@Before
	public void setup() throws IOException {
		tmpFile = Files.createTempFile("CsvExportServiceImplTest", ".csv")
				.toFile();
		pojo1 = new Pojo(1);
		pojo1.setSvar("teSt");

		when(reflectionService.getValue(pojo1, "svar")).thenReturn("teSt");
		when(reflectionService.getValue(pojo1, "id")).thenReturn(1l);

	}

	@Test
	public void testExport() {
		csvExportServiceImpl.export(tmpFile, Arrays.asList("id", "svar"), Arrays.asList(pojo1));
		Assert.assertTrue(tmpFile.length() > 0);

	}

	private class Pojo {

		private final Long id;

		private String svar;

		public Pojo(long id) {
			this.id = id;
		}

		public String getSvar() {
			return svar;
		}

		public void setSvar(String svar) {
			this.svar = svar;
		}
	}

}
