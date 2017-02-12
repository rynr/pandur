package org.rjung.util.pandur.beans;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.rjung.util.pandur.helper.ArbitraryAnnotation;

@Table(name = "users")
public class User {

  @Id
  private Long id;

  @Column()
  @ArbitraryAnnotation
  private String email;

  @Column(name = "password")
  private String passwordEncrypted;

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final User other = (User) obj;
    return Objects.equals(this.id, other.id) && Objects.equals(this.email, other.email)
        && Objects.equals(this.passwordEncrypted, other.passwordEncrypted);
  }

  public String getEmail() {
    return email;
  }

  public Long getId() {
    return id;
  }

  public String getPasswordEncrypted() {
    return passwordEncrypted;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, passwordEncrypted);
  }


  public void setEmail(final String email) {
    this.email = email;
  }

  public void setId(final Long id) {
    this.id = id;
  }


  public void setPasswordEncrypted(final String passwordEncrypted) {
    this.passwordEncrypted = passwordEncrypted;

  }
}
