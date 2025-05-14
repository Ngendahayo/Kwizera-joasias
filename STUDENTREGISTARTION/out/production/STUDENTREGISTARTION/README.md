# Student Registration System

A Java-based student registration system that demonstrates Object-Oriented Programming principles (Encapsulation, Inheritance, Polymorphism, and Abstraction).

## Project Structure

```
studentregistration/
│
├── abstractclass/
│   └── Student.java
│
├── softwareengineering/
│   └── SoftwareEngineeringStudent.java
│
├── networking/
│   └── NetworkingStudent.java
│
├── informationmanagement/
│   └── InformationManagementStudent.java
│
├── foreignstudent/
│   └── ForeignStudent.java
│
├── util/
│   ├── ValidationUtils.java
│   └── DateUtils.java
│
├── main/
│   └── Main.java
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, etc.)

## How to Compile and Run

1. Compile all Java files:
```bash
javac abstractclass/Student.java
javac softwareengineering/SoftwareEngineeringStudent.java
javac networking/NetworkingStudent.java
javac informationmanagement/InformationManagementStudent.java
javac foreignstudent/ForeignStudent.java
javac util/ValidationUtils.java
javac util/DateUtils.java
javac main/Main.java
```

2. Run the program:
```bash
java main.Main
```

## Department Rules

### Software Engineering
- Marks must be above 80
- Combination must be MPC or MPG
- Registration fee: 30,000 RWF

### Networking
- Combination must be MCE
- Marks must be above 70
- Registration fee: 40,000 RWF

### Information Management
- Marks must be between 50 and 70
- Combination must be MEG
- Registration fee: 50 RWF

### Foreign Students
- Only age and marks are validated
- No combination validation required
- 10% discount on registration fees

## Validation Rules

- Name: Must contain only letters
- Age: Must be 18 or older
- Marks: Between 0 and 100
- Combinations: MPC, MPG, MCE, MEG
- Registration: Only Monday or Friday
- Student ID: Must be unique
- Nationality: Required for foreign students

## Example Usage

```
Enter student details:
Student ID: SE001
Full Name: John Doe
Date of Birth (yyyy-MM-dd): 2000-01-01
Nationality: Rwanda
Combination (mpc, mpg, mce, meg): mpc
Marks: 85
Registration Date (yyyy-MM-dd): 2024-03-18
Registration Fee Paid: 30000
Is Foreign Student (true/false): false

Student registered successfully.
Registration Fee: 30000.0

Do you want to register another student? (true/false): false
```

## OOP Principles Demonstrated

1. **Encapsulation**
   - Protected attributes in Student class
   - Getters and setters for data access

2. **Inheritance**
   - Student as parent class
   - Department-specific student classes as subclasses

3. **Polymorphism**
   - Different implementations of abstract methods
   - Common interface for all student types

4. **Abstraction**
   - Abstract Student class
   - Abstract methods defining contract

## Error Handling

The system validates all inputs and provides appropriate error messages for:
- Invalid names
- Invalid dates
- Invalid marks
- Invalid combinations
- Invalid registration days
- Department eligibility criteria 