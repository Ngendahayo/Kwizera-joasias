import java.time.LocalDate;

public class Claim {
    private String claimId;
    private double claimAmount;
    private LocalDate claimDate;
    private String claimStatus;

    public Claim(String claimId, double claimAmount) {
        this.claimId = claimId;
        this.claimAmount = claimAmount;
        this.claimDate = LocalDate.now();
        this.claimStatus = "Pending";
    }

    public boolean validateClaim(double coverageAmount) {
        return claimAmount <= coverageAmount;
    }

    // Getters and Setters
    public String getClaimId() { return claimId; }
    public double getClaimAmount() { return claimAmount; }
    public LocalDate getClaimDate() { return claimDate; }
    public String getClaimStatus() { return claimStatus; }

    public void approveClaim() {
        this.claimStatus = "Approved";
    }

    public void rejectClaim() {
        this.claimStatus = "Rejected";
    }
}
