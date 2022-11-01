package simplonclone.Controllers;

public class Instructor extends User {
  public Instructor(String name, String email, int id) {
    super(name, email, id);
  }

  public Instructor(Instructor instructor) {
    super(instructor.getName(), instructor.getEmail(), instructor.getId());
  }

  private void printMenu() {
    System.out.println("1- add brief\n 2- assing student to promo\n  (anything else will quit) :");
  }

  public void handler() {
    System.out.println("hello : " + getName());
    printMenu();
  }
}
