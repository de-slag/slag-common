package de.slag.common.data;

import org.springframework.stereotype.Service;

import de.slag.common.model.beans.AdmParameter;

@Service
public class AdmParameterPersistServiceImpl extends AbstractPersistService<AdmParameter>
		implements AdmParameterPersistService {

	@Override
	protected Class<AdmParameter> getType() {
		return AdmParameter.class;
	}

}
