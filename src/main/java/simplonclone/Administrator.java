package simplonclone;

public class Administrator extends User {
  public Administrator(String name, String email) {
    super();
    this.setName(name);
    this.setEmail(email);
  }

  public static Administrator find(String name, String email) {
    return new Administrator("name", "email");
  }
}
