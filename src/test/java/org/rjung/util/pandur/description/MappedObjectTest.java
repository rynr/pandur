package org.rjung.util.pandur.description;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

import java.beans.IntrospectionException;

import org.junit.Test;
import org.rjung.util.pandur.beans.User;

public class MappedObjectTest {

  @Test
  public void PropertiesAreExtractedFromGivenClass() throws IntrospectionException {
    final MappedObject object = new MappedObject(User.class);

    assertThat(object.getProperties(), hasItems("id", "email", "passwordEncrypted"));
  }
}
