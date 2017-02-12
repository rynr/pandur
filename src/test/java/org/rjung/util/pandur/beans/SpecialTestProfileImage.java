package org.rjung.util.pandur.beans;

import java.util.Objects;

public class SpecialTestProfileImage {

  private String key;

  private String url;

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final SpecialTestProfileImage other = (SpecialTestProfileImage) obj;
    return Objects.equals(this.key, other.key) && Objects.equals(this.url, other.url);
  }

  public String getKey() {
    return key;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, url);
  }

  public void setKey(final String key) {
    this.key = key;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

}
