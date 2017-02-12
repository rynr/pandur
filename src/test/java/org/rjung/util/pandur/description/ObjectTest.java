package org.rjung.util.pandur.description;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

import java.beans.IntrospectionException;

import org.junit.Test;
import org.rjung.util.pandur.beans.User;

public class ObjectTest {

  @Test
  public void PropertiesAreExtractedFromGivenClass() throws IntrospectionException {
    final Object<User> object = new Object<>(User.class);

    assertThat(object.getProperties(), hasItems("id", "email", "passwordEncrypted"));
  }
}
