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
	public Optional<AdmParameter> loadByKey(String key) {
		return admParameterPersistService.loadBy(admParameter -> key.equals(admParameter.getConfigKey()));
	}

}
