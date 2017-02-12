package org.rjung.util.pandur.description;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Object<T> {

  private static final List<String> IGNORE_FIELDS = Arrays.asList("class");

  private final Map<String, Property<T>> properties;

  public Object(final Class<T> clazz) {
    try {
      this.properties = Arrays.stream(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
          .filter(d -> !IGNORE_FIELDS.contains(d.getName())).map(p -> new Property<T>(clazz, p))
          .collect(Collectors.toMap(Property::getName, Function.identity()));
    } catch (final IntrospectionException e) {
      throw new PandurInitializationException(e.getMessage(), e);
    }
  }

  public Set<String> getProperties() {
    return properties.keySet();
  }
}
