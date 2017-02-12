package org.rjung.util.pandur;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;

import javax.persistence.Column;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappedProperty {
  private static final Logger LOGGER = LoggerFactory.getLogger(MappedObject.class);
  private final PropertyDescriptor description;
  private boolean id;
  private String columnName;

  MappedProperty(final Class<?> clazz, final PropertyDescriptor description) {
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
    final String newColumnName = column.name();
    if (newColumnName != null && !newColumnName.isEmpty()) {
      LOGGER.trace("property {} has column name {}", description.getName(), newColumnName);
      this.columnName = newColumnName;
    }
  }

  public boolean isId() {
    return this.id;
  }
}
