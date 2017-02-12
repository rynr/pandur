package org.rjung.util.pandur.description;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.junit.Test;
import org.rjung.util.pandur.beans.User;

public class PropertyTest {

  private static final String PROPERTY_PASSWORD = "passwordEncrypted";
  private static final String PROPERTY_EMAIL = "email";
  private static final String PROPERTY_ID = "id";

  @Test
  public void verifyColumnNameIsTakenFromAnnotationIfSetInColumn() throws IntrospectionException {
    final Property<User> result =
        new Property<>(User.class, new PropertyDescriptor(PROPERTY_EMAIL, User.class, null, null));

    assertThat(result.getColumnName(), is(PROPERTY_EMAIL));
  }

  @Test
  public void verifyColumnNameIsTakenFromPropertyIfNotSetInColumn() throws IntrospectionException {
    final Property<User> result = new Property<>(User.class,
        new PropertyDescriptor(PROPERTY_PASSWORD, User.class, null, null));

    assertThat(result.getColumnName(), is("password"));
  }

  @Test
  public void verifyIdIsTakenFromAnnotation() throws IntrospectionException {
    final Property<User> resultId =
        new Property<>(User.class, new PropertyDescriptor(PROPERTY_ID, User.class, null, null));
    final Property<User> resultEmail =
        new Property<>(User.class, new PropertyDescriptor(PROPERTY_EMAIL, User.class, null, null));
    final Property<User> resultPassword = new Property<>(User.class,
        new PropertyDescriptor(PROPERTY_PASSWORD, User.class, null, null));

    assertThat(resultId.isId(), is(true));
    assertThat(resultEmail.isId(), is(false));
    assertThat(resultPassword.isId(), is(false));
  }


}
