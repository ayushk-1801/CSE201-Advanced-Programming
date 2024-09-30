import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

public class Course {
    private LocalDate dropDeadline;
    private String courseCode;
    private String courseName;
    private int credits;
    private int semester;
    private String assignedProfessor;
    private String syllabus;
    private String timings;
    private ArrayList<Course> prerequisites;
    private ArrayList<Student> enrolledStudents;
    private ArrayList<TeachingAssistant> teachingAssistants;
    private HashMap<Student, String> grades;
    private ArrayList<Feedback<?>> feedbackList = new ArrayList<>();
    private int maxStudents;
    private int currentStudents;

    public Course(String courseCode, String courseName, int credits, String instructor, int semester) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.assignedProfessor = instructor;
        this.semester = semester;
        this.prerequisites = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.teachingAssistants = new ArrayList<>();
        this.grades = new HashMap<>();
        this.dropDeadline = LocalDate.of(2024, 9, 3);
        this.maxStudents = 1;
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

    public LocalDate getDropDeadline() {
        return dropDeadline;
    }

    public ArrayList<Feedback<?>> getFeedbackList() {
        return feedbackList;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public ArrayList<TeachingAssistant> getTeachingAssistants() {
        return teachingAssistants;
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

    public void setDropDeadline(LocalDate dropDeadline) {
        this.dropDeadline = dropDeadline;
    }

    public boolean hasPrerequisites() {
        return !prerequisites.isEmpty();
    }

    public void addPrerequisite(Course prerequisite) {
        prerequisites.add(prerequisite);
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
        currentStudents++;
    }

    public void dropStudent(Student student) {
        enrolledStudents.remove(student);
        currentStudents--;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void addFeedback(Feedback<?> feedback) {
        feedbackList.add(feedback);
    }

    public void addTeachingAssistant(TeachingAssistant ta) {
        teachingAssistants.add(ta);
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public void setCurrentStudents(int currentStudents) {
        this.currentStudents = currentStudents;
    }

    public boolean isFull() {
        return currentStudents >= maxStudents;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-52s | %-6d | %-9d | %-16s |",
                courseCode, courseName, credits, semester,
                assignedProfessor != null ? assignedProfessor.split(" ")[0] : "N/A");
    }
}