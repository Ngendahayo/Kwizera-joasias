public class ElectronicsItem extends ShoppingItem {
    private int warrantyPeriodMonths;
    private String serialNumber;

    public ElectronicsItem(String itemId, String itemName, String itemDescription, double price,
                           int stockAvailable, int warrantyPeriodMonths, String serialNumber) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.warrantyPeriodMonths = warrantyPeriodMonths;
        this.serialNumber = serialNumber;
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

    @Override
    public String generateInvoice(Customer customer) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("=== Electronics Item Invoice ===\n")
                .append(super.toString()).append("\n")
                .append("Warranty Period: ").append(warrantyPeriodMonths).append(" months\n")
                .append("Serial Number: ").append(serialNumber).append("\n")
                .append("\nCustomer Details:\n")
                .append(customer.toString());
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 &&
                serialNumber != null &&
                !serialNumber.trim().isEmpty() &&
                warrantyPeriodMonths > 0;
    }

    public String registerProduct(Customer customer) {
        return "Product " + itemName + " (S/N: " + serialNumber + ") registered to " +
                customer.customerName() + " with " + warrantyPeriodMonths + " months warranty.";
    }

    // Getters
    public int getWarrantyPeriodMonths() { return warrantyPeriodMonths; }
    public String getSerialNumber() { return serialNumber; }
}