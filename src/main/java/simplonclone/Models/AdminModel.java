package simplonclone.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import simplonclone.Controllers.Administrator;
import simplonclone.App;

public class AdminModel {
  private static Connection con = App.getConnection();

  public static Administrator find(String email) {
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from administrators where email = ?");
      stmnt.setString(1, email);
      var rs = stmnt.executeQuery();
      if (rs.next())
        return new Administrator(rs.getString("name"), rs.getString("email"), rs.getInt("id"));
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  // add new instructor
  public static void addInstructor(String name, String email) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into instructors (name, email) values (?, ?)");
      stmnt.setString(1, name);
      stmnt.setString(2, email);
      stmnt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // add new promo
  public static void addPromo(String name) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into promos (name) values (?)");
      stmnt.setString(1, name);
      stmnt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // add new student
  public static void addStudent(String name, String email, int promoId) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into students (name, email, promo_id) values (?, ?, ?)");
      stmnt.setString(1, name);
      stmnt.setString(2, email);
      stmnt.setInt(3, promoId);
      stmnt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // get all promos
  public static void getAllPromos() {
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from promos");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getInt("id") + " : " + rs.getString("name"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}