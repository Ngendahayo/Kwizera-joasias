public abstract class ShoppingItem {
    protected String itemId;
    protected String itemName;
    protected String itemDescription;
    protected double price;
    protected int stockAvailable;

    public ShoppingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.stockAvailable = stockAvailable;
    }

    // Getters and Setters
    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public String getItemDescription() { return itemDescription; }
    public double getPrice() { return price; }
    public int getStockAvailable() { return stockAvailable; }

    // Abstract methods
    public abstract boolean updateStock(int quantity);
    public abstract boolean addToCart(Customer customer);
    public abstract String generateInvoice(Customer customer);
    public abstract boolean validateItem();

    @Override
    public String toString() {
        return "Item ID: " + itemId +
                "\nName: " + itemName +
                "\nDescription: " + itemDescription +
                "\nPrice: $" + price +
                "\nStock Available: " + stockAvailable;
    }
}