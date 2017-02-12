package org.rjung.util.pandur;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
  @Mock
  private Connection connection;
  @Mock
  private Statement statement;
  @Mock
  private ResultSet resultSet;

  @Test
  public void verifyFindIsNotYetImplemented()
      throws InstantiationException, IllegalAccessException, SQLException {
    when(dataSource.getConnection()).thenReturn(connection);
    when(connection.createStatement()).thenReturn(statement);
    when(statement.executeQuery(anyString())).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true);
    when(resultSet.last()).thenReturn(true);
    final Pandur sut = new Pandur(dataSource, User.class);

    final User result = sut.find("123", User.class);
  }

  @Test
  public void verifyGivenClassesAreInMappedClasses() {
    final Pandur sut = new Pandur(dataSource, User.class);
    assertThat(sut.getMappedClasses(), hasItem(User.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void verifyUnknownClassesCauseIllegalArgumentException()
      throws InstantiationException, IllegalAccessException, SQLException {
    final Pandur sut = new Pandur(dataSource, User.class);
    sut.find("123", List.class);
  }
}
