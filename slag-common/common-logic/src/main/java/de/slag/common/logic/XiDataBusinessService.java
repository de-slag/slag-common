package de.slag.common.logic;

import de.slag.common.model.beans.XiData;

public interface XiDataBusinessService extends BusinessService<XiData> {

	void addValue(XiData xiData, String attribute, String value);

}
