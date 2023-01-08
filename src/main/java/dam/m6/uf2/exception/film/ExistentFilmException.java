package dam.m6.uf2.exception.film;

import dam.m6.uf2.exception.EntityException;

public class ExistentFilmException extends EntityException {

  public ExistentFilmException() {
    super("Film already exist!");
  }
}
