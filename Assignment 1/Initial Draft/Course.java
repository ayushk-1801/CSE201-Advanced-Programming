
import java.util.Set;

public class Course {

    private String name;
    private String code;
    private String professor;
    private Set<String> prerequisites;
    private Set<Student> students;
    private int studentLimit;
    private int credits;
    private String syllabus;
    private String classTimings;

    public Course() {
    }

    public Course(String code, String name, int credits, String professor) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.professor = professor;
    }

    public Course(String name, String code, String professor, Set<String> prerequisites, Set<Student> students,
            int studentLimit, int credits, String syllabus, String classTimings) {
        this.name = name;
        this.code = code;
        this.professor = professor;
        this.prerequisites = prerequisites;
        this.students = students;
        this.studentLimit = studentLimit;
        this.credits = credits;
        this.syllabus = syllabus;
        this.classTimings = classTimings;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getProfessor() {
        return professor;
    }

    public Set<String> getPrerequisites() {
        return prerequisites;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public int getStudentLimit() {
        return studentLimit;
    }

    public int getCredits() {
        return credits;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public String getClassTimings() {
        return classTimings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setPrerequisites(Set<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void setStudentLimit(int studentLimit) {
        this.studentLimit = studentLimit;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public void setClassTimings(String classTimings) {
        this.classTimings = classTimings;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}
