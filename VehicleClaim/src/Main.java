import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Get Vehicle Info
        System.out.println("=== Vehicle Information ===");
        System.out.print("Vehicle ID: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Type (Car/SUV): ");
        String type = scanner.nextLine();

        Vehicle vehicle = new Vehicle(vehicleId, make, model, year, type);
        if (!vehicle.validateDetails()) {
            System.out.println("Invalid vehicle details. Exiting.");
            return;
        }

        // Step 2: Get Person Info
        System.out.println("\n=== Policyholder Information ===");
        System.out.print("Person ID: ");
        String personId = scanner.nextLine();
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("DOB (yyyy-mm-dd): ");
        String dob = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        Person person = new Person(personId, fullName, dob, email, phone);
        if (!person.validatePerson()) {
            System.out.println("Invalid personal details. Exiting.");
            return;
        }

        // Step 3: Create Comprehensive Policy
        System.out.print("\nEnter Policy ID: ");
        String policyId = scanner.nextLine();
        System.out.print("Enter Coverage Amount: ");
        double coverageAmount = scanner.nextDouble();

        ComprehensivePolicy policy = new ComprehensivePolicy(policyId, vehicle, person, coverageAmount);

        // Step 4: Validate and Display Policy Info
        if (!policy.validatePolicy()) return;

        policy.generatePolicyReport();

        // Step 5: Create and Process Claim
        scanner.nextLine(); // clear buffer
        System.out.print("\nEnter Claim ID: ");
        String claimId = scanner.nextLine();
        System.out.print("Enter Claim Amount: ");
        double claimAmount = scanner.nextDouble();

        Claim claim = new Claim(claimId, claimAmount);
        policy.processClaim(claim);

        // Final Report
        policy.generatePolicyReport();
        System.out.println("Claim Status: " + claim.getClaimStatus());

        scanner.close();
    }
}
