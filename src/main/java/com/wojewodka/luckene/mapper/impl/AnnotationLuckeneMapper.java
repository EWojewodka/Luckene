package com.wojewodka.luckene.mapper.impl;

import com.wojewodka.example.ExamplePojo;
import com.wojewodka.luckene.annotation.LuckeneField;
import com.wojewodka.luckene.exception.LuckeneMappingException;
import com.wojewodka.luckene.mapper.LuckeneFieldResolver;
import com.wojewodka.luckene.mapper.LuckeneMapper;
import com.wojewodka.luckene.mapper.LuckeneMapperField;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.lucene.document.Document;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static com.wojewodka.luckene.commons.ReflectionCommons.getAnnotatedFields;
import static java.util.stream.Collectors.toList;

/**
 * {@link LuckeneMapper} which allow to configure mapping by annotations as a {@link LuckeneField} <br/>
 *
 * @see #serialize(Object)
 * @see #deserialize(Document, Class)
 * @see LuckeneField
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class AnnotationLuckeneMapper implements LuckeneMapper {

	private final String CANNOT_FIND_RESOLVER = "Cannot find any resolver for [{}] LuckenMapperField.";

	private Map<Class<?>, Collection<LuckeneMapperField>> buffer = new HashMap<>();
	private final Collection<LuckeneFieldResolver> fieldResolvers;

	@Override
	public Document serialize(Object obj) throws LuckeneMappingException {
		return serialize(obj, null);
	}

	private Document serialize(Object obj, String prefix) throws LuckeneMappingException {
		Document doc = new Document();
		for (LuckeneMapperField mapperField : getLuckeneFieldsDescriptors(obj.getClass())) {
			LuckeneFieldResolver resolver = mapperField.getResolver();
			if (resolver == null) {
				log.warn(CANNOT_FIND_RESOLVER, mapperField);
				continue;
			}

			resolver.serialize(doc, mapperField.getName(), obj, mapperField.getField());
		}
		return doc;
	}

	private Collection<LuckeneMapperField> getLuckeneFieldsDescriptors(Class<?> clazz) {
		// try to get from cache
		Collection<LuckeneMapperField> cacheResult = buffer.get(clazz);
		if (cacheResult != null) {
			return cacheResult;
		}

		Collection<LuckeneMapperField> result = getAnnotatedFields(clazz, LuckeneField.class).stream()
			.map(pair -> {
				// create LuckeneMapperField
				LuckeneMapperField mapperField = new LuckeneMapperField(pair.getKey(), pair.getValue());
				mapperField.setResolver(findResolver(mapperField));
				return mapperField;
			}).collect(Collectors.toSet());

		// iterate over superclasses while is not Object
		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null && superClass.equals(Object.class)) {
			result.addAll(getLuckeneFieldsDescriptors(superClass));
		}
		buffer.put(clazz, result);
		return result;
	}

	private LuckeneFieldResolver findResolver(LuckeneMapperField mapperField) {
		LuckeneFieldResolver result = fieldResolvers.stream()
			.filter(resolver -> resolver.forType(mapperField))
			.findAny()
			.orElse(null);

		if (result == null) log.warn(CANNOT_FIND_RESOLVER, mapperField);

		return result;
	}

	@Override
	public <T> T deserialize(@NonNull Document document, @NonNull Class<T> destinationClass) throws LuckeneMappingException {
		return null;
	}

}
