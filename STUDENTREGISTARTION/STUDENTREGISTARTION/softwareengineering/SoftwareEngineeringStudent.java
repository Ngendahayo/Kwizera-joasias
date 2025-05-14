package softwareengineering;

import abstractclass.Student;
import java.time.LocalDate;

public class SoftwareEngineeringStudent extends Student {
    public SoftwareEngineeringStudent(String id, String fullName, LocalDate dateOfBirth, String nationality,
            String combination, double marks, LocalDate registrationDate, double registrationFeePaid,
            boolean isForeignStudent) {
        super(id, fullName, dateOfBirth, nationality, "Software Engineering", combination, marks, registrationDate,
                registrationFeePaid, isForeignStudent);
    }

    @Override
    public boolean validateAge() {
        // Age must be 18 or older
        return java.time.Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }

    @Override
    public boolean validateMarks() {
        // Marks must be above 80
        return marks > 80;
    }

    @Override
    public boolean validateCombination() {
        // Combination must be mpc or mpg
        return combination.equals("mpc") || combination.equals("mpg");
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