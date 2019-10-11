package de.slag.common.context;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.slag.common.base.BaseException;

public class SlagContext {

	private static final Log LOG = LogFactory.getLog(SlagContext.class);

	private static AnnotationConfigApplicationContext appCtx;

	static {
		LOG.info("init context...");
		init();
	}

	public static boolean isInitialized() {
		return appCtx != null;
	}

	public static void init() {
		synchronized (SlagContext.class) {
			if (isInitialized()) {
				LOG.error("already initalized");
				return;
			}
			LOG.info("create ApplicationContext...");

			appCtx = new AnnotationConfigApplicationContext(SlagConfig.class);
			String[] beanDefinitionNames = appCtx.getBeanDefinitionNames();
			LOG.info(String.join(", ", beanDefinitionNames));

		}
	}

	public static <T> T getBean(Class<T> c, String name) {
		Object bean = appCtx.getBean(name);
		return c.cast(Optional.of(bean).orElseThrow(() -> new BaseException("no bean with name" + name)));
	}

	public static <T> T getBean(Class<T> c) {
		return appCtx.getBeanFactory().getBean(c);
	}

	public static void shutDown() {
		appCtx.close();
	}
}
