package simplonclone.Controllers;

import java.util.LinkedHashMap;

import simplonclone.App;
import simplonclone.Models.AdminModel;

public class Administrator extends User {
  private LinkedHashMap<String, MenuHandler> menu;

  @FunctionalInterface
  private interface MenuHandler {
    public void run();
  }

  public Administrator(String name, String email, int id) {
    super(name, email, id);
    fillMenu();
  }

  public Administrator(Administrator admin) {
    super(admin.getName(), admin.getEmail(), admin.getId());
    fillMenu();
  }

  private void fillMenu() {
    menu = new LinkedHashMap<>();
    menu.put("add instructor", this::addInstructor);
    menu.put("add promo", this::addPromo);
    menu.put("add student", this::addStudent);
    menu.put("list instructors", this::listInstructors);
    menu.put("list promos", this::listPromos);
    menu.put("list students", this::liststudents);
  }

  private void printMenu() {
    int i = 0;
    System.out.println("");
    for (String key : menu.keySet()) {
      System.out.println(++i + "- " + key);
    }
    System.out.println("(anything else will quit)");
  }

  public void handler() {
    System.out.println("hello : " + this.getName());
    while (true) {
      printMenu();
      System.out.printf("choose an option: ");
      try {
        var choice = App.scanner.nextInt();
        App.scanner.nextLine();
        for (var entry : menu.entrySet()) {
          if (choice > menu.size() && choice < 1)
            return;
          if (entry.getKey().equals(menu.keySet().toArray()[choice - 1])) {
            entry.getValue().run();
            break;
          }
        }
      } catch (Exception e) {
        System.out.println("err : " + e.getMessage());
        return;
      }
    }
  }

  private void addInstructor() {
    System.out.printf("Instructor name: ");
    String name = App.scanner.nextLine();
    System.out.printf("Instructor email: ");
    String email = App.scanner.nextLine();
    if (AdminModel.addInstructor(name, email))
      System.out.println("added instructor '" + name + "' successfully!");
    else
      System.out.println("failed to add instructor '" + name + "'!");
  }

  private void addPromo() {
    System.out.printf("promo name :  ");
    String name = App.scanner.nextLine();
    listInstructors();
    System.out.printf("please choose an instructor_id :  ");
    int instrutorId = App.scanner.nextInt();
    App.scanner.nextLine();
    if (AdminModel.addPromo(name, instrutorId))
      System.out.println("added promo '" + name + "' successfully!");
    else
      System.out.println("failed to add promo '" + name + "'!");
  }

  private void addStudent() {
    System.out.printf("Student name: ");
    String name = App.scanner.nextLine();
    System.out.printf("Student email: ");
    String email = App.scanner.nextLine();
    this.listPromos();
    System.out.printf("please choose a promoId: ");
    int promo = App.scanner.nextInt();
    if (AdminModel.addStudent(name, email, promo))
      System.out.println("added student '" + name + "' successfully!");
    else
      System.out.println("failed to add student '" + name + "'!");
  }

  private void listInstructors() {
    System.out.println("Instructors:");
    AdminModel.getAllInstructors().forEach(System.out::println);
  }

  private void listPromos() {
    System.out.println("Promos:");
    AdminModel.getAllPromos().forEach(System.out::println);
  }

  private void liststudents() {
    System.out.println("Students:");
    AdminModel.getAllStudents().forEach(System.out::println);
  }
}
