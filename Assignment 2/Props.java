import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Props {
    public static ArrayList<Admin> admins = new ArrayList<Admin>();
    public static ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<TeachingAssistant> teachingAssistants = new ArrayList<TeachingAssistant>();
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

    public static ArrayList<TeachingAssistant> getTeachingAssistants() {
        return teachingAssistants;
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

    public static void addTeachingAssistant(TeachingAssistant teachingAssistant) {
        teachingAssistants.add(teachingAssistant);
    }

    public static void removeTeachingAssistant(TeachingAssistant teachingAssistant) {
        teachingAssistants.remove(teachingAssistant);
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
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
    }

    public static void printHeader(String title) {
        clear();
        System.out.println(
                "============================================================================================================");
        System.out.println("                                                " + title);
        System.out.println(
                "============================================================================================================");
    }

    public static void printFooter() {
        System.out.println(
                "============================================================================================================");
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

    public static void introScreen() {
        clear();
        System.out.println(
                "   ___                              __            _     _             _   _               __           _                 ");
        System.out.println(
                "  / __\\___  _   _ _ __ ___  ___    /__\\ ___  __ _(_)___| |_ _ __ __ _| |_(_) ___  _ __   / _\\_   _ ___| |_ ___ _ __ ___  ");
        System.out.println(
                " / /  / _ \\| | | | '__/ __|/ _ \\  / \\/// _ \\/ _` | / __| __| '__/ _` | __| |/ _ \\  '_ \\  \\ \\| | | / __| __/ _ \\ '_ ` _ \\ ");
        System.out.println(
                "/ /__| (_) | |_| | |  \\__ \\  __/ / _  \\  __/ (_| | \\__ \\ |_| | | (_| | |_| | (_) | | | | _\\ \\ |_| \\__ \\ ||  __/ | | | | |");
        System.out.println(
                "\\____/\\___/ \\__,_|_|  |___/\\___| \\/ \\_/\\___|\\___,|_|___/\\__|_|  \\__,_|\\__|_|\\___/|_| |_| \\__/\\__, |___/\\__\\___|_| |_| |_|");
        System.out.println(
                "                                            |___/                                            |___/                       ");
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
            clear();
            printHeader("Login/Signup");
            System.out.println("Select an option:");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (0-2): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                clear();
                printHeader("Login");
                System.out.println("Select user type:");
                System.out.println("1. Student");
                System.out.println("2. Teaching Assistant");
                System.out.println("3. Professor");
                System.out.println("4. Admin");
                System.out.println("0. Back");
                System.out.print("Enter your choice (0-4): ");
                int user = scanner.nextInt();
                scanner.nextLine();

                if (user == 0) {
                    continue;
                }

                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                try {
                    switch (user) {
                        case 1:
                            studentLogin(username, password);
                            break;
                        case 2:
                            teachingAssistantLogin(username, password);
                            break;
                        case 3:
                            professorLogin(username, password);
                            break;
                        case 4:
                            adminLogin(username, password);
                            break;
                        default:
                            System.out.println("Invalid user type. Please try again.");
                    }
                } catch (InvalidLoginException e) {
                    System.out.println(e.getMessage());
                }
            } else if (choice == 2) {
                signup();
            } else if (choice == 0) {
                exitScreen();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void signup() {
        printHeader("Signup");
        System.out.println("Select user type:");
        System.out.println("1. Student");
        System.out.println("2. Teaching Assistant");
        System.out.println("3. Professor");
        System.out.println("0. Back");
        System.out.print("Enter your choice (0-3): ");
        int user = scanner.nextInt();
        scanner.nextLine();

        if (user == 0) {
            return;
        }

        if (user != 1 && user != 2) {
            System.out.println("Invalid user type. Signup failed.");
            return;
        }

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (user == 1) {
            students.add(new Student(firstName, lastName, email, password));
            System.out.println(
                    "Congratulations! Your student account has been created successfully. You may now proceed to log in.");
        } else if (user == 2) {
            students.add(new TeachingAssistant(firstName, lastName, email, password, null));
            teachingAssistants.add(new TeachingAssistant(firstName, lastName, email, password, null));
            System.out.println(
                    "Congratulations! Your teaching assistant account has been created successfully. You may now proceed to log in.");
        } else {
            professors.add(new Professor(firstName, lastName, email, password));
            System.out.println(
                    "Congratulations! Your professor account has been created successfully. You may now proceed to log in.");
        }
    }

    public static void studentLogin(String username, String password) throws InvalidLoginException {
        for (Student student : students) {
            if (student.getEmail().equals(username) && student.getPassword().equals(password)) {
                studentMenu(student);
                return;
            }
        }
        throw new InvalidLoginException("Invalid credentials. Please try again.");
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
            System.out.println("7. Submit feedback");
            System.out.println("0. Logout");
            System.out.print("Enter your choice (0-7): ");

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
                    student.submitFeedback();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void teachingAssistantLogin(String username, String password) throws InvalidLoginException {
        for (TeachingAssistant teachingAssistant : teachingAssistants) {
            if (teachingAssistant.getEmail().equals(username) && teachingAssistant.getPassword().equals(password)) {
                teachingAssistantMenu(teachingAssistant);
                return;
            }
        }
        throw new InvalidLoginException("Invalid credentials. Please try again.");
    }

    public static void teachingAssistantMenu(TeachingAssistant teachingAssistant) {
        while (true) {
            printHeader("Teaching Assistant Menu");
            System.out.println("1. View enrolled students");
            System.out.println("2. View/Update student grade");
            System.out.println("0. Logout");
            System.out.print("Enter your choice (0-2): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    teachingAssistant.viewEnrolledStudents();
                    break;
                case 2:
                    teachingAssistant.gradeStudent();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void professorLogin(String username, String password) throws InvalidLoginException {
        for (Professor professor : professors) {
            if (professor.getEmail().equals(username) && professor.getPassword().equals(password)) {
                professorMenu(professor);
                return;
            }
        }
        throw new InvalidLoginException("Invalid credentials. Please try again.");
    }

    public static void professorMenu(Professor professor) {
        while (true) {
            printHeader("Professor Menu");
            System.out.println("1. Manage courses");
            System.out.println("2. View enrolled students");
            System.out.println("3. Grade student");
            System.out.println("4. View feedback");
            System.out.println("0. Logout");
            System.out.print("Enter your choice (0-4): ");

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
                    professor.gradeStudent();
                    break;
                case 4:
                    professor.viewCourseFeedback();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void adminLogin(String username, String password) throws InvalidLoginException {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(username) && admin.getPassword().equals(password)) {
                adminMenu(admin);
                return;
            }
        }
        throw new InvalidLoginException("Invalid credentials. Please try again.");
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
            System.out.println("7. View feedback");
            System.out.println("8. Assign TA to course");
            System.out.println("0. Logout");
            System.out.print("Enter your choice (0-6): ");

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
                    admin.viewFeedback();
                    break;
                case 8:
                    admin.assignTeachingAssistant();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void initializeData() {
        Props.courses = new ArrayList<ArrayList<Course>>();
        for (int i = 0; i < 8; i++) {
            Props.courses.add(new ArrayList<Course>());
        }
        Props.students = new ArrayList<Student>();
        Props.professors = new ArrayList<Professor>();
        Props.admins = new ArrayList<Admin>();

        Admin admin = new Admin("admin@iiitd.ac.in", "methhead");
        Props.addAdmin(admin);

        Professor prof1 = new Professor("Sambuddho", "Chakravarty", "sambuddho@iiitd.ac.in", "sambuddho123");
        Professor prof2 = new Professor("Raghava", "Mutharaju", "raghava@iiitd.ac.in", "raghava456");
        Props.addProfessor(prof1);
        Props.addProfessor(prof2);

        Student stud1 = new Student("Arhan", "Jain", "arhan@iiitd.ac.in", "arhan123");
        Student stud2 = new Student("Arnesh", "Batra", "arnesh@iiitd.ac.in", "arnesh123");
        Student stud3 = new Student("Dev", "Sharma", "dev@iiitd.ac.in", "dev123");
        Props.addStudent(stud1);
        Props.addStudent(stud2);
        Props.addStudent(stud3);

        Course course1 = new Course("CSE101", "Introduction to Programming", 4, "Pankaj Jalote", 1);
        Course course2 = new Course("MTH100", "Linear Algebra", 4, "Subhajit Ghoosechowdhary", 1);
        Course course3 = new Course("DES102", "Introduction to Human-Computer Interaction", 4, "Rajiv Ratn Shah", 1);
        Course course4 = new Course("COM101", "Communication Skills", 4, "Payal Mukherjee", 1);
        Course course5 = new Course("ECE111", "Digital Circuits", 4, "Pravesh Biyani", 1);

        TeachingAssistant ta1 = new TeachingAssistant("Anushk", "Kumar", "anushk@iiitd.ac.in", "anushk123", course1);
        TeachingAssistant ta2 = new TeachingAssistant("Ayush", "Kitnawat", "ayush@iiitd.ac.in", "ayush123", course2);
        TeachingAssistant ta3 = new TeachingAssistant("Anant", "Gyan", "anant@iiitd.ac.in", "anant123", course3);
        Props.addStudent(ta1);
        Props.addStudent(ta2);
        Props.addStudent(ta3);
        Props.addTeachingAssistant(ta1);
        Props.addTeachingAssistant(ta2);
        Props.addTeachingAssistant(ta3);

        Props.courses.get(1).add(course1);
        Props.courses.get(1).add(course2);
        Props.courses.get(1).add(course3);
        Props.courses.get(1).add(course4);
        Props.courses.get(1).add(course5);

        Props.courses.get(2).add(new Course("CSE102", "Data Structures and Algorithms", 4, "Ojaswa Sharma", 2));
        Props.courses.get(2).add(new Course("MTH201", "Probability and Statistics", 4, "Subhajit Ghoosechowdhary", 2));
        Props.courses.get(2).add(new Course("CSE112", "Computer Organization", 4, "Sujay Deb", 2));
        Props.courses.get(2).add(new Course("ECO223", "Money and Banking", 4, "Kirti Kanjilal", 2));
        Props.courses.get(2).add(new Course("CSE140", "Introduction to Intelligent Systems", 4, "Jainender Shukla", 2));

        Props.courses.get(3).add(new Course("CSE231", "Operating Systems", 4, "Raghava Mutharaju", 3));
        Props.courses.get(3).add(new Course("CSE201", "Advanced Programming", 4, "Sambuddho Chakravarty", 3));
        Props.courses.get(3).add(new Course("MTH210", "Discrete Structures", 4, "Ashish Kumar Pandey", 3));
        Props.courses.get(3).add(new Course("MTH203", "Multivariable Calculus", 4, "Sarthok Sirkar", 3));
        Props.courses.get(3).add(new Course("ECE250", "Signals and Systems", 4, "Anubha Gupta", 3));

        Props.courses.get(2).get(0).setPrerequisites(new ArrayList<Course>(Arrays.asList(Props.courses.get(1).get(0))));
    }
}