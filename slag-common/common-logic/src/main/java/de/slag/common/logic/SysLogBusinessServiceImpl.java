package de.slag.common.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.slag.common.data.PersistService;
import de.slag.common.data.SysLogPersistService;
import de.slag.common.model.beans.SysLog;
import de.slag.common.model.beans.SysLog.Severity;

@Service
public class SysLogBusinessServiceImpl extends AbstractBusinessService<SysLog> implements SysLogBusinessService {

	@Resource
	private SysLogPersistService sysLogPersistService;

	@Override
	public SysLog create() {
		return new SysLog();
	}

	@Override
	protected PersistService<SysLog> getPersistService() {
		return sysLogPersistService;
	}

	@Override
	public void save(Severity severity, String text) {
		SysLog syslog = create();
		syslog.setSeverity(Severity.INFO);
		syslog.setInfo(text);
		save(syslog);
	}

}
