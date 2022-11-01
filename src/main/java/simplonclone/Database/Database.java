package simplonclone.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  public Connection con;

  public Database(String username, String passwd, String database) {
    try {
      this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, username, passwd);
    } catch (SQLException e) {
      e.printStackTrace();
      close_db();
    }
  }

  public Database(String username, String passwd) {
    try {
      this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/simplon", username, passwd);
    } catch (SQLException e) {
      System.err.println("couldn't connect to database.");
      e.printStackTrace();
      close_db();
    }
  }

  public void close_db() {
    try {
      if (this.con != null)
        this.con.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
