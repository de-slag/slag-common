package de.slag.common.logic;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.slag.common.data.PersistService;
import de.slag.common.data.XiDataPersistService;
import de.slag.common.model.beans.XiData;
import de.slag.common.model.beans.XiDataValue;

@Service
public class XiBusinessServiceImpl extends AbstractBusinessService<XiData> implements XiBusinessService {

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

	public void map(Object o) {
		
	}
}
