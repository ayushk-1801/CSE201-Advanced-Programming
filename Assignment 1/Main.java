import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
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

        Props.courses.get(1).add(new Course("CSE101", "Introduction to Programming", 4, "Pankaj Jalote", 1));
        Props.courses.get(1).add(new Course("MTH100", "Linear Algebra", 4, "Subhajit Ghoosechowdhary", 1));
        Props.courses.get(1).add(new Course("DES102", "Introduction to Human-Computer Interaction", 4, "Rajiv Ratn Shah", 1));
        Props.courses.get(1).add(new Course("COM101", "Communication Skills", 4, "Payal Mukherjee", 1));
        Props.courses.get(1).add(new Course("ECE111", "Digital Circuits", 4, "Pravesh Biyani", 1));

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

        Props.introScreen();
        Props.login();
    }
}
