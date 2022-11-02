package simplonclone.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
  public static boolean addInstructor(String name, String email) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into instructors (name, email) values (?, ?)");
      stmnt.setString(1, name);
      stmnt.setString(2, email);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  // add new promo
  public static boolean addPromo(String name, int instructoId) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into promos (name, instructor_id) values (?, ?)");
      stmnt.setString(1, name);
      stmnt.setInt(2, instructoId);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  // add new student
  public static boolean addStudent(String name, String email, int promoId) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into students (name, email, promo_id) values (?, ?, ?)");
      stmnt.setString(1, name);
      stmnt.setString(2, email);
      stmnt.setInt(3, promoId);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  // get all promos
  public static ArrayList<String> getAllPromos() {
    ArrayList<String> promos = new ArrayList<>();
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from promos");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        promos.add(rs.getInt("id") + "- " + rs.getString("name"));
      }
      return promos;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  // get all instructors
  public static ArrayList<String> getAllInstructors() {
    ArrayList<String> instructors = new ArrayList<>();
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from instructors");
      var rs = stmnt.executeQuery();
      while (rs.next())
        instructors.add(rs.getInt("id") + "- " + rs.getString("name"));
      return instructors;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  // get all students
  public static ArrayList<String> getAllStudents() {
    ArrayList<String> students = new ArrayList<>();
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from students");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        students.add(rs.getInt("id") + "- " + rs.getString("name"));
      }
      return students;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

}
