import java.util.ArrayList;
import java.util.Scanner;

public class Props {
    public static ArrayList<Admin> admins = new ArrayList<Admin>();
    public static ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Professor> professors = new ArrayList<Professor>();
    public static ArrayList<ArrayList<Course>> courses = new ArrayList<ArrayList<Course>>();
    public static ArrayList<Complaint> complaints = new ArrayList<Complaint>();

    private static Scanner scanner = new Scanner(System.in);

    public static ArrayList<ArrayList<Course>> getCourses() {
        return courses;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static ArrayList<Professor> getProfessors() {
        return professors;
    }   

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<Complaint> getComplaints() {
        return complaints;
    }

    public static ArrayList<String> getComplaintsList() {
        ArrayList<String> complaintsList = new ArrayList<>();
        for (Complaint complaint : complaints) {
            complaintsList.add(complaint.toString());
        }
        return complaintsList;
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static void removeStudent(Student student) {
        students.remove(student);
    }

    public static void addProfessor(Professor professor) {
        professors.add(professor);
    }

    public static void removeProfessor(Professor professor) {
        professors.remove(professor);
    }

    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public static void addCourse(Course course) {
        courses.get(course.getSemester()).add(course);
    }

    public static void addComplaint(Complaint complaint) {
        complaints.add(complaint);
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

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

    public static void viewCourses() {
        printHeader("View Courses");
        for (ArrayList<Course> semesterCourses : courses) {
            for (Course course : semesterCourses) {
                System.out.println(course);
            }
        }
        printFooter();
    }

    // Screen methods
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
            printHeader("Login/Signup");
            System.out.println("Select an option:");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            clear();

            if (choice == 1) {
                printHeader("Login");   
                System.out.println("Select user type:");
                System.out.println("1. Student");
                System.out.println("2. Professor");
                System.out.println("3. Admin");
                System.out.print("Enter your choice (1-3): ");
                int user = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                switch (user) {
                    case 1:
                        studentLogin(username, password);
                        break;
                    case 2:
                        professorLogin(username, password);
                        break;
                    case 3:
                        adminLogin(username, password);
                        break;
                    default:
                        System.out.println("Invalid user type. Please try again.");
                }
            } else if (choice == 2) {
                signup();
            } else if (choice == 3) {
                exitScreen();
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
        }
    }

    public static void signup() {
        printHeader("Signup");
        System.out.println("Select user type:");
        System.out.println("1. Student");
        System.out.println("2. Professor");
        System.out.print("Enter your choice (1-2): ");
        int userType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userType == 1) {
            students.add(new Student(firstName, lastName, email, password));
            System.out.println("Congratulations! Your student account has been created successfully. You may now proceed to log in.");
        } else if (userType == 2) {
            professors.add(new Professor(firstName, lastName, email, password));
            System.out.println("Congratulations! Your professor account has been created successfully. You may now proceed to log in.");
        } else {
            System.out.println("Invalid user type. Signup failed.");
        }
    }

    public static void studentLogin(String username, String password) {
        for (Student student : students) {
            if (student.getEmail().equals(username) && student.getPassword().equals(password)) {
                studentMenu(student);
                return;
            }
        }
        System.out.println("Invalid credentials. Please try again.");
    }

    public static void studentMenu(Student student) {
        while (true) {
            printHeader("Student Menu");
            System.out.println("1. View available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. View schedule");
            System.out.println("5. View academic progress");
            System.out.println("6. Submit a complaint");
            System.out.println("7. Logout");
            System.out.print("Enter your choice (1-7): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
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
                    student.submitComplaint();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void professorLogin(String username, String password) {
        for (Professor professor : professors) {
            if (professor.getEmail().equals(username) && professor.getPassword().equals(password)) {
                professorMenu(professor);
                return;
            }
        }
        System.out.println("Invalid credentials. Please try again.");
    }

    public static void professorMenu(Professor professor) {
        while (true) {
            printHeader("Professor Menu");
            System.out.println("1. Manage courses");
            System.out.println("2. View enrolled students");
            System.out.println("3. Logout");
            System.out.print("Enter your choice (1-3): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    professor.manageCourses();
                    break;
                case 2:
                    professor.viewEnrolledStudents();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void adminLogin(String username, String password) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(username) && admin.getPassword().equals(password)) {
                adminMenu(admin);
                return;
            }
        }
        System.out.println("Invalid credentials. Please try again.");
    }

    public static void adminMenu(Admin admin) {
        while (true) {
            printHeader("Admin Menu");
            System.out.println("1. Add course");
            System.out.println("2. Remove course");
            System.out.println("3. View courses");
            System.out.println("4. Manage student records");
            System.out.println("5. Assign professor to course");
            System.out.println("6. Handle complaints");
            System.out.println("7. Logout");
            System.out.print("Enter your choice (1-7): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    admin.addCourse();
                    break;
                case 2:
                    admin.removeCourse();
                    break;
                case 3:
                    viewCourses();
                    break;
                case 4:
                    admin.manageStudentRecords();
                    break;
                case 5:
                    admin.assignProfessorToCourse();
                    break;
                case 6:
                    admin.handleComplaints();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}