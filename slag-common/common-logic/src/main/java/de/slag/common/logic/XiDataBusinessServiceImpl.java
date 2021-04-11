package de.slag.common.logic;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.slag.common.data.PersistService;
import de.slag.common.data.XiDataPersistService;
import de.slag.common.model.beans.XiData;
import de.slag.common.model.beans.XiDataValue;

@Service
public class XiDataBusinessServiceImpl extends AbstractBusinessService<XiData> implements XiDataBusinessService {

	@Resource
	private XiDataPersistService xiDataPersistService;

	@Override
	public XiData create() {
		return new XiData();
	}

	@Override
	protected PersistService<XiData> getPersistService() {
		return xiDataPersistService;
	}

	@Override
	public void addValue(XiData xiData, String attribute, String value) {
		XiDataValue xiDataValue = new XiDataValue();
		xiDataValue.setXiData(xiData);
		xiDataValue.setAttribute(attribute);
		xiDataValue.setValue(value);
		xiData.getValues().add(xiDataValue);
	}

	@Override
	public Collection<String> findAttributeValues(String attribute) {
		Collection<Long> findAllIds = xiDataPersistService.findAllIds();
		Set<String> values = new TreeSet<>();
		findAllIds.parallelStream().map(id -> loadById(id)).filter(o -> o.isPresent()).map(o -> o.get())
				.forEach(e -> values.add(e.getValue(attribute)));

		return values;
	}

	@Override
	public Collection<XiData> findBy(String attribute, String value) {
		return xiDataPersistService.findBy(attribute, value);
	}
}
