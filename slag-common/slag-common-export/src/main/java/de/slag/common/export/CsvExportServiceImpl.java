package de.slag.common.export;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.common.api.CsvExportService;
import de.slag.common.api.ReflectionService;
import de.slag.common.utils.CsvUtils;

public class CsvExportServiceImpl implements CsvExportService {

	private static final Log LOG = LogFactory.getLog(CsvExportServiceImpl.class);

	@Resource
	private ReflectionService reflectionService;

	@Override
	public void export(File toFile, List<String> attributes, Collection<?> datas) {
		final List<List<String>> result = new ArrayList<>();

		result.add(new ArrayList<String>(attributes));

		List<Supplier<String>> messages = new ArrayList<>();
		Consumer<Supplier<String>> messageConsumer = message -> {
			messages.add(message);
		};

		datas.forEach(data -> result.add(export(attributes, data, messageConsumer)));

		StringBuilder sb = new StringBuilder();
		messages.forEach(message -> sb.append("\n" + message.get()));

		LOG.info("export: " + sb.toString());

		CsvUtils.write(result, toFile.toPath());
	}

	private List<String> export(List<String> attributes, Object data, Consumer<Supplier<String>> messageConsumer) {
		return attributes.stream()
				.map(att -> {
					final Object value = reflectionService.getValue(data, att);
					messageConsumer.accept(() -> String.format("export: %s.%s: '%s'", data, att, value));
					return value;

				})
				.map(val -> val == null ? StringUtils.EMPTY : val.toString())
				.collect(Collectors.toList());
	}

}
