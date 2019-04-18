package com.wojewodka.luckene.mapper;

import com.wojewodka.luckene.annotation.LuckeneField;
import com.wojewodka.luckene.exception.LuckeneMappingException;
import org.apache.lucene.document.Document;

/**
 * Serializer and deserializer for {@link Object}s and {@link Document}.
 *
 * @see LuckeneField
 */
public interface LuckeneMapper {
	
	/**
	 * Serialize object to lucene {@link Document}. <br/>
	 * Implementation should consider a {@link LuckeneField} and allow to customize serializer using suitable annotation
	 *
	 * @param obj {@link Object} which should be serialized to {@link Document}
	 * @return result of serialization, {@link Document}
	 * @throws LuckeneMappingException if there will be throwed any {@link Exception} then it will be wrapped into {@link LuckeneMappingException}
	 * @throws NullPointerException    if {@link Object} is null.
	 */
	Document serialize(Object obj) throws LuckeneMappingException;
	
	/**
	 * \
	 * Deserialize document to indicated type of class. <br/>
	 * Mechanism for deserialization should using same annotations as a {@link #serialize(Object)}, e.g. {@link LuckeneField}.<br/>
	 * You can deserialize {@link Document} to any type with fields annotated by {@link LuckeneField}
	 *
	 * @param document         serialized lucene document which'll be deserialized
	 * @param destinationClass destination type of deserialization
	 * @return deserialized object
	 * @throws LuckeneMappingException if there will be throwed any {@link Exception} then it'll be wrapped into {@link LuckeneMappingException}
	 * @throws NullPointerException    if any  of parameters is null.
	 */
	<T> T deserialize(Document document, Class<T> destinationClass) throws LuckeneMappingException;
	
}
