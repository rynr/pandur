package org.rjung.util.pandur.description;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Table;

public class MappedObject {

  private static final List<String> IGNORE_FIELDS = Arrays.asList("class");

  private final String tableName;
  private final Map<String, MappedProperty> properties;

  public MappedObject(final Class<?> clazz) {
    try {
      this.tableName = extractTableName(clazz);
      this.properties = Arrays.stream(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
          .filter(d -> !IGNORE_FIELDS.contains(d.getName())).map(p -> new MappedProperty(clazz, p))
          .collect(Collectors.toMap(MappedProperty::getName, Function.identity()));
    } catch (final IntrospectionException e) {
      throw new PandurInitializationException(e.getMessage(), e);
    }
  }

  private String extractTableName(final Class<?> clazz) {
    final Table table = clazz.getAnnotation(Table.class);
    if (table != null && table.name() != null && !table.name().isEmpty()) {
      return table.name();
    }
    return clazz.getSimpleName().replaceAll("([^_A-Z])([A-Z])", "$1\\_$2").toLowerCase();
  }

  public Set<String> getProperties() {
    return properties.keySet();
  }

  public List<String> getPropertiesColumnNames() {
    return properties.values().stream().map(MappedProperty::getColumnName)
        .collect(Collectors.toList());
  }

  public String getTableName() {
    return tableName;
  }
}
