package simplonclone.Controllers;

import simplonclone.App;
import simplonclone.Models.AdminModel;

public class Administrator extends User {
  public Administrator(String name, String email, int id) {
    super(name, email, id);
  }

  public Administrator(Administrator admin) {
    super(admin.getName(), admin.getEmail(), admin.getId());
  }

  private void printMenu() {
    System.out.println(" 1- add instructor\n 2- add promo\n 3- add student\n (anything else will quit) :");
  }

  public void handler() {
    System.out.println("hello : " + this.getName());
    boolean exit = false;
    while (!exit) {
      printMenu();
      var choice = App.scanner.nextLine();
      switch (choice) {
        case "1":
          addInstructor();
          break;
        case "2":
          addPromo();
          break;
        case "3":
          addPromo();
          break;
        default:
          exit = true;
      }
    }
  }

  private void addPromo() {
    System.out.printf("promo name :  ");
    String name = App.scanner.nextLine();
    AdminModel.addPromo(name);
  }

  private void addInstructor() {
    System.out.printf("Instructor name: ");
    String name = App.scanner.nextLine();
    System.out.printf("Instructor email: ");
    String email = App.scanner.nextLine();
    AdminModel.addInstructor(name, email);
    System.out.println("added instructor '" + name + "' successfully!");
  }

}
