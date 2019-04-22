package com.wojewodka.luckene.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionCommons {
	
	/**
	 * Return {@link Collection} of <i>declared</i> fields annotated by parameter {@link Annotation}.  <br/>
	 * Note that result collection has set accessible set on {@code true} all {@link Field}s.
	 */
	public static <T extends Annotation> Collection<Pair<Field, T>> getAnnotatedFields(Class<?> clazz, Class<T> annotation) {
		return stream(clazz.getDeclaredFields())
			.filter(field -> field.isAnnotationPresent(annotation))
			.peek(field -> field.setAccessible(true))
			.map(field -> Pair.of(field, field.getDeclaredAnnotation(annotation)))
			.collect(Collectors.toList());
	}
	
}
