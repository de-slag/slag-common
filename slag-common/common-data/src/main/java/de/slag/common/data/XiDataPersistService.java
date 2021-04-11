package de.slag.common.data;

import java.util.Collection;

import de.slag.common.model.beans.XiData;

public interface XiDataPersistService extends PersistService<XiData> {

	Collection<XiData> findBy(String attribute, String value);

}
