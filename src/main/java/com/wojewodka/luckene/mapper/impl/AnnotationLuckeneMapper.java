package com.wojewodka.luckene.mapper.impl;

import com.wojewodka.example.ExamplePojo;
import com.wojewodka.luckene.annotation.LuckeneField;
import com.wojewodka.luckene.exception.LuckeneMappingException;
import com.wojewodka.luckene.mapper.LuckeneMapper;
import com.wojewodka.luckene.mapper.LuckeneMapperField;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@Service
@RequiredArgsConstructor
public class AnnotationLuckeneMapper implements LuckeneMapper {
	
	private Map<Class<?>, Collection<LuckeneMapperField>> buffer = new HashMap<>();
	
	@Override
	public Document serialize(Object obj) throws LuckeneMappingException {
		return serialize(obj, null);
	}
	
	private Document serialize(Object obj, String prefix) throws LuckeneMappingException {
		Document doc = new Document();
		for (LuckeneMapperField field : getLuckeneFieldsDescriptors(obj.getClass())) {
		}
		return null;
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
				LuckeneMapperField mapperField = new LuckeneMapperField();
				mapperField.setField(pair.getKey());
				mapperField.setLuckeneAnnotation(pair.getValue());
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
	
	public static void main(String[] args) throws LuckeneMappingException {
		AnnotationLuckeneMapper annotationLuckeneMapper = new AnnotationLuckeneMapper();
		annotationLuckeneMapper.serialize(new ExamplePojo());
	}
	
	@Override
	public <T> T deserialize(@NonNull Document document, @NonNull Class<T> destinationClass) throws LuckeneMappingException {
		return null;
	}
	
}
