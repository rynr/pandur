package org.rjung.util.pandur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.rjung.util.pandur.description.MappedObject;

public class Pandur {

  private final DataSource dataSource;
  private final Map<Class<?>, MappedObject> mapping;

  public Pandur(final DataSource dataSource, final Class<?>... classes) {
    this.dataSource = dataSource;
    mapping =
        Arrays.stream(classes).collect(Collectors.toMap(Function.identity(), MappedObject::new));
  }

  // TODO replace Exceptions
  public <T extends Object> T find(final Object id, final Class<T> clazz)
      throws SQLException, InstantiationException, IllegalAccessException {
    if (!mapping.containsKey(clazz)) {
      throw new IllegalArgumentException("class " + clazz.getName() + " not found in mapping");
    }

    final Connection connection = dataSource.getConnection();
    final Statement statement = connection.createStatement();
    final ResultSet resultSet = statement.executeQuery(findSql(id, mapping.get(clazz)));

    if (resultSet.next() && resultSet.last()) {
      final T result = clazz.newInstance();
      // TODO extract data to bean
      return result;
    } else {
      throw new SQLException("expected exactly one result");
    }
  }

  private String findSql(final Object id, final MappedObject object) {
    return "SELECT " + String.join(", " + object.getPropertiesColumnNames()) + " FROM "
        + object.getTableName();
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
