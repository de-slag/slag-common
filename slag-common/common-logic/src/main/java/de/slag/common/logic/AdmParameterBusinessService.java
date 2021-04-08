package de.slag.common.logic;

import de.slag.common.model.beans.AdmParameter;

public interface AdmParameterBusinessService extends BusinessService<AdmParameter> {
	
	AdmParameter loadByKey(String key);

}
