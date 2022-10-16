package simplonclone;

import java.util.Scanner;

import simplonclone.Database.Database;

public class App {
  private Scanner scanner;
  private Database db;

  public App() {
    this.scanner = new Scanner(System.in);
    this.db = new Database("postgres", "secret", "simplon");
  }

  private String readEmail() {
    System.out.print("email: ");
    String email = scanner.nextLine();
    return email;
  }

  public void run() {
    System.out.println("welcome to simplon.ma 🎉");
    var email = readEmail();
    System.out.println("loggin you in... ");
    var user = new AdminstratorHandler(this.db).findEmail(email);
    if (user != null)
      handleAdmin(user);

    this.closeAll();
  }

  private void handleAdmin(Administrator admin) {
    System.out.println("hello : " + admin.getName());
  }

  private void handleInstructor(Instructor instructor) {
    System.out.println("hello : " + instructor.getName());
  }

  private void handleStudent(Student student) {
    System.out.println("hello : " + student.getName());
  }

  private void closeAll() {
    this.db.close_db();
    this.scanner.close();
  }
}
