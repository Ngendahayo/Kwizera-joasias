public class FurnitureItem extends StockItem {
    private double weight; // in kg
    private boolean isWellPacked;
    private double discountPercentage;

    public FurnitureItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                         String category, String supplier, double weight) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.weight = weight;
        this.isWellPacked = false;
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
        System.out.println("\nFurniture Item Report:");
        System.out.println("Item ID: " + itemId);
        System.out.println("Item Name: " + itemName);
        System.out.println("Quantity in Stock: " + quantityInStock);
        System.out.println("Price per Unit: $" + pricePerUnit);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Packaging Status: " + (isWellPacked ? "Well Packed" : "Needs Packaging"));
        System.out.println("Discount: " + discountPercentage + "%");
        System.out.println("Total Stock Value: $" + calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0 && isWellPacked;
    }

    public void applyDiscount(double discount) {
        if (discount > 50) {
            System.out.println("Error: Discount cannot exceed 50%");
            return;
        }
        this.discountPercentage = discount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            System.out.println("Error: Weight must be greater than 0");
            return;
        }
        this.weight = weight;
    }

    public boolean isWellPacked() {
        return isWellPacked;
    }

    public void setWellPacked(boolean wellPacked) {
        isWellPacked = wellPacked;
    }

    public double calculateShippingCost() {
        // Basic shipping calculation based on weight
        return weight * 2.5; // $2.50 per kg
    }
}