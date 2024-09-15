interface StudentInterface {
    void viewAvailableCourses();
    void addCourse(Course course);
    void dropCourse(Course course);
    void viewSchedule();
    void viewGrades();
    void submitComplaint(String complaint);
}

public class Student extends User implements StudentInterface {
    private int currentSemester;

    // Constructor

    // Implement StudentInterface methods
    @Override
    public void viewAvailableCourses() {
        // Implementation
    }

    @Override
    public void addCourse(Course course) {
        // Implementation
    }

    @Override
    public void dropCourse(Course course) {
        // Implementation
    }

    @Override
    public void viewSchedule() {
        // Implementation
    }

    @Override
    public void viewGrades() {
        // Implementation
    }

    @Override
    public void submitComplaint(String complaint) {
        // Implementation
    }

    // Getters and setters for currentSemester
}
