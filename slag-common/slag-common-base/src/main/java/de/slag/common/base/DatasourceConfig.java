package de.slag.common.base;

public interface DatasourceConfig extends Comparable<DatasourceConfig> {
	
	String getIdentifier();
	
	@Override
	default int compareTo(DatasourceConfig o) {
		return getIdentifier().compareTo(o.getIdentifier());
	}

}
