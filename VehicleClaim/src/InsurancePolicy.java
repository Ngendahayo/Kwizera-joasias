import java.time.LocalDate;

public abstract class InsurancePolicy {
    protected String policyId;
    protected Vehicle vehicle;
    protected Person policyHolder;
    protected double coverageAmount;
    protected double premiumAmount;
    protected LocalDate policyStartDate;
    protected LocalDate policyEndDate;

    public InsurancePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount) {
        this.policyId = policyId;
        this.vehicle = vehicle;
        this.policyHolder = policyHolder;
        this.coverageAmount = coverageAmount;
        this.policyStartDate = LocalDate.now();
        this.policyEndDate = policyStartDate.plusYears(1);
    }

    public abstract void calculatePremium();
    public abstract boolean processClaim(Claim claim);
    public abstract void generatePolicyReport();
    public abstract boolean validatePolicy();

    // Common Method
    public double getPremiumAmount() {
        return premiumAmount;
    }

    public String getPolicyId() {
        return policyId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Person getPolicyHolder() {
        return policyHolder;
    }
}
