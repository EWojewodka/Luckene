package com.wojewodka.luckene.mapper;

import com.wojewodka.luckene.annotation.LuckeneField;
import lombok.Data;

import java.lang.reflect.Field;

@Data
public class LuckeneMapperField {
	
	private Field field;
	private LuckeneField luckeneAnnotation;
}
