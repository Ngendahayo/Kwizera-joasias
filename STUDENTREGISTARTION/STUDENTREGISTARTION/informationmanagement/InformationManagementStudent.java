package informationmanagement;

import abstractclass.Student;
import java.time.LocalDate;

public class InformationManagementStudent extends Student {
    public InformationManagementStudent(String id, String fullName, LocalDate dateOfBirth, String nationality,
            String combination, double marks, LocalDate registrationDate, double registrationFeePaid,
            boolean isForeignStudent) {
        super(id, fullName, dateOfBirth, nationality, "Information Management", combination, marks, registrationDate,
                registrationFeePaid, isForeignStudent);
    }

    @Override
    public boolean validateAge() {
        // Age must be 18 or older
        return java.time.Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }

    @Override
    public boolean validateMarks() {
        // Marks must be between 50 and 70
        return marks >= 50 && marks <= 70;
    }

    @Override
    public boolean validateCombination() {
        // Combination must be meg
        return combination.equals("meg");
    }

    @Override
    public double calculateRegistration() {
        // Registration fee is 50 RWF, with 10% discount for foreign students
        double fee = 50;
        if (isForeignStudent) {
            fee *= 0.9;
        }
        return fee;
    }
}