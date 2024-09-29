# Props
- public static
- Attributes:
    - student list
    - professor list
    - admint list
    - courses list
    - complaint list
- Methods:
    - getters & setters
    - print header
    - print footer
    - clear screen
    - intro screen
    - exit screen
    - login/signup
        - student
        - professor
        - admin
    - menu
        - student
        - professor
        - admin

# USER
- Attributes:
    - first name
    - last name
    - email
    - password
- Methods:
    - getters & setters

## StudentInterface
- Methods:
    - getters & setters
    - view available courses
    - add course
    - drop course
    - view schedule
    - view grades
    - submit complaint

### Student
- inherit USER
- Attributes:
    - current semester
- implements StudentInterface

## ProfessorInterface
- Methods:
    - getters & setters
    - manage course
        - syllabus
        - timings
        - credits
    - view enrolled students
    - grade students

### Professor
- inherit USER
- Attributes:
    - course assigned
- implements ProfessorInterface

# AdminInterface
- Methods:
    - getters & setters
    - manage course catalog
        - add course
        - remove course
        - update course
    - manage student record
        - add student
        - remove student
        - update student
    - assign professor to a course
    - handle complaint

## Admin
- Attributes:
    - email
    - password
- implements AdminInterface

## Course
- Attributes:
    - course name
    - course code
    - credits
    - semester
    - pre-requisite
    - assigned professor
    - enrolled students
- Methods:
    - getters & setters

## Complaint
- Attributes:
    - complaint
    - email
    - status
- Methods:
    - getters & setters
