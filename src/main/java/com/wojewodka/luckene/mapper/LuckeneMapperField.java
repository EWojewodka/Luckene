package com.wojewodka.luckene.mapper;

import com.wojewodka.luckene.annotation.LuckeneField;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Data
public class LuckeneMapperField {

	@NonNull
	private Field field;
	@NonNull
	private LuckeneField luckeneAnnotation;
	@Nullable
	private LuckeneFieldResolver resolver;

	public String getName() {
		return isEmpty(luckeneAnnotation.name())
			? field.getName()
			: luckeneAnnotation.name();
	}

}
