import java.util.Set;

public class Student extends User {

    private Set<String> courses;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public Set<String> getCourses() {
        return courses;
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public void dropCourse(String course) {
        courses.remove(course);
    }




}
