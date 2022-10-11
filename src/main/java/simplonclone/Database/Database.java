package simplonclone.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import simplonclone.Administrator;

public class Database {
  private Connection con;

  public Database(String username, String passwd, String database) {
    try {
      this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, username, passwd);
      System.out.println("successfull connectionn whooo!");
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

  public ArrayList<Administrator> getAdmins() {
    var admins = new ArrayList<Administrator>();
    try {
      var stmnt = this.con.createStatement();
      var rs = stmnt.executeQuery("select * from administrators;");
      System.out.println(rs.getFetchSize());
      while (rs.next()) {
        rs.getString("name");
        admins.add(new Administrator(rs.getString("name"), rs.getString("email")));
      }
    } catch (SQLException ex) {
      System.out.println("couldn't get columns");
    }
    return admins;
  }
}
