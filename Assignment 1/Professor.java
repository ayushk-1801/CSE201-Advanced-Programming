import java.util.ArrayList;
import java.util.Scanner;

interface ProfessorInterface {
    void manageCourses();
    void viewEnrolledStudents();
}

public class Professor extends User implements ProfessorInterface {
    private ArrayList<Course> assignedCourses;

    public Professor() {
        super();
        this.assignedCourses = new ArrayList<>();
    }

    public Professor(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.assignedCourses = new ArrayList<>();
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public void manageCourses() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Props.printHeader("Manage Courses");
            for (int i = 0; i < assignedCourses.size(); i++) {
                System.out.println((i + 1) + ". " + assignedCourses.get(i));
            }
            System.out.println("0. Back to main menu");
            System.out.print("Enter course number to update (0 to exit): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= assignedCourses.size()) {
                updateCourseDetails(assignedCourses.get(choice - 1));
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            Props.printFooter();
        }
    }

    private void updateCourseDetails(Course course) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Props.printHeader("Updating " + course.getCourseName());
            System.out.println("1. Update syllabus");
            System.out.println("2. Update timings");
            System.out.println("3. Update credits");
            System.out.println("0. Back to course list");
            System.out.print("Enter your choice (0-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    return;
                case 1:
                    System.out.print("Enter new syllabus: ");
                    String syllabus = scanner.nextLine();
                    // TODO: Implement syllabus update in Course class
                    System.out.println("Syllabus updated.");
                    break;
                case 2:
                    System.out.print("Enter new timings: ");
                    String timings = scanner.nextLine();
                    // TODO: Implement timings update in Course class
                    System.out.println("Timings updated.");
                    break;
                case 3:
                    System.out.print("Enter new credits: ");
                    int credits = scanner.nextInt();
                    course.setCredits(credits);
                    System.out.println("Credits updated.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            Props.printFooter();
        }
    }

    public void viewEnrolledStudents() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Props.printHeader("View Enrolled Students");
            for (int i = 0; i < assignedCourses.size(); i++) {
                System.out.println((i + 1) + ". " + assignedCourses.get(i));
            }
            System.out.println("0. Back to main menu");
            System.out.print("Enter course number to view enrolled students (0 to exit): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= assignedCourses.size()) {
                Course course = assignedCourses.get(choice - 1);
                ArrayList<Student> enrolledStudents = course.getEnrolledStudents();
                Props.printHeader("Enrolled students in " + course.getCourseName());
                for (int i = 0; i < enrolledStudents.size(); i++) {
                    Student student = enrolledStudents.get(i);
                    System.out.println((i + 1) + ". " + student.getFirstName() + " " + student.getLastName());
                }
                Props.printFooter();
            } else {
                System.out.println("Invalid choice. Please try again.");
                Props.printFooter();
            }
        }
    }
}
