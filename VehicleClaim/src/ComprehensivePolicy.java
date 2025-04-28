import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ComprehensivePolicy extends InsurancePolicy {

    public ComprehensivePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount) {
        super(policyId, vehicle, policyHolder, coverageAmount);
        calculatePremium();
    }

    @Override
    public void calculatePremium() {
        int currentYear = LocalDate.now().getYear();
        int vehicleAge = currentYear - vehicle.getVehicleYear();
        if (vehicleAge < 5) {
            premiumAmount = coverageAmount * 0.03; // 3%
        } else if (vehicleAge <= 10) {
            premiumAmount = coverageAmount * 0.05; // 5%
        } else {
            premiumAmount = coverageAmount * 0.07; // 7%
        }
    }

    @Override
    public boolean processClaim(Claim claim) {
        if (!claim.validateClaim(coverageAmount)) {
            claim.rejectClaim();
            System.out.println("Claim rejected: amount exceeds coverage.");
            return false;
        }

        claim.approveClaim();
        System.out.println("Claim approved.");
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("----- Comprehensive Policy Report -----");
        System.out.println("Policy ID: " + policyId);
        System.out.println("Holder: " + policyHolder.getFullName());
        System.out.println("Vehicle: " + vehicle.getVehicleMake() + " " + vehicle.getVehicleModel());
        System.out.println("Coverage: $" + coverageAmount);
        System.out.println("Premium: $" + premiumAmount);
        System.out.println("Start Date: " + policyStartDate);
        System.out.println("End Date: " + policyEndDate);
        System.out.println("---------------------------------------");
    }

    @Override
    public boolean validatePolicy() {
        boolean validType = vehicle.getVehicleType().equalsIgnoreCase("Car") || vehicle.getVehicleType().equalsIgnoreCase("SUV");
        boolean validYear = vehicle.getVehicleYear() >= 2000;

        if (!validType || !validYear) {
            System.out.println("Policy validation failed: Invalid vehicle type or year.");
            return false;
        }
        System.out.println("Policy validated successfully.");
        return true;
    }
}
