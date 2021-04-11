package de.slag.common.logic;

import de.slag.common.model.beans.SysLog;
import de.slag.common.model.beans.SysLog.Severity;

public interface SysLogBusinessService extends BusinessService<SysLog> {

	void save(Severity severity, String text);

}
