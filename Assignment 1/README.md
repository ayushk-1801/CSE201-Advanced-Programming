# Course Registration System

This is a Java-based Course Registration System that allows students, professors, and administrators to manage courses, enrollments, and academic records.

## How to Run

1. Ensure you have Java Development Kit (JDK) installed on your system.
2. Compile all Java files in the project directory:
   ```
   javac *.java
   ```
3. Run the Main class:
   ```
   java Main
   ```
4. Follow the on-screen prompts to navigate through the system.

## Assumptions

1. The system is console-based and does not have a graphical user interface.
2. Data is not persisted between runs; it's initialized with some sample data at startup.
3. The academic year is divided into 8 semesters.
4. Students can register for a maximum of 20 credits per semester.
5. Grades are entered manually by professors and are not calculated automatically.
6. The system does not handle concurrent users or transactions.

## OOP Concepts Applied

1. **Encapsulation**:
   - All classes use private fields with public getter and setter methods.
   - Example: The `Student` class encapsulates student-specific data like CGPA and registered courses.

2. **Inheritance**:
   - `Student`, `Professor`, and `Admin` classes inherit from the `User` class.
   - `Admin` extends `AdminUser` which is an abstract class.

3. **Polymorphism**:
   - Method overriding is used in subclasses. For example, `toString()` method is overridden in the `Course` class.
   - Method overloading is used in constructors (e.g., `Student` class has a default constructor and a parameterized constructor).

4. **Abstraction**:
   - Interfaces like `StudentInterface`, `ProfessorInterface`, and `AdminInterface` define abstract methods that are implemented by respective classes.
   - The `AdminUser` abstract class provides a base for the `Admin` class.

5. **Interfaces**:
   - `StudentInterface`, `ProfessorInterface`, and `AdminInterface` define contracts for their respective classes.

6. **Composition**:
   - The `Props` class uses composition to manage collections of `Student`, `Professor`, `Admin`, `Course`, and `Complaint` objects.

7. **Static Members**:
   - The `Props` class uses static fields and methods to maintain global state and utility functions.

8. **ArrayList and Other Collections**:
   - `ArrayList` is used extensively to store collections of objects (e.g., courses, students, professors).

9. **Exception Handling**:
   - Try-catch blocks are used to handle potential exceptions, particularly when parsing user input.

10. **Enums** (not explicitly used, but could be implemented for grades or course types).

This project demonstrates a comprehensive application of core OOP principles in Java, providing a flexible and extensible system for course registration and management.

## Features

- User authentication (login/signup) for students, professors, and admins
- Menu-driven interface for all user types
- Student functionalities:
  - View available courses
  - Course registration with prerequisite checking
  - View schedule
  - Academic progress tracking (SGPA and CGPA calculation)
  - Course dropping
  - Complaint submission
- Professor functionalities:
  - Course management (update syllabus, timings)
  - View enrolled students
  - Grade students
- Administrator functionalities:
  - Course catalog management (add, remove, update courses)
  - Student records management
  - Assign professors to courses
  - Handle student complaints

## Project Structure

- `Main.java`: Entry point of the application
- `Props.java`: Static class for managing global properties and utility methods
- `User.java`: Base class for all user types (not provided, but implied)
- `Student.java`: Represents a student user and their functionalities
- `Professor.java`: Represents a professor user and their functionalities
- `Admin.java`: Represents an admin user and their functionalities
- `Course.java`: Represents a course and its properties
- `Complaint.java`: Represents a student complaint (not provided, but implied)
