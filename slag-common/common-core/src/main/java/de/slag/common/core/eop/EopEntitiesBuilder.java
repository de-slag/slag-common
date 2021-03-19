package de.slag.common.core.eop;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.slag.common.base.BaseException;

public class EopEntitiesBuilder implements Builder<Collection<EopEntity>> {

	private static final Log LOG = LogFactory.getLog(EopEntitiesBuilder.class);

	private static final Predicate<Object> IS_STRING = o -> o instanceof String;

	private static final Function<Object, String> TO_STRING = o -> (String) o;

	private static final String DOT = "\\.";

	private static final Function<Stream<Object>, Stream<String>> TO_STRING_STREAM = objectStream -> objectStream
			.filter(IS_STRING).map(TO_STRING);

	public static final Function<Properties, Collection<EopType>> TYPES_OF = properties -> {
		Set<String> collect = TO_STRING_STREAM.apply(properties.keySet().stream()).peek(key -> LOG.debug(key))
				.map(key -> key.split(DOT)[0]).collect(Collectors.toSet());
		return collect.stream().map(typeName -> new EopType(typeName)).collect(Collectors.toList());
	};

	public static final BiConsumer<Properties, EopEntity> FILL_ATTRIBUTES = (properties, eopEntity) -> {
		final String keyPrefix = String.format("%s.%s.", eopEntity.getType().getName(), eopEntity.getId());

		final List<String> entityKeys = TO_STRING_STREAM.apply(properties.keySet().stream())
				.filter(key -> key.startsWith(keyPrefix)).collect(Collectors.toList());

		entityKeys.forEach(key -> {
			if (keyPrefix.length() > key.length()) {
				throw new BaseException(String.format("%s>%s", keyPrefix, key));
			}
			final String subKey = key.substring(keyPrefix.length());
			final String attributeValue = properties.getProperty(key);
			eopEntity.put(subKey, attributeValue);
		});

	};

	public static final BiFunction<Properties, EopType, Collection<EopEntity>> ENTITIES_OF = (properties, eopType) -> {
		final String keyPrefix = String.format("%s.", eopType.getName());

		final Collection<String> typeKeys = TO_STRING_STREAM.apply(properties.keySet().stream())
				.filter(key -> key.startsWith(keyPrefix)).collect(Collectors.toList());

		final Collection<Long> typeIds = typeKeys.stream().map(typeKey -> typeKey.split(DOT)[1])
				.map(id -> Long.valueOf(id)).collect(Collectors.toSet());

		return typeIds.stream().map(id -> new EopEntity(eopType, id)).collect(Collectors.toList());
	};

	private Properties properties;

	public EopEntitiesBuilder withProperties(Properties properties) {
		this.properties = properties;
		return this;
	}

	@Override
	public Collection<EopEntity> build() {
		if (properties == null) {
			throw new BaseException("properties not setted");
		}
		final Collection<EopType> types = TYPES_OF.apply(properties);
		final List<EopEntity> entities = types.stream().flatMap(type -> ENTITIES_OF.apply(properties, type).stream())
				.collect(Collectors.toList());
		entities.forEach(entity -> FILL_ATTRIBUTES.accept(properties, entity));
		return entities;
	}

}
