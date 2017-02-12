package org.rjung.util.pandur.helper;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This {@link Annotation} does not have a meaning at all. But like this it can be used to test that
 * it is ignored when others are parsed.
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface ArbitraryAnnotation {
}
