import java.util.HashMap;
import java.util.Map;

public class ClothingItem extends ShoppingItem {
    private final Map<String, Integer> sizeStock;
    private final String season;
    private final double seasonalDiscount;

    public ClothingItem(String itemId, String itemName, String itemDescription, double price,
                        String season, double seasonalDiscount) {
        super(itemId, itemName, itemDescription, price, 0);
        this.sizeStock = new HashMap<>();
        this.season = season;
        this.seasonalDiscount = seasonalDiscount;
    }

    public void addSizeStock(String size, int quantity) {
        sizeStock.put(size, sizeStock.getOrDefault(size, 0) + quantity);
        updateTotalStock();
    }

    private void updateTotalStock() {
        stockAvailable = sizeStock.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public boolean updateStock(int quantity) {
        // This method should not be used directly for clothing items
        // Use addSizeStock instead
        return false;
    }

    public boolean updateSizeStock(String size, int quantity) {
        int currentStock = sizeStock.getOrDefault(size, 0);
        if (currentStock + quantity >= 0) {
            sizeStock.put(size, currentStock + quantity);
            updateTotalStock();
            return true;
        }
        return false;
    }

    @Override
    public boolean addToCart(Customer customer) {
        // This should be called with a specific size in a real implementation
        return validateItem();
    }

    public boolean addToCartWithSize(Customer customer, String size) {
        if (validateItem() && sizeStock.getOrDefault(size, 0) > 0) {
            sizeStock.put(size, sizeStock.get(size) - 1);
            updateTotalStock();
            return true;
        }
        return false;
    }

    @Override
    public String generateInvoice(Customer customer) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("=== Clothing Item Invoice ===\n")
                .append(super.toString()).append("\n")
                .append("Season: ").append(season).append("\n");

        if (seasonalDiscount > 0) {
            invoice.append("Seasonal Discount: ").append(seasonalDiscount * 100).append("%\n")
                    .append("Final Price: $").append(String.format("%.2f", price * (1 - seasonalDiscount)));
        }

        invoice.append("\n\nAvailable Sizes and Stock:\n");
        for (Map.Entry<String, Integer> entry : sizeStock.entrySet()) {
            invoice.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        invoice.append("\nCustomer Details:\n")
                .append(customer.toString());
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        return !sizeStock.isEmpty() &&
                sizeStock.values().stream().anyMatch(stock -> stock > 0) &&
                seasonalDiscount >= 0 && seasonalDiscount <= 1;
    }

    public double getDiscountedPrice() {
        return price * (1 - seasonalDiscount);
    }

    // Getters
    public Map<String, Integer> getSizeStock() { return new HashMap<>(sizeStock); }
    public String getSeason() { return season; }
    public double getSeasonalDiscount() { return seasonalDiscount; }
}