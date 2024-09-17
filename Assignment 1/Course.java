import java.util.ArrayList;
import java.util.HashMap;

public class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private int semester;
    private String assignedProfessor;
    private String syllabus;
    private String timings;
    private ArrayList<Course> prerequisites;
    private ArrayList<Student> enrolledStudents;
    private HashMap<Student, String> grades;

    public Course(String courseCode, String courseName, int credits, String instructor, int semester) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.assignedProfessor = instructor;
        this.semester = semester;
        this.prerequisites = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.grades = new HashMap<>();
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

    public String getAssignedProfessor() {
        return assignedProfessor;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public String getTimings() {
        return timings;
    }

    public String getGrade(Student student) {
        return grades.get(student);
    }

    public ArrayList<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setAssignedProfessor(String assignedProfessor) {
        this.assignedProfessor = assignedProfessor;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setGrade(Student student, String grade) {
        grades.put(student, grade);
    }

    public boolean hasPrerequisites() {
        return !prerequisites.isEmpty();
    }

    public void addPrerequisite(Course prerequisite) {
        prerequisites.add(prerequisite);
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void dropStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-52s | %-6d | %-9d | %-16s |",
                courseCode, courseName, credits, semester,
                assignedProfessor != null ? assignedProfessor.split(" ")[0] : "N/A");
    }
}
