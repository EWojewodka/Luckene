package com.wojewodka.luckene.annotation;

import com.wojewodka.luckene.index.LuckeneRepository;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Configuration annotation for easy declare variables and necessary things.
 *
 * @version 1.0
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface LuckeneConfiguration {

	/**
	 * Array of repositories which'll be execution on application start. </br>
	 * Note that only these repositories are handled and managed by Luckene.
	 */
	Class<? extends LuckeneRepository>[] repositories();

}
