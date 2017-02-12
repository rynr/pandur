package org.rjung.util.pandur;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.sql.DataSource;

public class Pandur {

  private final DataSource dataSource;
  private final Map<Class<?>, MappedObject> mapping;

  public Pandur(final DataSource dataSource, final Class<?>... classes) {
    this.dataSource = dataSource;
    mapping =
        Arrays.stream(classes).collect(Collectors.toMap(Function.identity(), MappedObject::new));
  }

  public <T extends Object> List<T> findAll(final Class<T> clazz) throws SQLException,
      IllegalAccessException, InstantiationException, InvocationTargetException {
    verifyClassIsMapped(clazz);
    List<T> result = new ArrayList<>();
    MappedObject mapped = mapping.get(clazz);
    Query query = Query.query(mapped);
    ResultSet resultSet = query(query);
    while (resultSet.next()) {
      T entry = clazz.newInstance();
      for (MappedProperty property : mapped.getMappedProperties()) {
        if (property.getPropertyClass() == String.class) {
          property.getWriteMethod().invoke(entry, resultSet.getString(property.getColumnName()));
        } else if (property.getPropertyClass() == Long.class) {
          property.getWriteMethod().invoke(entry, resultSet.getLong(property.getColumnName()));
        }
      }
      result.add(entry);
    }

    return result;
  }

  /**
   * The {@link Set} of all classes that can be processed by this instance of Pandur.
   *
   * @return {@link Set} of all processable classes
   */
  public Set<Class<?>> getMappedClasses() {
    return mapping.keySet();
  }

  private ResultSet query(Query query) throws SQLException {
    final Connection connection = dataSource.getConnection();
    final Statement statement = connection.createStatement();
    return statement.executeQuery(query.toString());
  }

  private <T> void verifyClassIsMapped(final Class<?> clazz) {
    if (!mapping.containsKey(clazz)) {
      throw new IllegalArgumentException("class " + clazz.getName() + " not found in mapping");
    }
  }
}
