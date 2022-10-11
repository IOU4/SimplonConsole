package simplonclone;

import java.io.IOException;
import java.util.Scanner;

import simplonclone.Database.Database;

public class App {
  private Scanner scanner;
  private Database db;

  public App() {
    this.scanner = new Scanner(System.in);
    this.db = new Database("postgres", "secret", "simplon");
  }

  private String readEmail() throws IOException {
    System.out.print("email: ");
    String email = scanner.nextLine();
    System.out.println("loggin you in... ");
    return email;
  }

  private User getIdFromEmail(String email) {
    try {

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.out.println("email not found!");
    return null;
  }

  public void run() {
    // try {
    System.out.println("welcome to simplon.ma 🎉");
    var admins = Administrator.getAdministrators(this.db);
    // User user = getIdFromEmail(readEmail());
    //
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // System.out.printf("welcome aboard %s\n", email);
    this.db.close_db();
    this.scanner.close();
  }
}
