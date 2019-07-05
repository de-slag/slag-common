package de.slag.common.model.service;

import java.util.Collection;
import java.util.function.Predicate;

import de.slag.common.model.beans.SysLog.Severity;
import de.slag.common.model.beans.SysLogEntry;

public interface SysLogService {

	void log(Severity severity, String info);

	Collection<SysLogEntry> findBy(Predicate<SysLogEntry> filter);

}
