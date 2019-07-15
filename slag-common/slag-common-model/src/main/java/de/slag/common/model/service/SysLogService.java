package de.slag.common.model.service;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.slag.common.Dao;
import de.slag.common.model.beans.SysLog;
import de.slag.common.model.beans.SysLog.Severity;
import de.slag.common.model.beans.SysLogEntry;

public interface SysLogService {

	default void log(Severity severity, String info) {
		log(null, severity, info);
	}

	default void log(Class<?> clazz, Severity severity, String info) {		
		final SysLog sysLog = new SysLog();
		sysLog.setSeverity(severity);
		sysLog.setInfo(clazz == null ? info : clazz.getSimpleName() + " " + info);
		getDao().save(sysLog);
	}

	default Collection<SysLogEntry> findBy(Predicate<SysLogEntry> filter) {
		return getDao().findAll().stream().filter(filter).collect(Collectors.toList());
	}

	default void info(Class<?> clazz, String info) {
		log(clazz, Severity.INFO, info);
	}

	default void error(Class<?> clazz, String info) {
		log(Severity.ERROR, info);
	}

	default void warn(Class<?> clazz, String info) {
		log(Severity.WARN, info);
	}

	Dao<SysLog> getDao();
}
