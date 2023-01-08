package dam.m6.uf2;

import dam.m6.uf2.dao.implementation.DirectorDaoImplementation;
import dam.m6.uf2.dao.implementation.FilmDaoImplementation;
import dam.m6.uf2.model.Film;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {


  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    DirectorDaoImplementation directorDao = new DirectorDaoImplementation();
    FilmDaoImplementation filmDao = new FilmDaoImplementation();

    /*
     *TODO:
     * [1,5 punts]
     * Pel·lícules estrenades en un interval d’anys en concret (per exemple entre 1978 i 1990).
     * Aquest interval el pensareu vosaltres a partir dels registres que hagueu ficat a les taules.
     * Formateu adequadament.
     *  */
    filmsBetweenTwoDates(filmDao);

    /*
     *TODO:
     * [2 punts]
     * Pel·lícules de cada director, però sols mostrar per pantalla el títol de la pel·lícula i any d’estrena (sols any).
     * Formateu adequadament.
     * */
    filmsByDirectorShowTitleYear(filmDao);

    /*
     *TODO:
     * [2,5 punts]
     * Inserir noves pel·lícules a l’entitat Films.
     * Caldrà controlar prèviament que no existeixi a la taula,
     * mirant que no hi hagi altre registre amb el mateix títol (per fer-ho simple).
     * */
    insertFilmDatabaseWithDirectorName(filmDao);
  }

  private static void filmsBetweenTwoDates(FilmDaoImplementation filmDao) {
    System.out.println("Indica el rang d’anys de les pel·lícules a buscar.");
    System.out.println("Primer any: ");   // exemple: 2010
    final String firstYear = scanner.nextLine().trim();
    System.out.println("Segon any: ");   // exemple: 2014
    final String secondYear = scanner.nextLine().trim();
    filmDao.getAllFilmsBetweenYears(firstYear, secondYear).forEach(System.out::println);
  }

  private static void filmsByDirectorShowTitleYear(FilmDaoImplementation filmDao) {
    System.out.println("Indica el director de les pel·lícules a buscar. ");
    final String directorName = scanner.nextLine().trim();   // exemple: Christopher Nolan
    filmDao.getAllFilmsByDirectorTitleYear(directorName);
  }

  private static void insertFilmDatabaseWithDirectorName(FilmDaoImplementation filmDao) {
//    Scanner SCANNER = new Scanner(System.in);
    System.out.println("Indica el títol de la de la pel·lícula a afegir. ");
    final String filmName = scanner.nextLine().trim();
    System.out.println("Indica l’any d’estrena de la pel·lícula");
    final String next = scanner.nextLine().trim();
    final Date filmDate = Date.valueOf(next);
    System.out.println("Indica el país d’estrena de la pel·lícula");
    final String filmCountry = scanner.nextLine().trim();
    System.out.println("Indica el nom del director de la pel·lícula");
    final String nameDirector = scanner.nextLine().trim();
    filmDao.add(new Film(filmName, filmDate, filmCountry, nameDirector));
  }
}