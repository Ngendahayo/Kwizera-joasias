package networking;

import abstractclass.Student;
import java.time.LocalDate;

public class NetworkingStudent extends Student {
    public NetworkingStudent(String id, String fullName, LocalDate dateOfBirth, String nationality, String combination,
            double marks, LocalDate registrationDate, double registrationFeePaid, boolean isForeignStudent) {
        super(id, fullName, dateOfBirth, nationality, "Networking", combination, marks, registrationDate,
                registrationFeePaid, isForeignStudent);
    }

    @Override
    public boolean validateAge() {
        // Age must be 18 or older
        return java.time.Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }

    @Override
    public boolean validateMarks() {
        // Marks must be above 70
        return marks > 70;
    }

    @Override
    public boolean validateCombination() {
        // Combination must be mce
        return combination.equals("mce");
    }

    @Override
    public double calculateRegistration() {
        // Registration fee is 40000 RWF, with 10% discount for foreign students
        double fee = 40000;
        if (isForeignStudent) {
            fee *= 0.9;
        }
        return fee;
    }
}