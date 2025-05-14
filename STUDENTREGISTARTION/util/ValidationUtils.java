package util;

import java.time.LocalDate;
import java.time.DayOfWeek;

public class ValidationUtils {
    // Validate name: must not be empty and must only contain letters
    public static boolean validateName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z\\s]+");
    }

    // Validate date of birth: must be in yyyy-MM-dd format and used to calculate
    // age
    public static boolean validateDateOfBirth(LocalDate dateOfBirth) {
        return dateOfBirth != null && dateOfBirth.isBefore(LocalDate.now());
    }

    // Validate age: must be 18 years or older
    public static boolean validateAge(LocalDate dateOfBirth) {
        return java.time.Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }

    // Validate marks: must be between 0 and 100
    public static boolean validateMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }

    // Validate combination: must be one of mpc, mpg, mce, meg
    public static boolean validateCombination(String combination) {
        return combination != null && (combination.equals("mpc") || combination.equals("mpg")
                || combination.equals("mce") || combination.equals("meg"));
    }

    // Validate registration date: must be Monday or Friday
    public static boolean validateRegistrationDate(LocalDate registrationDate) {
        return registrationDate != null && (registrationDate.getDayOfWeek() == DayOfWeek.MONDAY
                || registrationDate.getDayOfWeek() == DayOfWeek.FRIDAY);
    }

    // Validate nationality: must not be empty
    public static boolean validateNationality(String nationality) {
        return nationality != null && !nationality.trim().isEmpty();
    }
}