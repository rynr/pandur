package org.rjung.util.pandur.description;

import java.beans.IntrospectionException;

import org.junit.Test;
import org.rjung.util.pandur.beans.User;

public class ObjectTest {

  @Test
  public void bla() throws IntrospectionException {
    new Object<>(User.class);
  }
}
