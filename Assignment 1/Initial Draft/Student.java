import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

interface StudentInterface {

    public Set<String> getCourses();
    public void addCourse(String course);
    public void dropCourse(String course);
    public void viewGrades();

}

public class Student extends User implements StudentInterface {

    private Set<String> registeredCourses;
    private Map<String, String> grades;

    public Student() {
        super();
        registeredCourses = new HashSet<>();
        grades = new HashMap<>(); 
    }

    public Student(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        registeredCourses = new HashSet<>();
        grades = new HashMap<>(); 
    }

    @Override
    public Set<String> getCourses() {
        return registeredCourses;
    }

    public void addCourse(String course) {
        registeredCourses.add(course);
    }

    public void dropCourse(String course) {
        registeredCourses.remove(course);
    }

    public void viewGrades() {
        System.out.println("Grades for " + this.getFirstName() + " " + this.getLastName() + ":");
        for (String course : registeredCourses) {
            System.out.println(course + ": " + grades.get(course));
        }
    }

}