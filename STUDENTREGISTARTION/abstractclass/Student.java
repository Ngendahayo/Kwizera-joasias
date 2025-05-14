package abstractclass;

import java.time.LocalDate;

public abstract class Student {
    protected String id;
    protected String fullName;
    protected LocalDate dateOfBirth;
    protected String nationality;
    protected String department;
    protected String combination;
    protected double marks;
    protected String schoolName;
    protected LocalDate registrationDate;
    protected double registrationFeePaid;
    protected boolean isForeignStudent;

    // Constructor
    public Student(String id, String fullName, LocalDate dateOfBirth, String nationality, String department, String combination, double marks, LocalDate registrationDate, double registrationFeePaid, boolean isForeignStudent) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.department = department;
        this.combination = combination;
        this.marks = marks;
        this.registrationDate = registrationDate;
        this.registrationFeePaid = registrationFeePaid;
        this.isForeignStudent = isForeignStudent;
    }

    // Abstract methods
    public abstract boolean validateAge();
    public abstract boolean validateMarks();
    public abstract boolean validateCombination();
    public abstract double calculateRegistration();

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCombination() { return combination; }
    public void setCombination(String combination) { this.combination = combination; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public double getRegistrationFeePaid() { return registrationFeePaid; }
    public void setRegistrationFeePaid(double registrationFeePaid) { this.registrationFeePaid = registrationFeePaid; }

    public boolean isForeignStudent() { return isForeignStudent; }
    public void setForeignStudent(boolean foreignStudent) { isForeignStudent = foreignStudent; }
} 