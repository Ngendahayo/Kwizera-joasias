package studentregistration;

public class Main {
    public static void main(String[] args) {
        // Create registration system
        Registration registration = new Registration();

        // Create courses
        ComputerScienceCourse javaCourse = new ComputerScienceCourse(
            "CS101", "Introduction to Java Programming", 3, 
            "Dr. Smith", "Java", "Lab 101"
        );

        ComputerScienceCourse pythonCourse = new ComputerScienceCourse(
            "CS102", "Python Programming", 3,
            "Dr. Johnson", "Python", "Lab 102"
        );

        // Add courses to registration system
        registration.addCourse(javaCourse);
        registration.addCourse(pythonCourse);

        // Create and register students
        Student student1 = new Student("S001", "John Doe", 20, "john@email.com", "Computer Science");
        Student student2 = new Student("S002", "Jane Smith", 19, "jane@email.com", "Computer Science");

        registration.registerStudent(student1);
        registration.registerStudent(student2);

        // Display all information
        System.out.println("Student Registration System Demo");
        System.out.println("===============================");
        
        registration.displayAllCourses();
        registration.displayAllStudents();
    }
} 