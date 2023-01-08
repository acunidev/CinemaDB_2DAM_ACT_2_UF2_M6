package dam.m6.uf2.model;

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
public class Director {

  private int directorId;
  private String name;
  private int birthYear;
  private String country;

  /**
   * Instantiates a new Director.
   *
   * @param name      the name
   * @param birthYear the birth year
   * @param country   the country
   */
  public Director(String name, int birthYear, String country) {
    this.name = name;
    this.birthYear = birthYear;
    this.country = country;
  }

}
