import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        // Use the initializeCourses method here
        ArrayList<ArrayList<Course>> courses = initializeCourses();

        // Print all courses
        for (int semester = 1; semester < courses.size(); semester++) {
            System.out.println("Semester " + semester + " Courses:");
            for (Course course : courses.get(semester)) {
                System.out.println("  " + course.getCode() + ": " + course.getName() + " (" + course.getCredits() + " credits)");
            }
            System.out.println();
        }
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
}
