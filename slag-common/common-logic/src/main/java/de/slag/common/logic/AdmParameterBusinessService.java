package de.slag.common.logic;

import java.util.Optional;

import de.slag.common.model.beans.AdmParameter;

public interface AdmParameterBusinessService extends BusinessService<AdmParameter> {
	
	Optional<AdmParameter> loadByKey(String key);

}
