package com.wojewodka.luckene.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Basic {@link Annotation} for indexing field.
 */
@Retention(RUNTIME)
@Target({FIELD})
@Documented
public @interface LuckeneField {
	
	/**
	 * Declared custom name for property in lucene index.
	 */
	String value() default EMPTY;
	
}
