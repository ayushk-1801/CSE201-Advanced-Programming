import java.util.ArrayList;
import java.util.Scanner;

interface ProfessorInterface {
    ArrayList<Course> getAssignedCourses();
    void setAssignedCourses(ArrayList<Course> assignedCourses);
    void addAssignedCourse(Course course);
    void removeAssignedCourse(Course course);
    void manageCourses();
    void viewEnrolledStudents();
    void gradeStudent();
    void updateCourseDetails(Course course);
    void viewCourseDetails(Course course);
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

    @Override
    public ArrayList<Course> getAssignedCourses() {
        return assignedCourses;
    }

    @Override
    public void setAssignedCourses(ArrayList<Course> assignedCourses) {
        this.assignedCourses = assignedCourses;
    }

    @Override
    public void addAssignedCourse(Course course) {
        this.assignedCourses.add(course);
    }

    @Override
    public void removeAssignedCourse(Course course) {
        this.assignedCourses.remove(course);
    }

    @Override
    public void manageCourses() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Props.printHeader("Manage Courses");
            for (int i = 0; i < assignedCourses.size(); i++) {
                System.out.println((i + 1) + ". " + assignedCourses.get(i));
            }
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter course number to update (0 to exit): ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                break;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice <= assignedCourses.size()) {
                    updateCourseDetails(assignedCourses.get(choice - 1));
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    @Override
    public void viewEnrolledStudents() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Props.printHeader("View Enrolled Students");
            for (int i = 0; i < assignedCourses.size(); i++) {
                System.out.println((i + 1) + ". " + assignedCourses.get(i));
            }
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter course number to view enrolled students (or '0' to return): ");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                break;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice <= assignedCourses.size()) {
                    Course course = assignedCourses.get(choice - 1);
                    ArrayList<Student> enrolledStudents = course.getEnrolledStudents();
                    Props.printHeader("Enrolled Students in " + course.getCourseName());
                    
                    System.out.println("+----+----------------------------------------+");
                    System.out.println("| ID | Student Name                           |");
                    System.out.println("+----+----------------------------------------+");
                    
                    for (int i = 0; i < enrolledStudents.size(); i++) {
                        Student student = enrolledStudents.get(i);
                        System.out.printf("| %-2d | %-40s |\n", 
                            i + 1, 
                            student.getFirstName() + " " + student.getLastName());
                    }
                    
                    System.out.println("+----+----------------------------------------+");
                    Props.printFooter();
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or '0'.");
            }
            Props.printFooter();
        }
    }

    @Override
    public void gradeStudent() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Props.printHeader("Grade Student");
            for (int i = 0; i < assignedCourses.size(); i++) {
                System.out.println((i + 1) + ". " + assignedCourses.get(i));
            }
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter course number to grade students (0 to exit): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= assignedCourses.size()) {
                Course course = assignedCourses.get(choice - 1);
                ArrayList<Student> enrolledStudents = course.getEnrolledStudents();
                Props.printHeader("Grades for " + course.getCourseName());
                
                System.out.println("+----+----------------------------------------+-------+");
                System.out.println("| ID | Student Name                           | Grade |");
                System.out.println("+----+----------------------------------------+-------+");
                
                for (int i = 0; i < enrolledStudents.size(); i++) {
                    Student student = enrolledStudents.get(i);
                    String grade = course.getGrade(student);
                    if (grade == null) grade = "N/A";
                    System.out.printf("| %-2d | %-40s | %-5s |\n", 
                        i + 1, 
                        student.getFirstName() + " " + student.getLastName(), 
                        grade);
                }
                
                System.out.println("+----+----------------------------------------+-------+");
                
                while (true) {
                    System.out.print("Enter student ID to update grade (0 to go back): ");
                    int studentChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (studentChoice == 0) break;

                    if (studentChoice > 0 && studentChoice <= enrolledStudents.size()) {
                        Student student = enrolledStudents.get(studentChoice - 1);
                        System.out.print("Enter new grade for " + student.getFirstName() + " " + student.getLastName() + ": ");
                        String grade = scanner.nextLine();
                        course.setGrade(student, grade);
                        System.out.println("Grade updated successfully.");
                        
                        boolean allCoursesGraded = true;
                        for (Course c : student.getRegisteredCourses()) {
                            if (c.getGrade(student) == null) {
                                allCoursesGraded = false;
                                break;
                            }
                        }
                        
                        if (allCoursesGraded) {
                            int currentSemester = student.getSemester();
                            student.setSemester(currentSemester + 1);
                            System.out.println("All courses graded. Student " + student.getFirstName() + " " + student.getLastName() + " moved to semester " + (currentSemester + 1));
                        }
                    } else {
                        System.out.println("Invalid student ID. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            Props.printFooter();
        }
    }

    @Override
    public void updateCourseDetails(Course course) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Props.printHeader("Updating " + course.getCourseName());
            System.out.println("1. Update Syllabus");
            System.out.println("2. Update Timings");
            System.out.println("0. Back to Course List");
            System.out.print("Enter your choice (0-2): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    return;
                case 1:
                    System.out.print("Enter new syllabus: ");
                    String syllabus = scanner.nextLine();
                    course.setSyllabus(syllabus);
                    System.out.println("Syllabus updated.");
                    break;
                case 2:
                    System.out.print("Enter new timings: ");
                    String timings = scanner.nextLine();
                    course.setTimings(timings);
                    System.out.println("Timings updated.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            Props.printFooter();
        }
    }

    @Override
    public void viewCourseDetails(Course course) {
        System.out.println("Course Details:");
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Syllabus: " + course.getSyllabus());
        System.out.println("Timings: " + course.getTimings());
    }
}
