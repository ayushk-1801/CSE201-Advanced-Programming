import java.util.ArrayList;
import java.util.Scanner;

public class Props {
    // Static attributes
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static ArrayList<Professor> professorList = new ArrayList<>();
    private static ArrayList<Admin> adminList = new ArrayList<>();
    private static ArrayList<Course> courseList = new ArrayList<>();
    private static ArrayList<Complaint> complaintList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    // Getters and setters
    public static ArrayList<Student> getStudentList() {
        return studentList;
    }

    public static ArrayList<Professor> getProfessorList() {
        return professorList;
    }

    public static ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public static ArrayList<Course> getCourseList() {
        return courseList;
    }

    public static ArrayList<Complaint> getComplaintList() {
        return complaintList;
    }

    // Utility methods
    public static void printHeader(String title) {
        clear();
        System.out.println("============================================================================================================");
        System.out.println("                                                " + title);
        System.out.println("============================================================================================================");
    }

    public static void printFooter() {
        System.out.println("============================================================================================================");
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void introScreen() {
        clear();
        System.out.println("   ___                              __            _     _             _   _               __           _                 ");
        System.out.println("  / __\\___  _   _ _ __ ___  ___    /__\\ ___  __ _(_)___| |_ _ __ __ _| |_(_) ___  _ __   / _\\_   _ ___| |_ ___ _ __ ___  ");
        System.out.println(" / /  / _ \\| | | | '__/ __|/ _ \\  / \\/// _ \\/ _` | / __| __| '__/ _` | __| |/ _ \\  '_ \\  \\ \\| | | / __| __/ _ \\ '_ ` _ \\ ");
        System.out.println("/ /__| (_) | |_| | |  \\__ \\  __/ / _  \\  __/ (_| | \\__ \\ |_| | | (_| | |_| | (_) | | | | _\\ \\ |_| \\__ \\ ||  __/ | | | | |");
        System.out.println("\\____/\\___/ \\__,_|_|  |___/\\___| \\/ \\_/\\___|\\___,|_|___/\\__|_|  \\__,_|\\__|_|\\___/|_| |_| \\__/\\__, |___/\\__\\___|_| |_| |_|");
        System.out.println("                                            |___/                                            |___/                       ");
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    public static void exitScreen() {
        clear();
        System.out.println("\nThank you for using the course registration system. Goodbye!");
        System.exit(0);
    }

    public static void login() {
        while (true) {
            printHeader("Login / Sign Up");
            System.out.println("Select user type:");
            System.out.println("1. Student");
            System.out.println("2. Professor");
            System.out.println("3. Admin");
            System.out.println("4. Sign Up");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    loginUser("Student");
                    break;
                case 2:
                    loginUser("Professor");
                    break;
                case 3:
                    loginUser("Admin");
                    break;
                case 4:
                    signUp();
                    break;
                case 5:
                    exitScreen();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Login methods
    private static void loginUser(String userType) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = null;
        switch (userType) {
            case "Student":
                user = loginStudent(email, password);
                break;
            case "Professor":
                user = loginProfessor(email, password);
                break;
            case "Admin":
                user = loginAdmin(email, password);
                break;
        }

        if (user != null) {
            System.out.println("Login successful!");
            switch (userType) {
                case "Student":
                    studentMenu((Student) user);
                    break;
                case "Professor":
                    professorMenu((Professor) user);
                    break;
                case "Admin":
                    adminMenu((Admin) user);
                    break;
            }
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    public static User loginStudent(String email, String password) {
        for (Student student : studentList) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    public static User loginProfessor(String email, String password) {
        for (Professor professor : professorList) {
            if (professor.getEmail().equals(email) && professor.getPassword().equals(password)) {
                return professor;
            }
        }
        return null;
    }

    public static User loginAdmin(String email, String password) {
        for (Admin admin : adminList) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    private static void signUp() {
        printHeader("Sign Up");
        System.out.println("Select user type:");
        System.out.println("1. Student");
        System.out.println("2. Professor");
        System.out.println("3. Admin");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        switch (choice) {
            case 1:
                Student newStudent = new Student(firstName, lastName, email, password);
                studentList.add(newStudent);
                break;
            case 2:
                Professor newProfessor = new Professor(firstName, lastName, email, password);
                professorList.add(newProfessor);
                break;
            case 3:
                Admin newAdmin = new Admin(firstName, lastName, email, password);
                adminList.add(newAdmin);
                break;
            default:
                System.out.println("Invalid choice. Sign up failed.");
                return;
        }
        System.out.println("Sign up successful!");
    }

    // Menu methods
    public static void studentMenu(Student student) {
        while (true) {
            printHeader("Student Menu");
            System.out.println("1. View available courses");
            System.out.println("2. Add course");
            System.out.println("3. Drop course");
            System.out.println("4. View schedule");
            System.out.println("5. View academic progress");
            System.out.println("6. Submit complaint");
            System.out.println("7. Logout");
            System.out.print("Enter your choice (1-7): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    student.viewAvailableCourses();
                    break;
                case 2:
                    student.registerCourse();
                    break;
                case 3:
                    student.dropCourse();
                    break;
                case 4:
                    student.viewSchedule();
                    break;
                case 5:
                    student.viewAcademicProgress();
                    break;
                case 6:
                    System.out.print("Enter your complaint: ");
                    String complaintText = scanner.nextLine();
                    Complaint complaint = new Complaint(complaintText, student);
                    complaintList.add(complaint);
                    System.out.println("Complaint submitted successfully.");
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            printFooter();
        }
    }

    public static void professorMenu(Professor professor) {
        while (true) {
            printHeader("Professor Menu");
            System.out.println("1. Manage course");
            System.out.println("2. View enrolled students");
            System.out.println("3. Grade students");
            System.out.println("4. Logout");
            System.out.print("Enter your choice (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    manageCourseMenu(professor);
                    break;
                case 2:
                    professor.viewEnrolledStudents();
                    break;
                case 3:
                    professor.gradeStudents();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            printFooter();
        }
    }

    private static void manageCourseMenu(Professor professor) {
        while (true) {
            printHeader("Manage Course Menu");
            System.out.println("1. Edit syllabus");
            System.out.println("2. Edit timings");
            System.out.println("3. Edit credits");
            System.out.println("4. Return to main menu");
            System.out.print("Enter your choice (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    professor.editSyllabus();
                    break;
                case 2:
                    professor.editTimings();
                    break;
                case 3:
                    professor.editCredits();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            printFooter();
        }
    }

    public static void adminMenu(Admin admin) {
        while (true) {
            printHeader("Admin Menu");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Student Record");
            System.out.println("3. Assign Professor to a Course");
            System.out.println("4. Handle Complaint");
            System.out.println("5. Logout");
            System.out.print("Enter your choice (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    manageCoursesCatalog(admin);
                    break;
                case 2:
                    manageStudentRecord(admin);
                    break;
                case 3:
                    admin.assignProfessorToCourse();
                    break;
                case 4:
                    admin.handleComplaints();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            printFooter();
        }
    }

    private static void manageCoursesCatalog(Admin admin) {
        while (true) {
            printHeader("Manage Course Catalog");
            System.out.println("1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. Update Course");
            System.out.println("4. Return to Admin Menu");
            System.out.print("Enter your choice (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    admin.addCourse();
                    break;
                case 2:
                    admin.removeCourse();
                    break;
                case 3:
                    admin.updateCourse();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            printFooter();
        }
    }

    private static void manageStudentRecord(Admin admin) {
        while (true) {
            printHeader("Manage Student Record");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Return to Admin Menu");
            System.out.print("Enter your choice (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    admin.addStudent();
                    break;
                case 2:
                    admin.removeStudent();
                    break;
                case 3:
                    admin.updateStudent();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            printFooter();
        }
    }
}
