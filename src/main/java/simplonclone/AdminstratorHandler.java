package simplonclone;

import java.util.ArrayList;

import simplonclone.Database.Database;

public class AdminstratorHandler {
  public ArrayList<Administrator> admins;
  private Database con;

  public AdminstratorHandler(Database db) {
    this.con = db;
    this.admins = con.getAdmins();
  }

  public Administrator findEmail(String email) {
    for (Administrator admin : admins) {
      if (admin.getEmail().equals(email))
        return admin;
    }
    return null;
  }
}
