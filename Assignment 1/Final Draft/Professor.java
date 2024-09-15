interface ProfessorInterface {
    void manageCourse(Course course);
    void viewEnrolledStudents();
    void gradeStudents();
}

public class Professor extends User implements ProfessorInterface {
    private Course assignedCourse;

    // Constructor

    // Implement ProfessorInterface methods
    @Override
    public void manageCourse(Course course) {
        // Implementation
    }

    @Override
    public void viewEnrolledStudents() {
        // Implementation
    }

    @Override
    public void gradeStudents() {
        // Implementation
    }

    // Getters and setters for assignedCourse
}
