import java.util.ArrayList;

interface AdminInterface {
    void addCourse(ArrayList<ArrayList<Course>> courseCatalog, Course course, int semester);
    void removeCourse(ArrayList<ArrayList<Course>> courseCatalog, Course course, int semester);
    void viewStudentGrades(Student student);
    String getUsername();
    String getPassword();
    void setUsername(String username);
    void setPassword(String password);
}

public class Admin implements AdminInterface {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void addCourse(ArrayList<ArrayList<Course>> courseCatalog, Course course, int semester) {
        if (course != null) {
            courseCatalog.get(semester).add(course);
            System.out.println("Course " + course.getCode() + ": " + course.getName() + " has been added successfully.");
        } else {
            System.out.println("Error: Cannot add a null course.");
        }
    }

    @Override
    public void removeCourse(ArrayList<ArrayList<Course>> courseCatalog, Course course, int semester) {
        if (course != null) {
            courseCatalog.get(semester).remove(course);
            System.out.println("Course " + course.getCode() + ": " + course.getName() + " has been removed successfully.");
        } else {
            System.out.println("Error: Cannot remove a null course.");
        }
    }

    @Override
    public void viewStudentGrades(Student student) {
    
    
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
