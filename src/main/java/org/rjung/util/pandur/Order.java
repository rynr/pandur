package org.rjung.util.pandur;

public class Order {

  public enum Direction {
    ASC, DESC
  }

  public static Order order(final MappedProperty property) {
    return new Order(property, Direction.ASC);
  }
  private final Direction direction;

  private final MappedProperty property;

  private Order(final MappedProperty property, final Direction direction) {
    this.property = property;
    this.direction = direction;
  }

  @Override
  public String toString() {
    return property.getColumnName() + " " + direction;
  }
}
