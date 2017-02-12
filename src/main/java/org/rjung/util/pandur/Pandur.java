package org.rjung.util.pandur;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.sql.DataSource;

public class Pandur {

  private final DataSource dataSource;
  private final Map<Class<?>, org.rjung.util.pandur.description.Object> mapping;

  public Pandur(final DataSource dataSource, final Class<?>... classes) {
    this.dataSource = dataSource;
    mapping = Arrays.stream(classes).collect(
        Collectors.toMap(Function.identity(), org.rjung.util.pandur.description.Object::new));
  }

  public <T extends Object> T find(final Object id, final Class<T> clazz) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  /**
   * The {@link Set} of all classes that can be processed by this instance of Pandur.
   *
   * @return {@link Set} of all processable classes
   */
  public Set<Class<?>> getMappedClasses() {
    return mapping.keySet();
  }
}
