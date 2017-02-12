package org.rjung.util.pandur;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TestHelper {

  private static SecureRandom secureRandom;

  private static SecureRandom getRandom() {
    if (secureRandom == null) {
      secureRandom = new SecureRandom();
    }
    return secureRandom;
  }

  public static String randomString(final int length) {
    return new BigInteger(5 * length, getRandom()).toString(32);
  }

}
