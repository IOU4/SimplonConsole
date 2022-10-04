package simplonclone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class App {
  public static void getGreeting() {
    System.out.println("Hello To simplon clone!");
  }

  private static String readEmail() throws IOException {
    System.out.println("email: ");
    var input = new BufferedReader(new InputStreamReader(System.in));
    String email = input.readLine();
    System.out.println("loggin you in... ");
    return email;
  }

  private static User getIdFromEmail(String email) {
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

  public static void main(String[] args) throws IOException {
    getGreeting();
    User user = getIdFromEmail(readEmail());

    // System.out.printf("welcome aboard %s\n", email);
  }
}
