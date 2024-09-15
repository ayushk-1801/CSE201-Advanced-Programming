interface AdminInterface {
    void manageCoursesCatalog();
    void manageStudentRecord();
    void assignProfessorToCourse(Professor professor, Course course);
    void handleComplaint(Complaint complaint);
}

public class Admin implements AdminInterface {
    private String email;
    private String password;

    // Constructor

    // Implement AdminInterface methods
    @Override
    public void manageCoursesCatalog() {
        // Implementation
    }

    @Override
    public void manageStudentRecord() {
        // Implementation
    }

    @Override
    public void assignProfessorToCourse(Professor professor, Course course) {
        // Implementation
    }

    @Override
    public void handleComplaint(Complaint complaint) {
        // Implementation
    }

    // Getters and setters
}
