import java.time.LocalDate;

public class GroceriesItem extends ShoppingItem {
    private LocalDate expirationDate;
    private int bulkQuantity; // Minimum quantity for bulk discount
    private double bulkDiscount; // Discount percentage for bulk purchases

    public GroceriesItem(String itemId, String itemName, String itemDescription, double price,
                         int stockAvailable, LocalDate expirationDate, int bulkQuantity, double bulkDiscount) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.expirationDate = expirationDate;
        this.bulkQuantity = bulkQuantity;
        this.bulkDiscount = bulkDiscount;
    }

    @Override
    public boolean updateStock(int quantity) {
        if (stockAvailable + quantity >= 0) {
            stockAvailable += quantity;
            return true;
        }
        return false;
    }

    @Override
    public boolean addToCart(Customer customer) {
        if (validateItem() && stockAvailable > 0) {
            stockAvailable--;
            return true;
        }
        return false;
    }

    public boolean addToCartBulk(Customer customer, int quantity) {
        if (validateItem() && stockAvailable >= quantity) {
            stockAvailable -= quantity;
            return true;
        }
        return false;
    }

    @Override
    public String generateInvoice(Customer customer) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("=== Groceries Item Invoice ===\n")
                .append(super.toString()).append("\n")
                .append("Expiration Date: ").append(expirationDate).append("\n")
                .append("Bulk Purchase Details:\n")
                .append("  Minimum Quantity: ").append(bulkQuantity).append("\n")
                .append("  Bulk Discount: ").append(bulkDiscount * 100).append("%\n")
                .append("\nCustomer Details:\n")
                .append(customer.toString());
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 &&
                expirationDate != null &&
                expirationDate.isAfter(LocalDate.now()) &&
                bulkQuantity > 0 &&
                bulkDiscount >= 0 && bulkDiscount <= 1;
    }

    public double calculatePrice(int quantity) {
        if (quantity >= bulkQuantity) {
            return price * quantity * (1 - bulkDiscount);
        }
        return price * quantity;
    }

    // Getters
    public LocalDate getExpirationDate() { return expirationDate; }
    public int getBulkQuantity() { return bulkQuantity; }
    public double getBulkDiscount() { return bulkDiscount; }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    public int getDaysUntilExpiration() {
        return LocalDate.now().until(expirationDate).getDays();
    }
}