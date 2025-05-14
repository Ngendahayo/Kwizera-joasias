package studentregistration;

public class ComputerScienceCourse extends Course {
    private String programmingLanguage;
    private String labRoom;

    public ComputerScienceCourse(String courseId, String courseName, int credits, 
                                String instructor, String programmingLanguage, String labRoom) {
        super(courseId, courseName, credits, instructor);
        this.programmingLanguage = programmingLanguage;
        this.labRoom = labRoom;
    }

    @Override
    public void displayCourseDetails() {
        System.out.println("Computer Science Course Details:");
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Credits: " + credits);
        System.out.println("Instructor: " + instructor);
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("Lab Room: " + labRoom);
    }

    // Additional methods specific to Computer Science courses
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getLabRoom() {
        return labRoom;
    }
} 