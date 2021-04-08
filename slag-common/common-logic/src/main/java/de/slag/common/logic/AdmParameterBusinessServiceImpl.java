package de.slag.common.logic;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.slag.common.data.AdmParameterPersistService;
import de.slag.common.data.PersistService;
import de.slag.common.model.beans.AdmParameter;

@Service
public class AdmParameterBusinessServiceImpl extends AbstractBusinessService<AdmParameter>
		implements AdmParameterBusinessService {

	@Resource
	private AdmParameterPersistService admParameterPersistService;

	@Override
	public AdmParameter create() {
		return new AdmParameter();
	}

	@Override
	protected PersistService<AdmParameter> getPersistService() {
		return admParameterPersistService;
	}

	@Override
	public AdmParameter loadByKey(String key) {
		final Optional<AdmParameter> parameterOptional = admParameterPersistService
				.loadBy(admParameter -> key.equals(admParameter.getConfigKey()));

		return parameterOptional.orElseThrow(() -> new RuntimeException("parameter not found: " + key));
	}

}
