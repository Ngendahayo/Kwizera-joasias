import java.util.*;

public class Warehouse {
    private String warehouseId;
    private String location;
    private int capacity;
    private String managerName;
    private Map<String, Integer> stockMovement; // Map<ItemId, Quantity>
    private List<String> stockHistory;

    public Warehouse(String warehouseId, String location, int capacity, String managerName) {
        setWarehouseId(warehouseId);
        setLocation(location);
        setCapacity(capacity);
        setManagerName(managerName);
        this.stockMovement = new HashMap<>();
        this.stockHistory = new ArrayList<>();
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        if (warehouseId == null || warehouseId.trim().isEmpty()) {
            throw new IllegalArgumentException("Warehouse ID cannot be empty");
        }
        this.warehouseId = warehouseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        if (managerName == null || managerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Manager name cannot be empty");
        }
        this.managerName = managerName;
    }

    public void addStockMovement(String itemId, int quantity) {
        if (itemId == null || itemId.trim().isEmpty()) {
            throw new IllegalArgumentException("Item ID cannot be empty");
        }

        int currentQuantity = stockMovement.getOrDefault(itemId, 0);
        int newQuantity = currentQuantity + quantity;

        if (newQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }

        if (newQuantity > capacity) {
            throw new IllegalArgumentException("Stock quantity exceeds warehouse capacity");
        }

        stockMovement.put(itemId, newQuantity);
        stockHistory.add(String.format("%s: %s - Quantity changed by %d (New total: %d)",
                new Date(), itemId, quantity, newQuantity));
    }

    public int getCurrentStock(String itemId) {
        return stockMovement.getOrDefault(itemId, 0);
    }

    public List<String> getStockHistory() {
        return new ArrayList<>(stockHistory);
    }

    public Map<String, Integer> getStockMovement() {
        return new HashMap<>(stockMovement);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseId='" + warehouseId + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", managerName='" + managerName + '\'' +
                ", currentStock=" + stockMovement +
                '}';
    }
}