import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

interface StudentInterface {
    void viewAvailableCourses();
    void registerCourse();
    void viewSchedule();
    void viewAcademicProgress();
    void dropCourse();
    void submitComplaint();
}

public class Student extends User implements StudentInterface {
    private ArrayList<Course> registeredCourses;
    private double cgpa;
    private int totalCredits;
    private Scanner scanner;

    public Student() {
        super();
        this.registeredCourses = new ArrayList<>();
        this.cgpa = 0.0;
        this.totalCredits = 0;
        this.scanner = new Scanner(System.in);
    }
    
    public Student(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.registeredCourses = new ArrayList<>();
        this.cgpa = 0.0;
        this.totalCredits = 0;
        this.scanner = new Scanner(System.in);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private void printHeader(String title) {
        clearScreen();
        System.out.println("============================================================================================");
        System.out.println("                                         " + title);
        System.out.println("============================================================================================");
    }

    private void printFooter() {
        System.out.println("============================================================================================");
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
    public void viewAvailableCourses() {
        Props.printHeader("Available Courses");
        System.out.print("Enter semester (1-8): ");
        int semester = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("+------------+------------------------------------------------------+--------+-----------+------------------+");
        System.out.println("| Course Code| Course Name                                          | Credits| Semester  | Professor        |");
        System.out.println("+------------+------------------------------------------------------+--------+-----------+------------------+");

        for (ArrayList<Course> semesterCourses : Props.getCourses()) {
            for (Course course : semesterCourses) {
                if (course.getSemester() == semester) {
                    System.out.printf("| %-10s | %-52s | %-6d | %-9d | %-16s |\n",
                        course.getCourseCode(),
                        course.getCourseName(),
                        course.getCredits(),
                        course.getSemester(),
                        course.getAssignedProfessor().split(" ")[0]);
                }
            }
        }

        System.out.println("+------------+------------------------------------------------------+--------+-----------+------------------+");
        Props.printFooter();
    }

    public void registerCourse() {
        Props.printHeader("Register Course");
        System.out.print("Enter course code to register: ");
        String courseCode = scanner.nextLine();

        for (ArrayList<Course> semesterCourses : Props.getCourses()) {
            for (Course course : semesterCourses) {
                if (course.getCourseCode().equals(courseCode)) {
                    if (totalCredits + course.getCredits() > 20) {
                        System.out.println("Cannot register. Exceeds credit limit of 20.");
                        Props.printFooter();
                        return;
                    }
                    if (course.hasPrerequisites() && !meetsPrerequisites(course)) {
                        System.out.println("Cannot register. Prerequisites not met.");
                        Props.printFooter();
                        return;
                    }
                    registeredCourses.add(course);
                    totalCredits += course.getCredits();
                    System.out.println("Successfully registered for " + course.getCourseName());
                    Props.printFooter();
                    return;
                }
            }
        }
        System.out.println("Course not found.");
        Props.printFooter();
    }

    private boolean meetsPrerequisites(Course course) {
        // Implement prerequisite checking logic
        return true; // Placeholder
    }

    public void viewSchedule() {
        Props.printHeader("Your Schedule");
        for (Course course : registeredCourses) {
            System.out.println(course);
        }
        Props.printFooter();
    }

    public void viewAcademicProgress() {
        Props.printHeader("Academic Progress");
        System.out.println("CGPA: " + cgpa);
        System.out.println("Total Credits: " + totalCredits);
        Props.printFooter();
    }

    public void dropCourse() {
        Props.printHeader("Drop Course");
        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();

        Iterator<Course> iterator = registeredCourses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getCourseCode().equals(courseCode)) {
                iterator.remove();
                totalCredits -= course.getCredits();
                System.out.println("Successfully dropped " + course.getCourseName());
                Props.printFooter();
                return;
            }
        }
        System.out.println("Course not found in your registered courses.");
        Props.printFooter();
    }

    public void submitComplaint() {
        Props.printHeader("Submit Complaint");
        System.out.print("Enter your complaint: ");
        String complaint = scanner.nextLine();
        Props.addComplaint(new Complaint(complaint, this.getEmail()));
        System.out.println("Your complaint has been submitted.");
        Props.printFooter();
    }
}
