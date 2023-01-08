package dam.m6.uf2.util;

public class QueriesMySql {

  public static final String ADD_FILM = "INSERT INTO film(title, release_date, country, director_id) VALUES (?,?,?,?)";
  public static final String DELETE_FILM = "DELETE FROM film WHERE film_id = ?";
  public static final String GET_FILM_BY_ID = "SELECT * FROM film WHERE film_id =  ?";
  public static final String GET_ALL_FILMS = "SELECT * FROM film";
  public static final String GET_ALL_FILMS_BETWEEN_YEARS = "SELECT * FROM film WHERE YEAR(release_date) BETWEEN ? AND ?";
  public static final String CHECK_IF_FILM_EXIST = """
      SELECT title
      FROM film f
               WHERE title = ?
        AND release_date = ?
        AND f.country = ?
      """;
  public static final String GET_FILM_BY_DIRECTOR_TITLE_YEAR = """
      SELECT *, YEAR(release_date) AS year
      FROM film f
               LEFT JOIN director d ON d.director_id = f.director_id
      WHERE d.name = ?
      """;

  //  Director Queries
  public static final String ADD_DIRECTOR = "INSERT INTO director(name, birth_year, country) VALUES (?,?,?)";
  public static final String DELETE_DIRECTOR = "DELETE FROM director WHERE director_id = ?";
  public static final String GET_DIRECTOR_BY_ID = "SELECT * FROM director WHERE director_id =  ?";
  public static final String GET_DIRECTOR_BY_NAME = "SELECT * FROM director WHERE name =  ?";
  public static final String GET_ALL_DIRECTORS = "SELECT * FROM director";
  public static final String CHECK_IF_DIRECTOR_EXIST = "SELECT * FROM director WHERE name = ? AND birth_year = ? AND country = ?";

  private QueriesMySql() {

  }

}
