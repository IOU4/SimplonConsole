package simplonclone.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  private Connection db;

  public Database(String username, String passwd, String database) {
    try {
      this.db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, username, passwd);
      System.out.println("successfull connectionn whooo!");
    } catch (SQLException e) {
      e.printStackTrace();
      close_db();
    }
  }

  public Database(String username, String passwd) {
    try {
      this.db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/simplon", username, passwd);
      System.out.println("successfull connectionn whooo!");
    } catch (SQLException e) {
      e.printStackTrace();
      close_db();
    }
  }

  private void close_db() {
    try {
      if (this.db != null)
        this.db.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
