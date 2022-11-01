package simplonclone;

import java.sql.Connection;
import java.util.Scanner;

import simplonclone.Controllers.Administrator;
import simplonclone.Controllers.Instructor;
import simplonclone.Controllers.Student;
import simplonclone.Database.Database;
import simplonclone.Models.AdminModel;
import simplonclone.Models.UserModel;

public class App {
  private Scanner scanner;
  private static Database db = new Database("postgres", "secret", "simplon");

  public static Connection getConnection() {
    if (db == null)
      db = new Database("postgres", "secret", "simplon");
    return db.con;
  }

  public static Database getDatabase() {
    return db;
  }

  public App() {
    this.scanner = new Scanner(System.in);
  }

  public void run() {
    System.out.println("welcome to simplon.ma 🎉");
    while (true) {
      var email = readEmail();
      System.out.println("loggin you in... ");
      var user = UserModel.find(email);
      if (user instanceof Administrator) {
        handleAdmin((Administrator) user);
        break;
      } else if (user instanceof Student) {
        handleStudent((Student) user);
        break;
      } else if (user instanceof Instructor) {
        handleInstructor((Instructor) user);
        break;
      } else
        System.out.println("user not found!");
    }
    this.closeAll();
  }

  private String readEmail() {
    System.out.print("email: ");
    String email = scanner.nextLine();
    return email;
  }

  private void handleAdmin(Administrator admin) {
    System.out.println("hello : " + admin.getName());
    boolean exit = false;
    while (!exit) {
      printAdminMenu();
      var choice = scanner.nextLine();
      switch (choice) {
        case "1":
          handleAdminAddInstructor();
          break;
        case "2":
          handleAdminAddPromo();
          break;
        case "3":
          handleAdminAddPromo();
          break;
        default:
          exit = true;
      }
    }
  }

  private void handleAdminAddPromo() {
    System.out.printf("promo name :  ");
    String name = scanner.nextLine();
    AdminModel.addPromo(name);
  }

  private void handleAdminAddInstructor() {
    System.out.printf("Instructor name: ");
    String name = scanner.nextLine();
    System.out.printf("Instructor email: ");
    String email = scanner.nextLine();
    AdminModel.addInstructor(name, email);
    System.out.println("added instructor '" + name + "' successfully!");
  }

  private void handleStudent(Student student) {
    System.out.println("hello : " + student.getName());
    printStudentMenu();
  }

  private void handleInstructor(Instructor instructor) {
    System.out.println("hello : " + instructor.getName());
    printInstructorMenu();
  }

  private void closeAll() {
    if (this.scanner != null)
      this.scanner.close();
  }

  private void printAdminMenu() {
    System.out.println(" 1- add instructor\n 2- add promo\n 3- add student\n (anything else will quit) :");
  }

  private void printInstructorMenu() {
    System.out.println("1- add brief\n 2- assing student to promo\n  (anything else will quit) :");
  }

  private void printStudentMenu() {
    System.out.println("1- view briefs\n 2- assing student to promo\n  (anything else will quit) :");
  }
}
