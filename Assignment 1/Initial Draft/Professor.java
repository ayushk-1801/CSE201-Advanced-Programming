public class Professor extends User {

    private Set<String> courses;

    public Professor() {
        super();
        courses = new HashSet<>();
    }

    public Professor(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        courses = new HashSet<>();
    }

    public void viewEnrolledStudents() {
        // TODO: Implement this method
    }

}
