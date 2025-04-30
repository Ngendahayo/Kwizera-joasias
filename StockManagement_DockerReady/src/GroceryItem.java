import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GroceryItem extends StockItem {
    private LocalDate expirationDate;
    private double discountPercentage;

    public GroceryItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                       String category, String supplier, LocalDate expirationDate) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.expirationDate = expirationDate;
        this.discountPercentage = 0.0;
    }

    @Override
    public void updateStock(int quantity) {
        if (quantity < 0) {
            System.out.println("Error: Stock quantity cannot be negative");
            return;
        }
        this.quantityInStock = quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (1 - discountPercentage/100);
    }

    @Override
    public void generateStockReport() {
        System.out.println("\nGrocery Item Report:");
        System.out.println("Item ID: " + itemId);
        System.out.println("Item Name: " + itemName);
        System.out.println("Quantity in Stock: " + quantityInStock);
        System.out.println("Price per Unit: $" + pricePerUnit);
        System.out.println("Expiration Date: " + expirationDate);
        System.out.println("Discount: " + discountPercentage + "%");
        System.out.println("Total Stock Value: $" + calculateStockValue());

        long daysUntilExpiration = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        if (daysUntilExpiration <= 7) {
            System.out.println("WARNING: Item is nearing expiration! " + daysUntilExpiration + " days remaining.");
        }
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0 && !LocalDate.now().isAfter(expirationDate);
    }

    public void applyDiscount(double discount) {
        if (discount > 50) {
            System.out.println("Error: Discount cannot exceed 50%");
            return;
        }
        this.discountPercentage = discount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isNearExpiration() {
        return ChronoUnit.DAYS.between(LocalDate.now(), expirationDate) <= 7;
    }
}