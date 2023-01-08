package dam.m6.uf2.exception.director;

import dam.m6.uf2.exception.EntityException;

public class ExistentDirectorException extends EntityException {

  public ExistentDirectorException() {
    super("Director does exist!");
  }
}
