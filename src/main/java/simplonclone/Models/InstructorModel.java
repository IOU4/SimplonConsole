package simplonclone.Models;

import simplonclone.Controllers.Instructor;
import java.sql.*;
import java.util.ArrayList;

import simplonclone.App;

public class InstructorModel {
  private static Connection con = App.getConnection();

  public static Instructor find(String email) {
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from instructors where email = ?");
      stmnt.setString(1, email);
      var rs = stmnt.executeQuery();
      if (rs.next())
        return new Instructor(rs.getString("name"), rs.getString("email"), rs.getInt("id"));
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  // get all instructors
  public static ArrayList<Instructor> getAllInstructors() {
    try {
      ArrayList<Instructor> instructors = new ArrayList<Instructor>();
      PreparedStatement stmnt = con.prepareStatement("select * from instructors");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        instructors.add(new Instructor(rs.getString("name"), rs.getString("email"), rs.getInt("id")));
      }
      return instructors;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
