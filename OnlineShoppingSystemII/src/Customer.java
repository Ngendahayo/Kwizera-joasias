import java.util.regex.Pattern;

public record Customer(String customerId, String customerName, String email, String address, String phone) {

    // Validation methods
    public boolean validateEmail() {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    public boolean validatePhone() {
        String phoneRegex = "^\\d{10}$";
        return Pattern.compile(phoneRegex).matcher(phone).matches();
    }

    public boolean validateAddress() {
        return address != null && !address.trim().isEmpty();
    }

    public boolean validateAllDetails() {
        return validateEmail() && validatePhone() && validateAddress();
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId +
                "\nName: " + customerName +
                "\nEmail: " + email +
                "\nAddress: " + address +
                "\nPhone: " + phone;
    }
}