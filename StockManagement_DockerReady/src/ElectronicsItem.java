import java.util.Scanner;

public class ElectronicsItem extends StockItem {
    private int warrantyPeriod; // in months
    private double discountPercentage;

    public ElectronicsItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                           String category, String supplier, int warrantyPeriod) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.warrantyPeriod = warrantyPeriod;
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
        System.out.println("\nElectronics Item Report:");
        System.out.println("Item ID: " + itemId);
        System.out.println("Item Name: " + itemName);
        System.out.println("Quantity in Stock: " + quantityInStock);
        System.out.println("Price per Unit: $" + pricePerUnit);
        System.out.println("Warranty Period: " + warrantyPeriod + " months");
        System.out.println("Discount: " + discountPercentage + "%");
        System.out.println("Total Stock Value: $" + calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0;
    }

    public void applyDiscount(double discount) {
        if (discount > 50) {
            System.out.println("Error: Discount cannot exceed 50%");
            return;
        }
        this.discountPercentage = discount;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        if (warrantyPeriod < 0 || warrantyPeriod > 36) {
            System.out.println("Error: Warranty period must be between 0 and 36 months");
            return;
        }
        this.warrantyPeriod = warrantyPeriod;
    }
}