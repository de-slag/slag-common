package de.slag.common.data;

import org.springframework.stereotype.Service;

import de.slag.common.model.beans.XiData;

@Service
public class XiDataPersistServiceImpl extends AbstractPersistService<XiData> implements XiDataPersistService {

	@Override
	protected Class<XiData> getType() {
		return XiData.class;
	}

}
