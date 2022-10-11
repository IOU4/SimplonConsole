package simplonclone;

import java.util.ArrayList;

import simplonclone.Database.Database;

public class Administrator extends User {
  public Administrator(String name, String email) {
    super(name, email);
  }

  public static ArrayList<Administrator> getAdministrators(Database db) {
    return db.getAdmins();
  }
}
