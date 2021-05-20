package de.slag.common.backendfortesting;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import de.slag.basic.backend.simple.SimpleBasicBackendService;
import de.slag.basic.model.EntityDto;
import de.slag.basic.model.Token;
import de.slag.common.backendfortesting.model.Animal;
import de.slag.common.backendfortesting.model.PersistType;

@Service
public class BftSimpleBasicBackendServiceImpl implements SimpleBasicBackendService {

	private Map<PersistType, Map<Long, Object>> repository = new HashMap<>();

	@Override
	public Token getLogin(String identifier, String passcode) {
		final long currentTimeMillis = System.currentTimeMillis();
		return Token.of(String.valueOf(currentTimeMillis));
	}

	@Override
	public EntityDto create(String type) {
		PersistType persistType = PersistType.valueOf(type);
		final EntityDto entityDto = new EntityDto();
		entityDto.setType(persistType.name());
		return entityDto;
	}

	@Override
	public EntityDto load(String type, Long id) {
		PersistType persistType = PersistType.valueOf(type);
		switch (persistType) {
		case ANIMAL:
			return toDto(repository.get(PersistType.ANIMAL).get(id));
		default:
			throw new IllegalStateException("not supported type: " + type);
		}
	}

	@Override
	public EntityDto save(EntityDto dto) {
		PersistType persistType = PersistType.valueOf(dto.getType());
		switch (persistType) {
		case ANIMAL:
			Map<Long, Object> animals = repository.get(PersistType.ANIMAL);
			animals.put(dto.getId(), toAnimal(dto));
			return toDto(animals.get(dto.getId()));
		default:
			throw new IllegalStateException("not supported type: " + dto.getType());
		}
	}

	@Override
	public EntityDto delete(String type, Long id) {
		PersistType persistType = PersistType.valueOf(type);
		Map<Long, Object> map = repository.get(persistType);
		if (!map.containsKey(id)) {
			return null;
		}
		return toDto(map.remove(id));
	}

	@Override
	public void validateLogin(Token token) {
		// in this case always good
	}

	private EntityDto toDto(Object o) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	private Animal toAnimal(EntityDto dto) {
		throw new UnsupportedOperationException("not implemented yet");
	}
}
