package de.slag.common.data;

import java.util.Collection;

import org.springframework.stereotype.Service;

import de.slag.common.model.beans.XiData;

@Service
public class XiDataPersistServiceImpl extends AbstractPersistService<XiData> implements XiDataPersistService {

	@Override
	protected Class<XiData> getType() {
		return XiData.class;
	}

	@Override
	public Collection<XiData> findBy(String attribute, String value) {
		return findBy(xiData -> value.equals(xiData.getValue(attribute)));
	}
}
