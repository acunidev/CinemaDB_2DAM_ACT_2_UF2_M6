package dam.m6.uf2.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  private static Connection connection = null;

  /*
   *  Java supports a special block, called a static block (also called static clause)
   *  that can be used for static initialization of a class.
   *  This code inside the static block is executed only once: the first time the class is loaded into memory.
   * */
  static {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/cinema_db";
    String user = "root";
    String pass = "acuni";
    try {
      Class.forName(driver);
      connection = DriverManager.getConnection(url, user, pass);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private DatabaseConnection() {
  }

  public static Connection getConnection() {
    return connection;
  }

}
