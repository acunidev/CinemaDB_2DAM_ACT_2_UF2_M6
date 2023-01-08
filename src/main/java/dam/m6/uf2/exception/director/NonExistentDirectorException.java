package dam.m6.uf2.exception.director;

import dam.m6.uf2.exception.EntityException;

public class NonExistentDirectorException extends EntityException {

  public NonExistentDirectorException() {
    super("Director does not exist!");
  }
}
