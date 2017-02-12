package org.rjung.util.pandur;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
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
  public void verifyGivenClassesAreInMappedClasses() {
    final Pandur sut = new Pandur(dataSource, User.class);
    assertThat(sut.getMappedClasses(), hasItem(User.class));
  }

  @Test
  public void realTest() throws InvocationTargetException, SQLException, InstantiationException,
      IllegalAccessException {
    dataSource =
        JdbcConnectionPool.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "user", "password");
    connection = dataSource.getConnection();
    statement = connection.createStatement();
    statement.executeUpdate("CREATE TABLE users (id INTEGER, email VARCHAR(64), password CHAR(32))");
    statement.executeUpdate("INSERT INTO users VALUES (1, 'r@iner.cc', 'asdf')");
    statement.close();
    connection.close();
    Pandur pandur = new Pandur(dataSource, User.class);
    List<User> users = pandur.findAll(User.class);
    System.out.println(users.toString());
  }
}
