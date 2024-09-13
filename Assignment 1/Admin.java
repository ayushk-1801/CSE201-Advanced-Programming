import java.util.ArrayList;
import java.util.Scanner;

interface AdminInterface {
    void manageCoursesCatalog();
    void manageStudentRecords();
    void assignProfessorToCourse();
    void handleComplaints();
}

public class Admin extends User implements AdminInterface {
    private ArrayList<Course> courseCatalog;
    private ArrayList<String> complaints;
    private Scanner scanner;

    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.courseCatalog = new ArrayList<>();
        this.complaints = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void manageCoursesCatalog() {
        while (true) {
            Props.printHeader("Course Catalog Management");
            System.out.println("1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. Update Course");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    removeCourse();
                    break;
                case 3:
                    updateCourse();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
            Props.printFooter();
        }
    }

    public void addCourse() {
        Props.printHeader("Add Course");
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = scanner.nextInt();
        System.out.print("Enter semester: ");
        int semester = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();

        Course newCourse = new Course(courseCode, courseName, credits, instructor, semester);
        courseCatalog.add(newCourse);
        System.out.println("Course added successfully.");
    }

    public void removeCourse() {
        Props.printHeader("Remove Course");
        System.out.print("Enter course code to remove: ");
        String courseCode = scanner.nextLine();

        for (Course course : courseCatalog) {
            if (course.getCourseCode().equals(courseCode)) {
                courseCatalog.remove(course);
                System.out.println("Course removed successfully.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void updateCourse() {
        Props.printHeader("Update Course");
        System.out.print("Enter course code to update: ");
        String courseCode = scanner.nextLine();

        for (Course course : courseCatalog) {
            if (course.getCourseCode().equals(courseCode)) {
                System.out.print("Enter new credits (or -1 to keep current): ");
                int newCredits = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (newCredits != -1) {
                    course.setCredits(newCredits);
                }
                System.out.println("Course updated successfully.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void manageStudentRecords() {
        while (true) {
            Props.printHeader("Student Records Management");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
            Props.printFooter();
        }
    }

    private void addStudent() {
        Props.printHeader("Add Student");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Student newStudent = new Student(firstName, lastName, email, password);
        Props.addStudent(newStudent);
        System.out.println("Student added successfully.");
    }

    private void removeStudent() {
        Props.printHeader("Remove Student");
        System.out.print("Enter student email to remove: ");
        String email = scanner.nextLine();

        for (Student student : Props.getStudents()) {
            if (student.getEmail().equals(email)) {
                Props.removeStudent(student);
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private void updateStudent() {
        Props.printHeader("Update Student");
        System.out.print("Enter student email to update: ");
        String email = scanner.nextLine();

        for (Student student : Props.getStudents()) {
            if (student.getEmail().equals(email)) {
                System.out.print("Enter new first name (or press Enter to keep current): ");
                String newFirstName = scanner.nextLine();
                if (!newFirstName.isEmpty()) {
                    student.setFirstName(newFirstName);
                }
                System.out.print("Enter new last name (or press Enter to keep current): ");
                String newLastName = scanner.nextLine();
                if (!newLastName.isEmpty()) {
                    student.setLastName(newLastName);
                }
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void assignProfessorToCourse() {
        Props.printHeader("Assign Professor to Course");
        System.out.print("Enter professor email: ");
        String professorEmail = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Professor professor = null;
        Course course = null;

        for (Professor p : Props.getProfessors()) {
            if (p.getEmail().equals(professorEmail)) {
                professor = p;
                break;
            }
        }

        for (ArrayList<Course> semesterCourses : Props.getCourses()) {
            for (Course c : semesterCourses) {
                if (c.getCourseCode().equals(courseCode)) {
                    course = c;
                    break;
                }
            }
            if (course != null) {
                break;
            }
        }

        if (professor != null && course != null) {
            course.setAssignedProfessor(professor.getEmail());
            System.out.println("Professor assigned to course successfully.");
        } else {
            System.out.println("Professor or course not found.");
        }
    }

    public void handleComplaints() {
        Props.printHeader("Handle Complaints");
        ArrayList<Complaint> complaints = Props.getComplaints();
        if (complaints.isEmpty()) {
            System.out.println("No complaints to handle.");
            return;
        }

        while (true) {
            System.out.println("Complaints:");
            for (int i = 0; i < complaints.size(); i++) {
                Complaint complaint = complaints.get(i);
                System.out.println((i + 1) + ": " + complaint.getComplaint());
            }

            System.out.print("Enter complaint number to handle (or 0 to exit): ");
            int complaintNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (complaintNumber == 0) {
                break;
            }

            if (complaintNumber > 0 && complaintNumber <= complaints.size()) {
                Complaint selectedComplaint = complaints.get(complaintNumber - 1);
                System.out.println("Selected complaint: " + selectedComplaint);
                System.out.print("Enter new status (Pending/In Progress/Resolved): ");
                String newStatus = scanner.nextLine();
                selectedComplaint.setStatus(newStatus);
                System.out.println("Complaint status updated to: " + newStatus);

                if (newStatus.equalsIgnoreCase("Resolved")) {
                    complaints.remove(complaintNumber - 1);
                    System.out.println("Complaint resolved and removed from the list.");
                }
            } else {
                System.out.println("Invalid complaint number.");
            }

            System.out.println(); // Add a blank line for readability
        }
    }
}
