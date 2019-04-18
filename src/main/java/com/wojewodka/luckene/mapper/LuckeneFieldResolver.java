package com.wojewodka.luckene.mapper;

import com.wojewodka.luckene.exception.LuckeneMappingException;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;

import java.lang.reflect.Field;
import java.util.function.Predicate;

public interface LuckeneFieldResolver {
	
	void serialize(Document document, String fieldName, Object value, Field field) throws LuckeneMappingException;
	
	void deserialize(IndexableField docField, Field field, Object destinationObject) throws LuckeneMappingException;
	
	Predicate<Object> forType();
	
}
