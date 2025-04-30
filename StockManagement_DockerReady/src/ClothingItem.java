import java.util.*;

public class ClothingItem extends StockItem {
    private Map<String, Map<String, Integer>> sizeColorStock; // Map<Size, Map<Color, Quantity>>
    private double discountPercentage;

    public ClothingItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                        String category, String supplier) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.sizeColorStock = new HashMap<>();
        this.discountPercentage = 0.0;
    }

    public void addSizeColor(String size, String color, int quantity) {
        sizeColorStock.putIfAbsent(size, new HashMap<>());
        sizeColorStock.get(size).put(color, quantity);
        updateTotalQuantity();
    }

    private void updateTotalQuantity() {
        int total = 0;
        for (Map<String, Integer> colorMap : sizeColorStock.values()) {
            for (int quantity : colorMap.values()) {
                total += quantity;
            }
        }
        this.quantityInStock = total;
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
        System.out.println("\nClothing Item Report:");
        System.out.println("Item ID: " + itemId);
        System.out.println("Item Name: " + itemName);
        System.out.println("Total Quantity in Stock: " + quantityInStock);
        System.out.println("Price per Unit: $" + pricePerUnit);
        System.out.println("Discount: " + discountPercentage + "%");
        System.out.println("Total Stock Value: $" + calculateStockValue());

        System.out.println("\nSize and Color Breakdown:");
        for (Map.Entry<String, Map<String, Integer>> sizeEntry : sizeColorStock.entrySet()) {
            System.out.println("Size: " + sizeEntry.getKey());
            for (Map.Entry<String, Integer> colorEntry : sizeEntry.getValue().entrySet()) {
                System.out.println("  Color: " + colorEntry.getKey() + ", Quantity: " + colorEntry.getValue());
            }
        }
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

    public Map<String, Map<String, Integer>> getSizeColorStock() {
        return sizeColorStock;
    }
}