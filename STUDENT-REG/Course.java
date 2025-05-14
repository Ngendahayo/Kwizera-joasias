package studentregistration;

public abstract class Course {
    protected String courseId;
    protected String courseName;
    protected int credits;
    protected String instructor;

    public Course(String courseId, String courseName, int credits, String instructor) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.instructor = instructor;
    }

    // Abstract method that must be implemented by subclasses
    public abstract void displayCourseDetails();

    // Concrete methods
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public String getInstructor() {
        return instructor;
    }
} 