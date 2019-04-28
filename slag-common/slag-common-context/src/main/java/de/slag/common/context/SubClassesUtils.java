package de.slag.common.context;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import de.slag.common.base.BaseException;

public class SubClassesUtils {
	
	private static final String ROOT_PACKAGE = "de/slag";
	
	
	public static Collection<BeanDefinition> findAllDefinitionsAnnotated(Class<? extends Annotation> annotation) {
		return findAllDefinitions(new AnnotationTypeFilter(annotation));
	}

	public static Collection<Class<?>> findAllSubclassesOf(Class<?> clazz) {
		return findAllClasses(new AssignableTypeFilter(clazz));
	}

	private static Collection<Class<?>> findAllClasses(final AbstractTypeHierarchyTraversingFilter includeFilter) {
		final Set<BeanDefinition> components = findAllDefinitions(includeFilter);
		
		
		
		return components.stream().map(def -> {
			try {
				return Class.forName(def.getBeanClassName());
			} catch (ClassNotFoundException e) {
				throw new BaseException(e);
			}
		}).collect(Collectors.toList());
	}

	private static Set<BeanDefinition> findAllDefinitions(final AbstractTypeHierarchyTraversingFilter includeFilter) {
		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(includeFilter);
		return provider.findCandidateComponents(ROOT_PACKAGE);
	}

}
