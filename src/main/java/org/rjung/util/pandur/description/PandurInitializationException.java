package org.rjung.util.pandur.description;

public class PandurInitializationException extends RuntimeException {
  private static final long serialVersionUID = -2622329874880395287L;

  public PandurInitializationException() {
    super();
  }

  public PandurInitializationException(final String message) {
    super(message);
  }

  public PandurInitializationException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public PandurInitializationException(final Throwable cause) {
    super(cause);
  }
}
