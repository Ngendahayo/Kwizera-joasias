import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Payment {
    private String paymentId;
    private String paymentMethod;
    private double amountPaid;
    private LocalDateTime transactionDate;
    private static final List<String> VALID_PAYMENT_METHODS = Arrays.asList("Credit Card", "PayPal", "Debit Card");

    public Payment(String paymentId, String paymentMethod, double amountPaid) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.transactionDate = LocalDateTime.now();
    }

    public boolean validatePaymentMethod() {
        return VALID_PAYMENT_METHODS.contains(paymentMethod);
    }

    public boolean validatePaymentAmount(double expectedAmount) {
        return Math.abs(amountPaid - expectedAmount) < 0.01; // Account for floating-point precision
    }

    public boolean processPayment(ShoppingCart cart) {
        if (!validatePaymentMethod()) {
            System.out.println("Invalid payment method: " + paymentMethod);
            return false;
        }

        if (!validatePaymentAmount(cart.getTotalPrice())) {
            System.out.println("Payment amount does not match cart total");
            return false;
        }

        // In a real system, this would integrate with a payment gateway
        System.out.println("Processing payment of $" + amountPaid + " via " + paymentMethod);
        return true;
    }

    // Getters
    public String getPaymentId() { return paymentId; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getAmountPaid() { return amountPaid; }
    public LocalDateTime getTransactionDate() { return transactionDate; }

    public String generateReceipt(ShoppingCart cart) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=== Payment Receipt ===\n")
                .append("Payment ID: ").append(paymentId).append("\n")
                .append("Date: ").append(transactionDate).append("\n")
                .append("Payment Method: ").append(paymentMethod).append("\n")
                .append("Amount Paid: $").append(String.format("%.2f", amountPaid)).append("\n")
                .append("\nCustomer Details:\n")
                .append(cart.getCustomer().toString()).append("\n")
                .append("\nPurchased Items:\n");

        for (ShoppingItem item : cart.getCartItems()) {
            receipt.append("- ").append(item.getItemName())
                    .append(" ($").append(item.getPrice()).append(")\n");
        }

        receipt.append("\nTotal Amount: $").append(String.format("%.2f", cart.getTotalPrice()));
        return receipt.toString();
    }
} 