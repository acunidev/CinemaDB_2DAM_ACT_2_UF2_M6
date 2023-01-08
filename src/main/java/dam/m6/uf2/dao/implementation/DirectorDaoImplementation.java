package dam.m6.uf2.dao.implementation;

import dam.m6.uf2.connection.DatabaseConnection;
import dam.m6.uf2.dao.CinemaDao;
import dam.m6.uf2.exception.director.ExistentDirectorException;
import dam.m6.uf2.exception.director.NonExistentDirectorException;
import dam.m6.uf2.exception.film.NonExistentFilmException;
import dam.m6.uf2.model.Director;
import dam.m6.uf2.util.QueriesMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectorDaoImplementation implements CinemaDao<Director, Integer> {

  private static final Logger LOGGER = Logger.getLogger(DirectorDaoImplementation.class.getName());
  private static final String DIRECTOR_ID = "director_id";
  private static final String NAME = "name";
  private static final String BIRTH_YEAR = "birth_year";
  private static final String COUNTRY = "country";
  private static Connection connection = DatabaseConnection.getConnection();

  @Override
  public int add(Director director) {
    int executeUpdateResult = 0;
    try {
      if (checkIfExistData(director)) {
        throw new ExistentDirectorException();
      }
      try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.ADD_DIRECTOR)) {
        ps.setString(1, director.getName());
        ps.setInt(2, director.getBirthYear());
        ps.setString(3, director.getCountry());

        executeUpdateResult = ps.executeUpdate();
        LOGGER.log(Level.INFO, "Director added to database? {0}", executeUpdateResult > 0);

        return executeUpdateResult;
      } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    } catch (ExistentDirectorException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return executeUpdateResult;
  }

  @Override
  public void delete(Integer id) {
    final Director director = getData(id);
    if (!checkIfExistData(director)) {
      try {
        throw new NonExistentFilmException();
      } catch (NonExistentFilmException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    }
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.DELETE_DIRECTOR)) {
      ps.setInt(1, id);

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

  }

  @Override
  public Director getData(Integer id) {
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.GET_DIRECTOR_BY_ID)) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          final int directorId = rs.getInt(DIRECTOR_ID);
          final String name = rs.getString(NAME);
          final int birthYear = rs.getInt(BIRTH_YEAR);
          final String country = rs.getString(COUNTRY);
          return new Director(directorId, name, birthYear, country);
        }
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return null;
  }

  @Override
  public List<Director> getAllData() {
    List<Director> directors = new ArrayList<>();
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.GET_ALL_DIRECTORS);
        ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        final int directorId = rs.getInt(DIRECTOR_ID);
        final String name = rs.getString(NAME);
        final int birthYear = rs.getInt(BIRTH_YEAR);
        final String country = rs.getString(COUNTRY);
        directors.add(new Director(directorId, name, birthYear, country));
      }
      return directors;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return directors;
  }

  @Override
  public void update(Director director) {
    //TODO no update method for now.
  }

  @Override
  public boolean checkIfExistData(Director director) {
    boolean exist = false;
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.CHECK_IF_DIRECTOR_EXIST)) {
      ps.setString(1, director.getName());
      ps.setObject(2, director.getBirthYear());
      ps.setString(3, director.getCountry());
      ResultSet rs = ps.executeQuery();

      exist = rs.next();
      return exist;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return exist;
  }

  public Director getDirectorByName(String nameDirector) {
    try (PreparedStatement ps = connection.prepareStatement(QueriesMySql.GET_DIRECTOR_BY_NAME)) {
      ps.setString(1, nameDirector);
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next()) {
          throw new NonExistentDirectorException();
        }
        final int directorId = rs.getInt(DIRECTOR_ID);
        final String name = rs.getString(NAME);
        final int birthYear = rs.getInt(BIRTH_YEAR);
        final String country = rs.getString(COUNTRY);
        return new Director(directorId, name, birthYear, country);
      }
    } catch (SQLException | NonExistentDirectorException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return null;
  }
}

