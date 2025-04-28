import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private String cartId;
    private List<ShoppingItem> cartItems;
    private double totalPrice;
    private Customer customer;

    public ShoppingCart(String cartId, Customer customer) {
        this.cartId = cartId;
        this.customer = customer;
        this.cartItems = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public boolean addItem(ShoppingItem item) {
        if (item.validateItem() && item.getStockAvailable() > 0) {
            cartItems.add(item);
            totalPrice += item.getPrice();
            return true;
        }
        return false;
    }

    public boolean removeItem(ShoppingItem item) {
        if (cartItems.remove(item)) {
            totalPrice -= item.getPrice();
            return true;
        }
        return false;
    }

    public void clearCart() {
        cartItems.clear();
        totalPrice = 0.0;
    }

    // Getters
    public String getCartId() { return cartId; }
    public List<ShoppingItem> getCartItems() { return new ArrayList<>(cartItems); }
    public double getTotalPrice() { return totalPrice; }
    public Customer getCustomer() { return customer; }

    public String generateCartSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Cart ID: ").append(cartId)
                .append("\nCustomer: ").append(customer.customerName())
                .append("\n\nItems in Cart:\n");

        for (ShoppingItem item : cartItems) {
            summary.append("- ").append(item.getItemName())
                    .append(" ($").append(item.getPrice()).append(")\n");
        }

        summary.append("\nTotal Price: $").append(String.format("%.2f", totalPrice));
        return summary.toString();
    }
}