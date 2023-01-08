package dam.m6.uf2.model;

import dam.m6.uf2.dao.implementation.DirectorDaoImplementation;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Entity class for Film
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Data
//Lombok creates getter/setters/constructor/equals/hash/toString
public class Film {

  private static final DirectorDaoImplementation DIRECTOR_DAO_IMPLEMENTATION = new DirectorDaoImplementation();
  private int filmId;
  private String title;
  private Date releaseDate;
  private String country;
  private int directorId;


  /**
   * Instantiates a new Film.
   * <p>
   * Not knowing directorId but name
   *
   * @param title       the title
   * @param releaseDate the release date
   * @param country     the country
   */
  public Film(String title, Date releaseDate, String country, String directoName) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.country = country;
    this.directorId = DIRECTOR_DAO_IMPLEMENTATION.getDirectorByName(directoName).getDirectorId();
  }

  /**
   * Instantiates a new Film.
   * <p>
   * Knowing directorId
   *
   * @param title       the title
   * @param releaseDate the relase date
   * @param country     the country
   * @param directorId  the director id
   */
  public Film(String title, Date releaseDate, String country, int directorId) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.country = country;
    this.directorId = directorId;
  }
}
