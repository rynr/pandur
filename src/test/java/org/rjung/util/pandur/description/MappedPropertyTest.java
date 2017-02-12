package org.rjung.util.pandur.description;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.junit.Test;
import org.rjung.util.pandur.beans.User;

public class MappedPropertyTest {

  private static final String PROPERTY_PASSWORD = "passwordEncrypted";
  private static final String PROPERTY_EMAIL = "email";
  private static final String PROPERTY_ID = "id";
  private static final String INVALID_PROPERTY = "invalid";

  @Test
  public void verifyColumnNameIsTakenFromAnnotationIfSetInColumn() throws IntrospectionException {
    final MappedProperty result =
        new MappedProperty(User.class, new PropertyDescriptor(PROPERTY_EMAIL, User.class, null, null));

    assertThat(result.getColumnName(), is(PROPERTY_EMAIL));
  }

  @Test
  public void verifyColumnNameIsTakenFromPropertyIfNotSetInColumn() throws IntrospectionException {
    final MappedProperty result =
        new MappedProperty(User.class, new PropertyDescriptor(PROPERTY_PASSWORD, User.class, null, null));

    assertThat(result.getColumnName(), is("password"));
  }

  @Test
  public void verifyIdIsTakenFromAnnotation() throws IntrospectionException {
    final MappedProperty resultId =
        new MappedProperty(User.class, new PropertyDescriptor(PROPERTY_ID, User.class, null, null));
    final MappedProperty resultEmail =
        new MappedProperty(User.class, new PropertyDescriptor(PROPERTY_EMAIL, User.class, null, null));
    final MappedProperty resultPassword =
        new MappedProperty(User.class, new PropertyDescriptor(PROPERTY_PASSWORD, User.class, null, null));

    assertThat(resultId.isId(), is(true));
    assertThat(resultEmail.isId(), is(false));
    assertThat(resultPassword.isId(), is(false));
  }

  @Test(expected = PandurInitializationException.class)
  public void verifyUnknownColumnNameCausesException() throws IntrospectionException {
    new MappedProperty(User.class, new PropertyDescriptor(INVALID_PROPERTY, User.class, null, null));
  }

}
