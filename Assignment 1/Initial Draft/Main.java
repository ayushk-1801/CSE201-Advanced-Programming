import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static ArrayList<ArrayList<Course>> courses;
    private static ArrayList<Student> students;
    private static ArrayList<Admin> admins;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        clearScreen();
        System.out.println("Welcome to the University Course Registration System!");

        // Initialize courses, students, and admins
        courses = initializeCourses();
        students = initializeStudents();
        admins = initializeAdmins();

        // Start the authentication process
        authenticateUser();
    }

    private static void authenticateUser() {
        while (true) {
            clearScreen();
            System.out.println("\n+-------------------------------------------+");
            System.out.println("|        Please select your role:           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. Student                                |");
            System.out.println("| 2. Admin                                  |");
            System.out.println("| 3. Exit                                   |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    authenticateStudent();
                    break;
                case 2:
                    authenticateAdmin();
                    break;
                case 3:
                    clearScreen();
                    System.out.println("+-------------------------------------------+");
                    System.out.println("| Thank you for using the University Course |");
                    System.out.println("| Registration System. Goodbye!             |");
                    System.out.println("+-------------------------------------------+");
                    System.exit(0);
                default:
                    clearScreen();
                    System.out.println("+-------------------------------------------+");
                    System.out.println("| Invalid choice. Please try again.         |");
                    System.out.println("+-------------------------------------------+");
            }
        }
    }

    private static void authenticateStudent() {
        clearScreen();
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|           Student Authentication          |");
        System.out.println("+-------------------------------------------+");
        System.out.print("| Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("| Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("+-------------------------------------------+");

        for (Student student : students) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                clearScreen();
                System.out.println("| Authentication successful.               |");
                System.out.println("| Welcome, " + String.format("%-34s", student.getFirstName() + "!") + "|");
                System.out.println("+-------------------------------------------+");
                studentMenu(student);
                return;
            }
        }
        clearScreen();
        System.out.println("| Authentication failed.                    |");
        System.out.println("| Invalid email or password.                |");
        System.out.println("+-------------------------------------------+");
    }

    private static void authenticateAdmin() {
        clearScreen();
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|            Admin Authentication           |");
        System.out.println("+-------------------------------------------+");
        System.out.print("| Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("| Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("+-------------------------------------------+");

        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                clearScreen();
                System.out.println("| Authentication successful.               |");
                System.out.println("| Welcome, Admin!                          |");
                System.out.println("+-------------------------------------------+");
                adminMenu(admin);
                return;
            }
        }
        clearScreen();
        System.out.println("| Authentication failed.                    |");
        System.out.println("| Invalid username or password.             |");
        System.out.println("+-------------------------------------------+");
    }

    private static void studentMenu(Student student) {
        while (true) {
            clearScreen();
            System.out.println("\n+-------------------------------------------+");
            System.out.println("|             Student Menu                  |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. View Available Courses                 |");
            System.out.println("| 2. Register for a Course                  |");
            System.out.println("| 3. Drop a Course                          |");
            System.out.println("| 4. View Registered Courses                |");
            System.out.println("| 5. View Grades                            |");
            System.out.println("| 6. Logout                                 |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableCourses();
                    break;
                case 2:
                    registerForCourse(student);
                    break;
                case 3:
                    dropCourse(student);
                    break;
                case 4:
                    viewRegisteredCourses(student);
                    break;
                case 5:
                    student.viewGrades();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu(Admin admin) {
        while (true) {
            clearScreen();
            System.out.println("\n+-------------------------------------------+");
            System.out.println("|               Admin Menu                  |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1. Add Course                             |");
            System.out.println("| 2. Remove Course                          |");
            System.out.println("| 3. View All Courses                       |");
            System.out.println("| 4. View Student Grades                    |");
            System.out.println("| 5. Logout                                 |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCourse(admin);
                    break;
                case 2:
                    removeCourse(admin);
                    break;
                // case 3:
                //     viewAllCourses();
                //     break;
                // case 4:
                //     viewStudentGrades(admin);
                    // break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ArrayList<ArrayList<Course>> initializeCourses() {
        ArrayList<ArrayList<Course>> courses = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            courses.add(new ArrayList<>());
        }
        
        courses.get(1).add(new Course("CSE101", "Introduction to Programming", 4, "Pankaj Jalote"));
        courses.get(1).add(new Course("MTH100", "Linear Algebra", 4, "Subhajit Ghoosechowdhary"));
        courses.get(1).add(new Course("DES102", "Introduction to Human-Computer Interaction", 4, "Rajiv Ratn Shah"));
        courses.get(1).add(new Course("COM101", "Communication Skills", 4, "Payal Mukherjee"));
        courses.get(1).add(new Course("ECE111", "Digital Circuits", 4, "Pravesh Biyani"));

        courses.get(2).add(new Course("CSE102", "Data Structures and Algorithms", 4, "Ojaswa Sharma"));
        courses.get(2).add(new Course("MTH201", "Probability and Statistics", 4, "Subhajit Ghoosechowdhary"));
        courses.get(2).add(new Course("CSE112", "Computer Organization", 4, "Sujay Deb"));
        courses.get(2).add(new Course("ECO223", "Money and Banking", 4, "Kirti Kanjilal"));
        courses.get(2).add(new Course("CSE140", "Introduction to Intelligent Systems", 4, "Jainender Shukla"));

        courses.get(3).add(new Course("CSE231", "Operating Systems", 4, "Raghava Mutharaju"));
        courses.get(3).add(new Course("CSE201", "Advanced Programming", 4, "Sambuddho Chakraborty"));
        courses.get(3).add(new Course("MTH210", "Discrete Structures", 4, "Ashish Kumar Pandey"));
        courses.get(3).add(new Course("MTH203", "Multivariable Calculus", 4, "Sarthok Sirkar"));
        courses.get(3).add(new Course("ECE250", "Signals and Systems", 4, "Anubha Gupta"));

        return courses;
    }

    public static ArrayList<Student> initializeStudents() {
        ArrayList<Student> students = new ArrayList<>();
        
        students.add(new Student("Arhan", "Jain", "arhanjain@iiitd.ac.in", "arhanjain"));
        students.add(new Student("Dev", "Sharma", "devsharma@iiitd.ac.in", "devsharma"));
        students.add(new Student("Arnesh", "Batra", "arneshbatra@iiitd.ac.in", "arneshbatra"));
        students.add(new Student("Anant", "Singhal", "anantsinghal@iiitd.ac.in", "anantsinghal"));
        students.add(new Student("Anushk", "Kumar", "anushkkumar@iiitd.ac.in", "anushkkumar"));
        students.add(new Student("Ayush", "Kitnawat", "ayushkitnawat@iiitd.ac.in", "ayushkitnawat"));
        
        return students;
    }

    public static ArrayList<Admin> initializeAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        
        admins.add(new Admin("admin1", "password1"));
        admins.add(new Admin("admin2", "password2"));
        
        return admins;
    }

    private static void viewAvailableCourses() {
        clearScreen();
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|           Available Courses               |");
        System.out.println("+-------------------------------------------+");
        for (int i = 1; i < courses.size(); i++) {
            System.out.println("Semester " + i + ":");
            for (Course course : courses.get(i)) {
                System.out.println(course.getCode() + ": " + course.getName());
            }
            System.out.println();
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void registerForCourse(Student student) {
        clearScreen();
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|           Course Registration             |");
        System.out.println("+-------------------------------------------+");
        System.out.print("Enter the course code: ");
        String courseCode = scanner.nextLine();

        for (ArrayList<Course> semesterCourses : courses) {
            for (Course course : semesterCourses) {
                if (course.getCode().equalsIgnoreCase(courseCode)) {
                    student.addCourse(course.getCode());
                    System.out.println("Successfully registered for " + course.getName());
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    return;
                }
            }
        }

        System.out.println("Course not found. Please try again.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void dropCourse(Student student) {
        clearScreen();
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|              Drop Course                  |");
        System.out.println("+-------------------------------------------+");
        System.out.print("Enter the course code to drop: ");
        String courseCode = scanner.nextLine();

        if (student.getCourses().contains(courseCode)) {
            student.dropCourse(courseCode);
            System.out.println("Successfully dropped the course.");
        } else {
            System.out.println("You are not registered for this course.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void viewRegisteredCourses(Student student) {
        clearScreen();
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|           Registered Courses              |");
        System.out.println("+-------------------------------------------+");
        Set<String> registeredCourses = student.getCourses();
        if (registeredCourses.isEmpty()) {
            System.out.println("You are not registered for any courses.");
        } else {
            for (String courseCode : registeredCourses) {
                System.out.println(courseCode);
            }
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void addCourse(Admin admin) {
        clearScreen();
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|               Add Course                  |");
        System.out.println("+-------------------------------------------+");
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        System.out.print("Enter course credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();
        System.out.print("Enter semester (1-3): ");
        int semester = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Course newCourse = new Course(code, name, credits, instructor);
        // admin.addCourse(courses, newCourse, semester);

        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void removeCourse(Admin admin) {
        clearScreen();
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|             Remove Course                 |");
        System.out.println("+-------------------------------------------+");
        System.out.print("Enter course code to remove: ");
        String code = scanner.nextLine();
        System.out.print("Enter semester (1-3): ");
        int semester = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Course course : courses.get(semester)) {
            if (course.getCode().equalsIgnoreCase(code)) {
                // admin.removeCourse(courses, course, semester);
                System.out.println("Course removed successfully.");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                return;
            }
        }

        System.out.println("Course not found. Please try again.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
