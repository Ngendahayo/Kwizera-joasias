package foreignstudent;

import abstractclass.Student;
import java.time.LocalDate;

public class ForeignStudent extends Student {
    public ForeignStudent(String id, String fullName, LocalDate dateOfBirth, String nationality, String combination, double marks, LocalDate registrationDate, double registrationFeePaid, boolean isForeignStudent) {
        super(id, fullName, dateOfBirth, nationality, "Foreign Student", combination, marks, registrationDate, registrationFeePaid, isForeignStudent);
    }

    @Override
    public boolean validateAge() {
        // Age must be 18 or older
        return java.time.Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }

    @Override
    public boolean validateMarks() {
        // Marks must be between 0 and 100
        return marks >= 0 && marks <= 100;
    }

    @Override
    public boolean validateCombination() {
        // No combination validation required for foreign students
        return true;
    }

    @Override
    public double calculateRegistration() {
        // Registration fee is 30000 RWF, with 10% discount for foreign students
        double fee = 30000;
        if (isForeignStudent) {
            fee *= 0.9;
        }
        return fee;
    }
} 