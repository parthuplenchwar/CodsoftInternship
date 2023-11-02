import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int availableSlots;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Course Code: " + code + ", Title: " + title + ", Description: " + description +
                ", Capacity: " + capacity + ", Available Slots: " + availableSlots + ", Schedule: " + schedule;
    }
}

class Student {
    int id;
    String name;
    List<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Registered Courses: " + registeredCourses;
    }
}

public class CourseRegistrationSystem {
    List<Course> courseDatabase;
    List<Student> studentDatabase;

    public CourseRegistrationSystem() {
        this.courseDatabase = new ArrayList<>();
        this.studentDatabase = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courseDatabase.add(course);
    }

    public void addStudent(Student student) {
        studentDatabase.add(student);
    }

    public void displayCourses() {
        for (Course course : courseDatabase) {
            System.out.println(course);
        }
    }

    public void registerStudentForCourse(Student student, Course course) {
        if (student.registeredCourses.contains(course)) {
            System.out.println("Student is already registered for this course.");
            return;
        }

        if (course.availableSlots > 0) {
            student.registeredCourses.add(course);
            course.availableSlots--;
            System.out.println("Student successfully registered for the course.");
        } else {
            System.out.println("Course is already full. Cannot register.");
        }
    }

    public void dropCourse(Student student, Course course) {
        if (student.registeredCourses.contains(course)) {
            student.registeredCourses.remove(course);
            course.availableSlots++;
            System.out.println("Student successfully dropped the course.");
        } else {
            System.out.println("Student is not registered for this course.");
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        Course c1 = new Course("CS101", "Introduction to Computer Science", "Basic programming concepts", 30, "Mon-Wed-Fri");
        Course c2 = new Course("MATH201", "Calculus I", "Single-variable calculus", 25, "Tue-Thu");

        Student s1 = new Student(1, "John Doe");
        Student s2 = new Student(2, "Jane Smith");

        system.addCourse(c1);
        system.addCourse(c2);

        system.addStudent(s1);
        system.addStudent(s2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Courses");
            System.out.println("2. Register Student for Course");
            System.out.println("3. Drop Course for Student");
            System.out.println("4. Quit");
            System.out.println("\nEnter your Choice");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;
                case 2:
                    System.out.println("Enter student ID:");
                    int studentId = scanner.nextInt();
                    System.out.println("Enter course code:");
                    String courseCode = scanner.next();
                    Student student = null;
                    Course course = null;
                    for (Student s : system.studentDatabase) {
                        if (s.id == studentId) {
                            student = s;
                            break;
                        }
                    }
                    for (Course c : system.courseDatabase) {
                        if (c.code.equals(courseCode)) {
                            course = c;
                            break;
                        }
                    }
                    if (student != null && course != null) {
                        system.registerStudentForCourse(student, course);
                    } else {
                        System.out.println("Invalid student ID or course code.");
                    }
                    break;
                case 3:
                    System.out.println("Enter student ID:");
                    int studentIdDrop = scanner.nextInt();
                    System.out.println("Enter course code:");
                    String courseCodeDrop = scanner.next();
                    Student studentDrop = null;
                    Course courseDrop = null;
                    for (Student s : system.studentDatabase) {
                        if (s.id == studentIdDrop) {
                            studentDrop = s;
                            break;
                        }
                    }
                    for (Course c : system.courseDatabase) {
                        if (c.code.equals(courseCodeDrop)) {
                            courseDrop = c;
                            break;
                        }
                    }
                    if (studentDrop != null && courseDrop != null) {
                        system.dropCourse(studentDrop, courseDrop);
                    } else {
                        System.out.println("Invalid student ID or course code.");
                    }
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
