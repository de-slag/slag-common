package de.slag.common.data;

import org.springframework.stereotype.Service;

import de.slag.common.model.beans.SysLog;

@Service
public class SysLogPersistServiceImpl extends AbstractPersistService<SysLog> implements SysLogPersistService {

	@Override
	protected Class<SysLog> getType() {
		return SysLog.class;
	}

}
