package de.slag.common.logic;

import java.util.Collection;

import de.slag.common.model.beans.XiData;

public interface XiDataBusinessService extends BusinessService<XiData> {

	void addValue(XiData xiData, String attribute, String value);

	Collection<String> findAttributeValues(String attribute);

	Collection<XiData> findBy(String attribute, String value);

}
