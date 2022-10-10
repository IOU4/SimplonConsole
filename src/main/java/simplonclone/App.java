package simplonclone;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONTokener;

import simplonclone.Database.Database;

public class App {

  private Scanner scanner;

  public App() {
    this.scanner = new Scanner(System.in);
    new Database("postgres", "secret", "simplon");
  }

  private String readEmail() throws IOException {
    System.out.print("email: ");
    String email = scanner.nextLine();
    System.out.println("loggin you in... ");
    scanner.close();
    return email;
  }

  private User getIdFromEmail(String email) {
    try (var reader = new FileReader("src/main/resources/data.json")) {
      var data = new JSONObject(new JSONTokener(reader));

      // look for email in admins array
      var admins = data.getJSONArray("administrators");
      for (int i = 0; i < admins.length(); i++) {
        JSONObject current = admins.optJSONObject(i);
        if (current.opt("email").equals(email)) {
          System.out.println("welcom admin " + current.getString("name"));
          return new Administrator(current.getString("name"), current.getString("email"));
        }
      }

      // look for email in admins array
      var instructors = data.getJSONArray("instructors");
      for (int i = 0; i < instructors.length(); i++) {
        JSONObject current = instructors.optJSONObject(i);
        if (current.opt("email").equals(email)) {
          System.out.println("welcom instructor " + current.getString("name"));
          return new Instructor(current.getString("name"), current.getString("email"));
        }
      }

    } catch (Exception ex) {
      System.err.println(ex);
    }

    System.out.println("email not found!");
    return null;
  }

  public void main(String[] args) throws IOException {
    System.out.println("welcome to simplon.ma 🎉");
    User user = getIdFromEmail(readEmail());

    // System.out.printf("welcome aboard %s\n", email);
  }
}
