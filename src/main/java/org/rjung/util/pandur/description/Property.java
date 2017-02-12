package org.rjung.util.pandur.description;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;

import javax.persistence.Column;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Property {
  private static final Logger LOGGER = LoggerFactory.getLogger(Object.class);
  private final PropertyDescriptor description;
  private boolean id;
  private String columnName;

  Property(final Class<?> clazz, final PropertyDescriptor description) {
    this.description = description;
    this.columnName = description.getName();

    try {
      for (final Annotation annotation : clazz.getDeclaredField(description.getName())
          .getAnnotations()) {
        handleAnnotation(annotation);
      }
    } catch (NoSuchFieldException | SecurityException e) {
      throw new PandurInitializationException("Invalid PropertyDescriptor was passed", e);
    }
  }

  public String getColumnName() {
    return this.columnName;
  }

  public String getName() {
    return description.getName();
  }

  private void handleAnnotation(final Annotation annotation) {
    switch (annotation.annotationType().getName()) {
      case "javax.persistence.Id":
        LOGGER.trace("property {} is id", description.getName());
        this.id = true;
        break;

      case "javax.persistence.Column":
        handleColumn((Column) annotation);
        break;

      default:
        LOGGER.trace("Found unhandled annotation: {}", annotation);
        break;
    }
  }

  private void handleColumn(final Column column) {
    if (column.name() != null && !column.name().isEmpty()) {
      LOGGER.trace("property {} has column name {}", description.getName(), column.name());
      this.columnName = column.name();
    }
  }

  public boolean isId() {
    return this.id;
  }
}
