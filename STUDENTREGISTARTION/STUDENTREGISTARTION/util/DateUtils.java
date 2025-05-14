package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    // Parse date string in yyyy-MM-dd format to LocalDate
    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // Calculate age from date of birth
    public static int calculateAge(LocalDate dateOfBirth) {
        return java.time.Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    // Check if registration date is Monday or Friday
    public static boolean isRegistrationDay(LocalDate registrationDate) {
        return registrationDate != null && (registrationDate.getDayOfWeek().equals(java.time.DayOfWeek.MONDAY)
                || registrationDate.getDayOfWeek().equals(java.time.DayOfWeek.FRIDAY));
    }
}