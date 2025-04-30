import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PerishableItem extends StockItem {
    private LocalDate productionDate;
    private int shelfLifeDays;
    private double discountPercentage;

    public PerishableItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                          String category, String supplier, LocalDate productionDate, int shelfLifeDays) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.productionDate = productionDate;
        this.shelfLifeDays = shelfLifeDays;
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
        return quantityInStock * pricePerUnit * (1 - discountPercentage / 100);
    }

    @Override
    public void generateStockReport() {
        System.out.println("\nPerishable Item Report:");
        System.out.println("Item ID: " + itemId);
        System.out.println("Item Name: " + itemName);
        System.out.println("Quantity in Stock: " + quantityInStock);
        System.out.println("Price per Unit: $" + pricePerUnit);
        System.out.println("Production Date: " + productionDate);
        System.out.println("Shelf Life: " + shelfLifeDays + " days");
        System.out.println("Days Remaining: " + getDaysRemaining());
        System.out.println("Discount: " + discountPercentage + "%");
        System.out.println("Total Stock Value: $" + calculateStockValue());

        if (needsDisposal()) {
            System.out.println("WARNING: This item needs to be disposed of!");
        } else if (isNearExpiration()) {
            System.out.println("WARNING: Item is nearing expiration!");
        }
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0 && !needsDisposal();
    }

    public void applyDiscount(double discount) {
        if (discount > 50) {
            System.out.println("Error: Discount cannot exceed 50%");
            return;
        }
        this.discountPercentage = discount;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public int getShelfLifeDays() {
        return shelfLifeDays;
    }

    public void setShelfLifeDays(int shelfLifeDays) {
        if (shelfLifeDays <= 0) {
            System.out.println("Error: Shelf life must be greater than 0");
            return;
        }
        this.shelfLifeDays = shelfLifeDays;
    }

    public long getDaysRemaining() {
        LocalDate expirationDate = productionDate.plusDays(shelfLifeDays);
        return ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
    }

    public boolean needsDisposal() {
        return getDaysRemaining() <= 0;
    }

    public boolean isNearExpiration() {
        return getDaysRemaining() <= 3;
    }
}