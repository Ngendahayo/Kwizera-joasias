public class Person {
    private String personId;
    private String fullName;
    private String dob;
    private String email;
    private String phone;

    public Person(String personId, String fullName, String dob, String email, String phone) {
        this.personId = personId;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public boolean validatePerson() {
        return email.contains("@") && phone.length() >= 10;
    }

    // Getters
    public String getPersonId() { return personId; }
    public String getFullName() { return fullName; }
    public String getDob() { return dob; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
