package com.wojewodka.luckene.mapper.resolvers;

import com.wojewodka.luckene.exception.LuckeneMappingException;
import com.wojewodka.luckene.mapper.LuckeneFieldResolver;
import com.wojewodka.luckene.mapper.LuckeneMapperField;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class LuckeneStringFieldResolver implements LuckeneFieldResolver {

	@Override
	public void serialize(Document document, String fieldName, Object value, Field field) throws LuckeneMappingException {
	}

	@Override
	public void deserialize(IndexableField docField, Field field, Object destinationObject) throws LuckeneMappingException {

	}

	@Override
	public boolean forType(LuckeneMapperField field) {
		return String.class.equals(field.getField().getType());
	}

}
