import java.util.ArrayList;
import java.util.Scanner;

interface TeachingAssistantInterface {
    void viewEnrolledStudents();
    void gradeStudent();
}

public class TeachingAssistant extends Student {
    private Course assignedCourse;

    public TeachingAssistant(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public TeachingAssistant(String firstName, String lastName, String email, String password, Course course) {
        super(firstName, lastName, email, password);
        this.assignedCourse = course;
    }

    public Course getCourse() {
        return assignedCourse;
    }

    public void setCourse(Course course) {
        this.assignedCourse = course;
    }

    public void viewEnrolledStudents() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> enrolledStudents = assignedCourse.getEnrolledStudents();
        Props.printHeader("Enrolled Students in " + assignedCourse.getCourseName());

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
    }


    public void gradeStudent() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> enrolledStudents = assignedCourse.getEnrolledStudents();
        Props.printHeader("Grades for " + assignedCourse.getCourseName());

        System.out.println("+----+----------------------------------------+-------+");
        System.out.println("| ID | Student Name                           | Grade |");
        System.out.println("+----+----------------------------------------+-------+");

        for (int i = 0; i < enrolledStudents.size(); i++) {
            Student student = enrolledStudents.get(i);
            String grade = assignedCourse.getGrade(student);
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
                assignedCourse.setGrade(student, grade);
                student.addCompletedCourse(this.assignedCourse);
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
        Props.printFooter();
    }
}