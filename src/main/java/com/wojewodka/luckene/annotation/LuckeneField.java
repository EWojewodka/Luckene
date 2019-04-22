package com.wojewodka.luckene.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Basic {@link Annotation} for indexing field.
 */
@Retention(RUNTIME)
@Target({FIELD})
@Documented
public @interface LuckeneField {

	@AliasFor("name")
	String value() default EMPTY;

	/**
	 * Declared custom name for property in lucene index.
	 */
	String name() default EMPTY;

}
