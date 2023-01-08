package dam.m6.uf2.exception.film;

import dam.m6.uf2.exception.EntityException;

public class NonExistentFilmException extends EntityException {

  public NonExistentFilmException() {
    super("Film does not exist!");
  }
}
