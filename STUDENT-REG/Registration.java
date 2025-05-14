package studentregistration;

import java.util.ArrayList;
import java.util.List;

public class Registration {
    private List<Student> students;
    private List<Course> courses;

    public Registration() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // Polymorphism: Method can accept any type of Course
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void displayAllCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            // Polymorphism: Each course type will display its details differently
            course.displayCourseDetails();
            System.out.println("------------------------");
        }
    }

    public void displayAllStudents() {
        System.out.println("\nRegistered Students:");
        for (Student student : students) {
            student.displayInfo();
            System.out.println("------------------------");
        }
    }
} 