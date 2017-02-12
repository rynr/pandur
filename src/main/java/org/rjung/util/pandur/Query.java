package org.rjung.util.pandur;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Query {

  static Query query(final MappedObject clazz) {
    return new Query(clazz, clazz.getMappedProperties());
  }

  static Query query(final MappedObject clazz, final Collection<MappedProperty> properties) {
    return new Query(clazz, clazz.getMappedProperties());
  }

  private final MappedObject object;
  private final Map<String, MappedProperty> properties;
  private Order order;

  private Query(final MappedObject object, final Collection<MappedProperty> properties) {
    this.object = object;
    this.properties =
        properties.stream().collect(Collectors.toMap(MappedProperty::getName, Function.identity()));
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Query other = (Query) obj;
    return Objects.equals(this.object, other.object)
        && Objects.equals(this.properties, other.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(object, properties);
  }

  public void order(final String property) {
    orderBy(properties.get(property));
  }

  private void orderBy(final MappedProperty property) {
    this.order = Order.order(property);
  }


  @Override
  public String toString() {
    return "SELECT " + properties.values().stream().map(MappedProperty::getColumnName)
        .collect(Collectors.joining(",")) + " FROM " + object.getTableName()
        + (order == null ? "" : "ORDER BY " + order);
  }

}
