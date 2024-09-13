import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private int semester;
    private ArrayList<Course> prerequisites;
    private String assignedProfessor;
    private ArrayList<Student> enrolledStudents;

    public Course(String courseCode, String courseName, int credits, String instructor, int semester) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.assignedProfessor = instructor;
        this.semester = semester;
        this.prerequisites = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public int getSemester() {
        return semester;
    }

    public boolean hasPrerequisites() {
        return !prerequisites.isEmpty();
    }

    public void addPrerequisite(Course prerequisite) {
        prerequisites.add(prerequisite);
    }

    public String getAssignedProfessor() {
        return assignedProfessor;
    }

    public void setAssignedProfessor(String assignedProfessor) {
        this.assignedProfessor = assignedProfessor;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void dropStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-52s | %-6d | %-9d | %-16s |",
                courseCode, courseName, credits, semester,
                assignedProfessor != null ? assignedProfessor.split(" ")[0] : "N/A");
    }
}
