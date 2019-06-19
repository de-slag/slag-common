package de.slag.common.app.context;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class XyServiceImpl implements XyService {

	@Resource
	private AbService abService;

}
