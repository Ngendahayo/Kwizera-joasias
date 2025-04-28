import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineShoppingSystem {
    private static final List<ShoppingItem> inventory = new ArrayList<>();
    private static final List<Customer> customers = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSampleData();

        while (true) {
            System.out.println("\n=== Online Shopping System ===");
            System.out.println("1. Register New Customer");
            System.out.println("2. View Inventory");
            System.out.println("3. Add Item to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Add Review");
            System.out.println("7. View Item Reviews");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    displayInventory();
                    break;
                case 3:
                    addItemToCart();
                    break;
                case 4:
                    viewCart();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    addReview();
                    break;
                case 7:
                    viewItemReviews();
                    break;
                case 8:
                    System.out.println("Thank you for shopping with us!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeSampleData() {
        // Add sample customers
        customers.add(new Customer("C001", "John Doe", "john@example.com", "123 Main St", "1234567890"));

        // Add sample items
        inventory.add(new ElectronicsItem("E001", "Smartphone", "Latest model", 999.99, 10, 12, "SN123456"));
        inventory.add(new ClothingItem("CL001", "T-Shirt", "Cotton casual", 29.99, "Summer", 0.2));
        inventory.add(new GroceriesItem("G001", "Organic Apples", "Fresh fruit", 4.99, 100,
                LocalDate.now().plusDays(14), 10, 0.1));
        inventory.add(new BooksItem("B001", "Java Programming", "Programming guide", 49.99, 20,
                "978-0134685991", "2nd", "John Smith", "Paperback"));
        inventory.add(new AccessoriesItem("A001", "Leather Wallet", "Genuine leather", 39.99, 15, "Fashion"));
    }

    private static void registerCustomer() {
        System.out.println("\n=== Customer Registration ===");
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(customerId, name, email, address, phone);
        if (customer.validateAllDetails()) {
            customers.add(customer);
            System.out.println("Customer registered successfully!");
        } else {
            System.out.println("Invalid customer details. Please check the information provided.");
        }
    }

    private static void displayInventory() {
        System.out.println("\n=== Available Items ===");
        for (ShoppingItem item : inventory) {
            System.out.println("\n" + item.toString());
        }
    }

    private static void addItemToCart() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        displayInventory();
        System.out.print("Enter item ID to add to cart: ");
        String itemId = scanner.nextLine();
        ShoppingItem item = findItem(itemId);

        if (item == null) {
            System.out.println("Item not found!");
            return;
        }

        if (item.addToCart(customer)) {
            System.out.println("Item added to cart successfully!");
        } else {
            System.out.println("Failed to add item to cart. Please check stock availability.");
        }
    }

    private static void viewCart() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        // In a real implementation, we would maintain a cart for each customer
        System.out.println("Cart viewing functionality would show items added by the customer.");
    }

    private static void checkout() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.println("\nProcessing payment...");
        System.out.print("Enter payment method (Credit Card/PayPal/Debit Card): ");
        String paymentMethod = scanner.nextLine();

        Payment payment = new Payment("P" + System.currentTimeMillis(), paymentMethod, 0.0); // Amount would be cart total
        if (payment.validatePaymentMethod()) {
            System.out.println("Payment processed successfully!");
        } else {
            System.out.println("Invalid payment method!");
        }
    }

    private static void addReview() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter item ID to review: ");
        String itemId = scanner.nextLine();
        ShoppingItem item = findItem(itemId);

        if (!(item instanceof AccessoriesItem accessory)) {
            System.out.println("Item not found or not reviewable!");
            return;
        }

        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter review comment: ");
        String comment = scanner.nextLine();

        accessory.addReview(customer, rating, comment, LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        System.out.println("Review added successfully!");
    }

    private static void viewItemReviews() {
        System.out.print("Enter item ID: ");
        String itemId = scanner.nextLine();
        ShoppingItem item = findItem(itemId);

        if (!(item instanceof AccessoriesItem accessory)) {
            System.out.println("Item not found or not reviewable!");
            return;
        }

        System.out.println(accessory.getReviewsSummary());
    }

    private static Customer findCustomer(String customerId) {
        return customers.stream()
                .filter(c -> c.customerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    private static ShoppingItem findItem(String itemId) {
        return inventory.stream()
                .filter(i -> i.getItemId().equals(itemId))
                .findFirst()
                .orElse(null);
    }
}