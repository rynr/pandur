package org.rjung.util.pandur;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.rjung.util.pandur.beans.User;

@RunWith(MockitoJUnitRunner.class)
public class PandurTest {

  @Mock
  private DataSource dataSource;

  @Test(expected = UnsupportedOperationException.class)
  public void verifyFindIsNotYetImplemented() {
    // This test is only to calm down solar.
    final Pandur sut = new Pandur(dataSource, User.class);
    sut.find("123", User.class);
  }

  @Test
  public void verifyGivenClassesAreInMappedClasses() {
    final Pandur sut = new Pandur(dataSource, User.class);
    assertThat(sut.getMappedClasses(), hasItem(User.class));
  }
}
