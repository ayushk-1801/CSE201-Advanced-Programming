import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.time.LocalDate;

interface StudentInterface {
    ArrayList<Course> getRegisteredCourses();

    void setRegisteredCourses(ArrayList<Course> registeredCourses);

    double getCgpa();

    void setCgpa(double cgpa);

    int getTotalCredits();

    void setTotalCredits(int totalCredits);

    int getSemester();

    void setSemester(int semester);

    void viewAvailableCourses();

    void registerCourse();

    void viewSchedule();

    void viewAcademicProgress();

    void dropCourse();

    void submitComplaint();

    void addCompletedCourse(Course course);

    void submitFeedback();
}

public class Student extends User implements StudentInterface {
    private ArrayList<Course> registeredCourses;
    private ArrayList<Course> completedCourses;
    private double cgpa;
    private int totalCredits;
    private Scanner scanner;
    private int semester;

    public Student() {
        super();
        this.registeredCourses = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
        this.cgpa = 0.0;
        this.totalCredits = 0;
        this.scanner = new Scanner(System.in);
        this.semester = 1;
    }

    public Student(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.completedCourses = new ArrayList<>();
        this.registeredCourses = new ArrayList<>();
        this.cgpa = 0.0;
        this.totalCredits = 0;
        this.scanner = new Scanner(System.in);
        this.semester = 1;
    }

    @Override
    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    @Override
    public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    @Override
    public double getCgpa() {
        return cgpa;
    }

    @Override
    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public int getTotalCredits() {
        return totalCredits;
    }

    @Override
    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    @Override
    public int getSemester() {
        return semester;
    }

    @Override
    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public void viewAvailableCourses() {
        Props.printHeader("Available Courses");

        System.out.println("+------------+------------------------------------------------------+--------+-----------+------------------+------------------+");
        System.out.println("| Course Code| Course Name                                          | Credits| Semester  | Professor        | Timings          |");
        System.out.println("+------------+------------------------------------------------------+--------+-----------+------------------+------------------+");

        ArrayList<Course> currentSemesterCourses = Props.getCourses().get(semester);
        for (Course course : currentSemesterCourses) {
            System.out.printf("| %-10s | %-52s | %-6d | %-9d | %-16s | %-16s |\n",
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getCredits(),
                    course.getSemester(),
                    course.getAssignedProfessor().split(" ")[0],
                    course.getTimings());
        }

        System.out.println("+------------+------------------------------------------------------+--------+-----------+------------------+------------------+");
        Props.printFooter();
    }

    @Override
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
                    if (course.isFull()) {
                        System.out.println("Cannot register. Course is full.");
                        Props.printFooter();
                        return;
                    }
                    course.enrollStudent(this);
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
        ArrayList<Course> prerequisites = course.getPrerequisites();
        if (prerequisites == null || prerequisites.isEmpty()) {
            return true;
        }

        for (Course prerequisite : prerequisites) {
            boolean found = false;
            for (Course registeredCourse : registeredCourses) {
                if (registeredCourse.getCourseCode().equals(prerequisite.getCourseCode())) {
                    String grade = registeredCourse.getGrade(this);
                    if (grade == null || grade.equals("F")) {
                        return false;
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void viewSchedule() {
        Props.printHeader("Your Schedule");
        for (Course course : registeredCourses) {
            System.out.println(course);
        }
        Props.printFooter();
    }

    @Override
    public void viewAcademicProgress() {
        Props.printHeader("Academic Progress");

        double calculatedCGPA = calculateCGPA();
        this.cgpa = calculatedCGPA;

        System.out.println("CGPA: " + String.format("%.2f", cgpa));
        System.out.println("Total Credits: " + totalCredits);
        System.out.println("Current Semester: " + semester);

        System.out.println("\nCourses and Grades:");
        System.out.println("+----------------+-------------------------------------+-------+");
        System.out.println("| Course Code    | Course Name                         | Grade |");
        System.out.println("+----------------+-------------------------------------+-------+");

        for (Course course : registeredCourses) {
            String grade = course.getGrade(this);
            if (grade == null) grade = "N/A";
            System.out.printf("| %-14s | %-35s | %-5s |\n",
                    course.getCourseCode(),
                    course.getCourseName(),
                    grade);
        }

        System.out.println("+----------------+-------------------------------------+-------+");

        double sgpa = calculateSGPA();
        System.out.printf("\nCurrent Semester GPA: %.2f\n", sgpa);

        Props.printFooter();
    }

    private double calculateSGPA() {
        double totalGradePoints = 0;
        int totalCredits = 0;

        for (Course course : registeredCourses) {
            String grade = course.getGrade(this);
            if (grade != null && !grade.equals("N/A")) {
                double gradePoint = convertGradeToPoint(grade);
                totalGradePoints += gradePoint * course.getCredits();
                totalCredits += course.getCredits();
            }
        }

        return totalCredits > 0 ? totalGradePoints / totalCredits : 0;
    }

    private double calculateCGPA() {
        double totalGradePoints = 0;
        int totalCredits = 0;

        for (Course course : registeredCourses) {
            String grade = course.getGrade(this);
            if (grade != null && !grade.equals("N/A")) {
                double gradePoint = convertGradeToPoint(grade);
                totalGradePoints += gradePoint * course.getCredits();
                totalCredits += course.getCredits();
            }
        }

        return totalCredits > 0 ? totalGradePoints / totalCredits : 0;
    }

    private double convertGradeToPoint(String grade) {
        switch (grade) {
            case "A+":
                return 10.0;
            case "A":
                return 9.0;
            case "A-":
                return 8.0;
            case "B+":
                return 7.0;
            case "B":
                return 6.0;
            case "B-":
                return 5.0;
            case "C+":
                return 4.0;
            case "C":
                return 3.0;
            case "D":
                return 2.0;
            case "E":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0;
        }
    }

    @Override
    public void dropCourse() {
        Props.printHeader("Drop Course");
        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();

        try {
            Iterator<Course> iterator = registeredCourses.iterator();
            while (iterator.hasNext()) {
                Course course = iterator.next();
                if (course.getCourseCode().equals(courseCode)) {
                    if (hasDropDeadlinePassed(course)) {
                        throw new DropDeadlinePassedException("Cannot drop course. Drop deadline has passed.");
                    }
                    iterator.remove();
                    totalCredits -= course.getCredits();
                    System.out.println("Successfully dropped " + course.getCourseName());
                    Props.printFooter();
                    return;
                }
            }
            System.out.println("Course not found in your registered courses.");
        } catch (DropDeadlinePassedException err) {
            System.out.println(err.getMessage());
        }
        Props.printFooter();
    }

    private boolean hasDropDeadlinePassed(Course course) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dropDeadlineDate = course.getDropDeadline();
        return currentDate.isAfter(dropDeadlineDate);
    }

    @Override
    public void submitComplaint() {
        Props.printHeader("Submit Complaint");
        System.out.print("Enter your complaint: ");
        String complaint = scanner.nextLine();
        Props.addComplaint(new Complaint(complaint, this.getEmail()));
        System.out.println("Your complaint has been submitted.");
        Props.printFooter();
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    @Override
    public void addCompletedCourse(Course course) {
        if (completedCourses == null) {
            completedCourses = new ArrayList<>();
        }
        completedCourses.add(course);
    }

    @Override
    public void submitFeedback() {
        Scanner scanner = new Scanner(System.in);
        Props.printHeader("Submit Feedback");

        System.out.print("Enter course code to give feedback: ");
        String courseCode = scanner.nextLine();

        Course completedCourse = null;
        for (Course course : this.completedCourses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode) && course.getGrade(this) != null) {
                completedCourse = course;
                break;
            }
        }

        if (completedCourse == null) {
            System.out.println("Course not found or not completed.");
            Props.printFooter();
            return;
        }

        System.out.print("Enter numeric rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter textual feedback: ");
        String comment = scanner.nextLine();

        Feedback<Integer> numericFeedback = new Feedback<>(rating, this.getEmail());
        Feedback<String> textualFeedback = new Feedback<>(comment, this.getEmail());

        completedCourse.addFeedback(numericFeedback);
        completedCourse.addFeedback(textualFeedback);

        System.out.println("Feedback submitted successfully.");
        Props.printFooter();
    }
}
