package dam.m6.uf2.dao.implementation;

import dam.m6.uf2.connection.DatabaseConnection;
import dam.m6.uf2.dao.CinemaDao;
import dam.m6.uf2.exception.film.ExistentFilmException;
import dam.m6.uf2.exception.film.NonExistentFilmException;
import dam.m6.uf2.model.Film;
import dam.m6.uf2.util.QueriesMySql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Film dao implementation.
 */
public class FilmDaoImplementation implements CinemaDao<Film, Integer> {


  private static final Logger LOGGER = Logger.getLogger(FilmDaoImplementation.class.getName());
  private static final String FILM_ID = "film_id";
  private static final String TITLE = "title";
  private static final String RELEASE_DATE = "release_date";
  private static final String COUNTRY = "country";
  private static final String DIRECTOR_ID = "director_id";
  private static Connection connection = DatabaseConnection.getConnection();

  /**
   * Gets all films between years.
   *
   * @param firstYear  the first year
   * @param secondYear the second year
   * @return all films between years
   */
  public List<Film> getAllFilmsBetweenYears(String firstYear, String secondYear) {
    List<Film> films = new ArrayList<>();
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.GET_ALL_FILMS_BETWEEN_YEARS)) {
      ps.setString(1, firstYear);
      ps.setString(2, secondYear);

      try (ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
          final int filmId = rs.getInt(FILM_ID);
          final String title = rs.getString(TITLE);
          final Date releaseDate = rs.getObject(RELEASE_DATE, Date.class);
          final String country = rs.getString(COUNTRY);
          final int directorId = rs.getInt(DIRECTOR_ID);
          films.add(new Film(filmId, title, releaseDate, country, directorId));
        }
      }
      return films;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, null, e);
    }
    return films;
  }

  /**
   * Gets all films by director title year.
   *
   * @param directorName the director name
   * @return all films by director title year
   */
  public List<Film> getAllFilmsByDirectorTitleYear(String directorName) {
    List<Film> films = new ArrayList<>();
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.GET_FILM_BY_DIRECTOR_TITLE_YEAR)) {
      ps.setString(1, directorName);

      try (ResultSet rs = ps.executeQuery()) {

        System.out.printf("\t\t%s%n", directorName);
        while (rs.next()) {
          final int filmId = rs.getInt(FILM_ID);
          final String title = rs.getString(TITLE);
          final Date releaseDate = rs.getObject(RELEASE_DATE, Date.class);
          final String country = rs.getString(COUNTRY);
          final int directorId = rs.getInt(DIRECTOR_ID);
          final int year = rs.getInt("year");
          System.out.printf("%-25s=>%5s%n", title, year);
          films.add(new Film(filmId, title, releaseDate, country, directorId));
        }
        System.out.println();
      }
      return films;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, null, e);
    }
    return films;
  }

  /**
   * Gets film by director name.
   *
   * @param directorName the director name
   * @return film by director name
   */
  public Film getFilmByDirectorName(String directorName) {
    //TODO Add method to get film by directorName
    return null;
  }

  @Override
  public int add(Film film) {
    int executeUpdateResult = 0;
    try {
      if (checkIfExistData(film)) {
        throw new ExistentFilmException();
      }
      try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.ADD_FILM)) {
        ps.setString(1, film.getTitle());
        ps.setObject(2, film.getReleaseDate());
        ps.setString(3, film.getCountry());
        ps.setInt(4, film.getDirectorId());

        executeUpdateResult = ps.executeUpdate();
        LOGGER.log(Level.INFO, "Film added to database? {0}", executeUpdateResult > 0);

        return executeUpdateResult;
      } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    } catch (ExistentFilmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return executeUpdateResult;
  }

  @Override
  public void delete(Integer id) {
    final Film film = getData(id);
    if (!checkIfExistData(film)) {
      try {
        throw new NonExistentFilmException();
      } catch (NonExistentFilmException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    }
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.DELETE_FILM)) {
      ps.setInt(1, id);

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

  }

  @Override
  public Film getData(Integer id) {
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.GET_FILM_BY_ID)) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
          final int filmId = rs.getInt(FILM_ID);
          final String title = rs.getString(TITLE);
          final Date releaseDate = rs.getObject(RELEASE_DATE, Date.class);
          final String country = rs.getString(COUNTRY);
          final int directorId = rs.getInt(DIRECTOR_ID);
          return new Film(filmId, title, releaseDate, country, directorId);
        }
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return null;
  }

  @Override
  public List<Film> getAllData() {
    List<Film> films = new ArrayList<>();
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.GET_ALL_FILMS);
        ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        final int filmId = rs.getInt(FILM_ID);
        final String title = rs.getString(TITLE);
        final Date releaseDate = rs.getObject(RELEASE_DATE, Date.class);
        final String country = rs.getString(COUNTRY);
        final int directorId = rs.getInt(DIRECTOR_ID);
        films.add(new Film(filmId, title, releaseDate, country, directorId));
      }
      return films;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return films;
  }

  @Override
  public void update(Film film) {
    //TODO no update method for now.
  }

  @Override
  public boolean checkIfExistData(Film film) {
    boolean exist = false;
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.CHECK_IF_FILM_EXIST)) {
      ps.setString(1, film.getTitle());
      ps.setObject(2, film.getReleaseDate());
      ps.setString(3, film.getCountry());
      ResultSet rs = ps.executeQuery();

      exist = rs.next();
      return exist;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return exist;
  }
}
