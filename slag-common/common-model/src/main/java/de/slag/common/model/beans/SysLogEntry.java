package de.slag.common.model.beans;

import java.util.Date;

import de.slag.common.model.beans.SysLog.Severity;

public interface SysLogEntry {
	
	Severity getSeverity();
	
	String getInfo();
	
	Date getCreatedAt();
	
	

}
