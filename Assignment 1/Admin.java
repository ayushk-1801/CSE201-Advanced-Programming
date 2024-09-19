import java.util.ArrayList;
import java.util.Scanner;

abstract class AdminUser {
    protected String email;
    protected String password;

    public AdminUser() {}
    public AdminUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

interface AdminInterface {
    void manageCoursesCatalog();
    void manageStudentRecords();
    void assignProfessorToCourse();
    void handleComplaints();
}

public class Admin extends AdminUser implements AdminInterface {
    private ArrayList<Course> courseCatalog;
    private Scanner scanner;

    public Admin() {
        super();
        this.courseCatalog = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public Admin(String email, String password) {
        super(email, password);
        this.courseCatalog = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public ArrayList<Course> getCourseCatalog() {
        return courseCatalog;
    }

    public void setCourseCatalog(ArrayList<Course> courseCatalog) {
        this.courseCatalog = courseCatalog;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
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
            scanner.nextLine();

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
        System.out.print("Enter semester: ");
        int semester = scanner.nextInt();
        System.out.print("Enter credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();

        Course newCourse = new Course(courseCode, courseName, credits, instructor, semester);
        Props.getCourses().get(semester).add(newCourse);
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
                System.out.print("Enter new credits: ");
                int newCredits = scanner.nextInt();
                scanner.nextLine();
                course.setCredits(newCredits);
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
            scanner.nextLine();

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
        System.out.print("Enter student email: ");
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
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        for (Student student : Props.getStudents()) {
            if (student.getEmail().equals(email)) {
                System.out.print("Enter new first name: ");
                String newFirstName = scanner.nextLine();
                student.setFirstName(newFirstName);
                System.out.print("Enter new last name: ");
                String newLastName = scanner.nextLine();
                student.setLastName(newLastName);
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

        for (ArrayList<Course> semwisecourses : Props.getCourses()) {
            for (Course c : semwisecourses) {
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
            professor.addAssignedCourse(course);
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
            System.out.println("1. View all complaints");
            System.out.println("2. Filter complaints");
            System.out.println("0. Return to main menu");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                break;
            } else if (choice.equals("1")) {
                displayComplaints(complaints);
            } else if (choice.equals("2")) {
                filterComplaints(complaints);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayComplaints(ArrayList<Complaint> complaints) {
        while (true) {
            System.out.println("+----+------------------------------------------------------------------+----------+");
            System.out.println("| ID | Complaint                                                        | Status   |");
            System.out.println("+----+------------------------------------------------------------------+----------+");
            for (int i = 0; i < complaints.size(); i++) {
                Complaint complaint = complaints.get(i);
                System.out.printf("| %-2d | %-80s | %-8s |\n", 
                    i + 1, 
                    truncate(complaint.getComplaint(), 80), 
                    complaint.getStatus());
            }
            System.out.println("+----+------------------------------------------------------------------+----------+");
            System.out.print("Enter complaint number to handle (or '0' to return): ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                break;
            }

            handleComplaint(complaints, input);
        }
    }

    private void filterComplaints(ArrayList<Complaint> complaints) {
        System.out.print("Enter status to filter (Pending/Resolved): ");
        String filterStatus = scanner.nextLine().trim().toLowerCase();
        ArrayList<Complaint> filteredComplaints = new ArrayList<>();

        for (Complaint complaint : complaints) {
            if (complaint.getStatus().toLowerCase().equals(filterStatus)) {
                filteredComplaints.add(complaint);
            }
        }

        if (filteredComplaints.isEmpty()) {
            System.out.println("No complaints found with status: " + filterStatus);
        } else {
            displayComplaints(filteredComplaints);
        }
    }

    private void handleComplaint(ArrayList<Complaint> complaints, String input) {
        int complaintNumber;
        try {
            complaintNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or '0'.");
            return;
        }
        if (complaintNumber > 0 && complaintNumber <= complaints.size()) {
            Complaint selectedComplaint = complaints.get(complaintNumber - 1);
            System.out.println("Selected complaint: " + selectedComplaint.getComplaint());
            System.out.print("Enter status (Pending/Resolved): ");
            String status = scanner.nextLine();
            selectedComplaint.setStatus(status);
            System.out.println("Complaint status updated to: " + status);
        } else {
            System.out.println("Invalid complaint number.");
        }

        System.out.println();
    }

    private String truncate(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }
}
