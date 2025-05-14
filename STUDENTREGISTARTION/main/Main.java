package main;

import abstractclass.Student;
import softwareengineering.SoftwareEngineeringStudent;
import networking.NetworkingStudent;
import informationmanagement.InformationManagementStudent;
import foreignstudent.ForeignStudent;
import util.ValidationUtils;
import util.DateUtils;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Student> registeredStudents = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    registerNewStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayDepartmentInfo();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the Student Registration System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Student Registration System ===");
        System.out.println("1. Register New Student");
        System.out.println("2. View All Registered Students");
        System.out.println("3. Search Student");
        System.out.println("4. Department Information");
        System.out.println("5. Exit");
        System.out.println("=================================");
    }

    private static void displayDepartmentMenu() {
        System.out.println("\n=== Select Department ===");
        System.out.println("1. Software Engineering");
        System.out.println("2. Networking");
        System.out.println("3. Information Management");
        System.out.println("4. Foreign Student");
        System.out.println("5. Back to Main Menu");
        System.out.println("=========================");
    }

    private static void displayDepartmentInfo() {
        System.out.println("\n=== Department Information ===");
        System.out.println("1. Software Engineering");
        System.out.println("   - Marks must be above 80");
        System.out.println("   - Combination must be MPC or MPG");
        System.out.println("   - Registration fee: 30,000 RWF");
        
        System.out.println("\n2. Networking");
        System.out.println("   - Combination must be MCE");
        System.out.println("   - Marks must be above 70");
        System.out.println("   - Registration fee: 40,000 RWF");
        
        System.out.println("\n3. Information Management");
        System.out.println("   - Marks must be between 50 and 70");
        System.out.println("   - Combination must be MEG");
        System.out.println("   - Registration fee: 50 RWF");
        
        System.out.println("\n4. Foreign Students");
        System.out.println("   - Only age and marks are validated");
        System.out.println("   - No combination validation required");
        System.out.println("   - 10% discount on registration fees");
        
        System.out.println("\nPress Enter to return to main menu...");
        scanner.nextLine();
    }

    private static void registerNewStudent() {
        displayDepartmentMenu();
        int deptChoice = getIntInput("Select department (1-5): ");
        
        if (deptChoice == 5) return;

        System.out.println("\n=== Student Registration Form ===");
        String id = getInput("Student ID: ");
        
        // Check if ID already exists
        if (isIdExists(id)) {
            System.out.println("Error: Student ID already exists!");
            return;
        }

        String fullName = getInput("Full Name: ");
        LocalDate dateOfBirth = getDateInput("Date of Birth (yyyy-MM-dd): ");
        String nationality = getInput("Nationality: ");
        String combination = getCombinationInput();
        double marks = getDoubleInput("Marks: ");
        LocalDate registrationDate = getDateInput("Registration Date (yyyy-MM-dd): ");
        double registrationFeePaid = getDoubleInput("Registration Fee Paid: ");
        boolean isForeignStudent = getBooleanInput("Is Foreign Student (true/false): ");

        // Validate inputs
        if (!validateStudentInputs(fullName, dateOfBirth, nationality, combination, marks, registrationDate)) {
            return;
        }

        Student student = createStudent(deptChoice, id, fullName, dateOfBirth, nationality, 
                                     combination, marks, registrationDate, registrationFeePaid, isForeignStudent);

        if (student != null) {
            registeredStudents.add(student);
            System.out.println("\nStudent registered successfully!");
            System.out.println("Registration Fee: " + student.calculateRegistration());
        } else {
            System.out.println("\nStudent does not meet department criteria.");
        }
    }

    private static void viewAllStudents() {
        if (registeredStudents.isEmpty()) {
            System.out.println("\nNo students registered yet.");
            return;
        }

        System.out.println("\n=== Registered Students ===");
        for (Student student : registeredStudents) {
            displayStudentInfo(student);
        }
    }

    private static void searchStudent() {
        String searchId = getInput("\nEnter Student ID to search: ");
        Student found = registeredStudents.stream()
                .filter(s -> s.getId().equals(searchId))
                .findFirst()
                .orElse(null);

        if (found != null) {
            System.out.println("\n=== Student Information ===");
            displayStudentInfo(found);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void displayStudentInfo(Student student) {
        System.out.println("\nStudent ID: " + student.getId());
        System.out.println("Name: " + student.getFullName());
        System.out.println("Department: " + student.getDepartment());
        System.out.println("Combination: " + student.getCombination());
        System.out.println("Marks: " + student.getMarks());
        System.out.println("Registration Fee: " + student.calculateRegistration());
        System.out.println("Foreign Student: " + student.isForeignStudent());
        System.out.println("-----------------------------------");
    }

    private static Student createStudent(int deptChoice, String id, String fullName, 
            LocalDate dateOfBirth, String nationality, String combination, double marks, 
            LocalDate registrationDate, double registrationFeePaid, boolean isForeignStudent) {
        
        switch (deptChoice) {
            case 1:
                if (marks > 80 && (combination.equals("mpc") || combination.equals("mpg"))) {
                    return new SoftwareEngineeringStudent(id, fullName, dateOfBirth, nationality, 
                            combination, marks, registrationDate, registrationFeePaid, isForeignStudent);
                }
                break;
            case 2:
                if (combination.equals("mce") && marks > 70) {
                    return new NetworkingStudent(id, fullName, dateOfBirth, nationality, 
                            combination, marks, registrationDate, registrationFeePaid, isForeignStudent);
                }
                break;
            case 3:
                if (marks >= 50 && marks <= 70 && combination.equals("meg")) {
                    return new InformationManagementStudent(id, fullName, dateOfBirth, nationality, 
                            combination, marks, registrationDate, registrationFeePaid, isForeignStudent);
                }
                break;
            case 4:
                if (isForeignStudent) {
                    return new ForeignStudent(id, fullName, dateOfBirth, nationality, 
                            combination, marks, registrationDate, registrationFeePaid, isForeignStudent);
                }
                break;
        }
        return null;
    }

    private static boolean validateStudentInputs(String fullName, LocalDate dateOfBirth, 
            String nationality, String combination, double marks, LocalDate registrationDate) {
        
        if (!ValidationUtils.validateName(fullName)) {
            System.out.println("Error: Invalid name format!");
            return false;
        }
        if (!ValidationUtils.validateDateOfBirth(dateOfBirth)) {
            System.out.println("Error: Invalid date of birth!");
            return false;
        }
        if (!ValidationUtils.validateAge(dateOfBirth)) {
            System.out.println("Error: Student must be 18 or older!");
            return false;
        }
        if (!ValidationUtils.validateMarks(marks)) {
            System.out.println("Error: Marks must be between 0 and 100!");
            return false;
        }
        if (!ValidationUtils.validateCombination(combination)) {
            System.out.println("Error: Invalid combination!");
            return false;
        }
        if (!ValidationUtils.validateRegistrationDate(registrationDate)) {
            System.out.println("Error: Registration is only allowed on Monday or Friday!");
            return false;
        }
        if (!ValidationUtils.validateNationality(nationality)) {
            System.out.println("Error: Nationality is required!");
            return false;
        }
        return true;
    }

    private static boolean isIdExists(String id) {
        return registeredStudents.stream().anyMatch(s -> s.getId().equals(id));
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Invalid input. Please enter 'true' or 'false'.");
        }
    }

    private static LocalDate getDateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            LocalDate date = DateUtils.parseDate(scanner.nextLine());
            if (date != null) {
                return date;
            }
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static String getCombinationInput() {
        while (true) {
            System.out.print("Combination (mpc, mpg, mce, meg): ");
            String input = scanner.nextLine().toLowerCase();
            if (ValidationUtils.validateCombination(input)) {
                return input;
            }
            System.out.println("Invalid combination. Please enter mpc, mpg, mce, or meg.");
        }
    }
}