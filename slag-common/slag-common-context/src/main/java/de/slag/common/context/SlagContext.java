package de.slag.common.context;

import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

public class SlagContext {

	private static final Log LOG = LogFactory.getLog(SlagContext.class);
	
	private static SlagContext ctx;

	private Properties contextProperties = new Properties();

	private AnnotationConfigApplicationContext appCtx;

	public static void init() {
		synchronized (SlagContext.class) {
			final boolean alreadyInitialized = ctx != null;
			if (alreadyInitialized) {
				throw new SlagContextException("already initalized");
			}

			LOG.info("create ApplicationContext...");
			ctx = new SlagContext();			
			ctx.setContext(new AnnotationConfigApplicationContext());
			
			LOG.info("init annotated beans...");
			ctx.initAnnotatedBeans();
			LOG.info("init annotated beans...done.");

		}
	}

	private void setContext(AnnotationConfigApplicationContext appCtx) {
		this.appCtx = appCtx;
	}

	private void initAnnotatedBeans() {
		final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
				true);

		scanner.addIncludeFilter(new AnnotationTypeFilter(Service.class));
		final Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents("de.slag");
		for (BeanDefinition beanDefinition : candidateComponents) {
			final String beanClassName = beanDefinition.getBeanClassName();
			LOG.info("register: " + beanClassName);
			appCtx.registerBeanDefinition(beanClassName, beanDefinition);
		}
	}

	public static ConfigurableApplicationContext getContext() {
		return ctx.appCtx;
	}

	public static <T> T getBean(Class<T> c) {
		return getContext().getBeanFactory().getBean(c);
	}
	
	private Properties getContextProperties() {
		return contextProperties;
	}
	
	public static String getContextProperty(String key) {
		return ctx.getContextProperties().getProperty(key);
	}
}
