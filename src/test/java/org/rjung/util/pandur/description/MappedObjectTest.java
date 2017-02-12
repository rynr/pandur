package org.rjung.util.pandur.description;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.beans.IntrospectionException;

import org.junit.Test;
import org.rjung.util.pandur.beans.SpecialTestProfileImage;
import org.rjung.util.pandur.beans.User;

public class MappedObjectTest {

  @Test
  public void verifyPropertiesAreExtractedFromGivenClass() throws IntrospectionException {
    final MappedObject object = new MappedObject(User.class);

    assertThat(object.getProperties(), hasItems("id", "email", "passwordEncrypted"));
  }

  @Test
  public void verifyTableNameIsTakenFromAnnotation() {
    final MappedObject object = new MappedObject(User.class);

    assertThat(object.getTableName(), is("users"));
  }

  @Test
  public void verifyTableNameIsTakenFromClassNameIfNoAnnotationAvailable() {
    final MappedObject object = new MappedObject(SpecialTestProfileImage.class);

    assertThat(object.getTableName(), is("special_test_profile_image"));
  }
}
