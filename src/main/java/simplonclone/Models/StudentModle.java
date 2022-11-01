package simplonclone.Models;

import simplonclone.Controllers.Student;
import java.sql.*;
import simplonclone.App;

public class StudentModle {
  private static Connection con = App.getConnection();

  public static Student find(String email) {
    try {
      Connection con = App.getConnection();
      PreparedStatement stmnt = con.prepareStatement("select * from students where email = ?");
      stmnt.setString(1, email);
      var rs = stmnt.executeQuery();
      if (rs.next())
        return new Student(rs.getString("name"), rs.getString("email"), rs.getInt("id"));
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  // add new student
  public static boolean addStudent(Student student) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into students (name, email) values (?, ?)");
      stmnt.setString(1, student.getName());
      stmnt.setString(2, student.getEmail());
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // remove student
  public static boolean removeStudent(int id) {
    try {
      PreparedStatement stmnt = con.prepareStatement("delete from students where id = ?");
      stmnt.setInt(1, id);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // update student
  public static boolean updateStudent(Student student) {
    try {
      PreparedStatement stmnt = con.prepareStatement("update students set name = ?, email = ? where id = ?");
      stmnt.setString(1, student.getName());
      stmnt.setString(2, student.getEmail());
      stmnt.setInt(3, student.getId());
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

}
