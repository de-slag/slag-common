package de.slag.common.core;

public interface DatasourceConfig extends Comparable<DatasourceConfig> {
	
	String getIdentifier();
	
	@Override
	default int compareTo(DatasourceConfig o) {
		return getIdentifier().compareTo(o.getIdentifier());
	}

}
