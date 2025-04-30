import java.time.LocalDate;
import java.util.Scanner;

public class StockManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Stock Management System ===");
            System.out.println("1. Manage Electronics");
            System.out.println("2. Manage Clothing");
            System.out.println("3. Manage Grocery");
            System.out.println("4. Manage Furniture");
            System.out.println("5. Manage Perishable Items");
            System.out.println("6. Manage Products");
            System.out.println("7. Manage Suppliers");
            System.out.println("8. Manage Warehouse");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageElectronics();
                    break;
                case 2:
                    manageClothing();
                    break;
                case 3:
                    manageGrocery();
                    break;
                case 4:
                    manageFurniture();
                    break;
                case 5:
                    managePerishableItems();
                    break;
                case 6:
                    manageProducts();
                    break;
                case 7:
                    manageSuppliers();
                    break;
                case 8:
                    manageWarehouse();
                    break;
                case 9:
                    System.out.println("Thank you for using the Stock Management System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageElectronics() {
        System.out.println("\n=== Electronics Management ===");
        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price per Unit: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Supplier: ");
        String supplier = scanner.nextLine();
        System.out.print("Enter Warranty Period (months): ");
        int warranty = scanner.nextInt();

        ElectronicsItem item = new ElectronicsItem(itemId, itemName, quantity, price, category, supplier, warranty);
        item.generateStockReport();
    }

    private static void manageClothing() {
        System.out.println("\n=== Clothing Management ===");
        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price per Unit: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Supplier: ");
        String supplier = scanner.nextLine();

        ClothingItem item = new ClothingItem(itemId, itemName, quantity, price, category, supplier);

        System.out.print("Enter number of sizes to add: ");
        int numSizes = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numSizes; i++) {
            System.out.print("Enter Size: ");
            String size = scanner.nextLine();
            System.out.print("Enter Color: ");
            String color = scanner.nextLine();
            System.out.print("Enter Quantity for this size/color: ");
            int sizeQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            item.addSizeColor(size, color, sizeQuantity);
        }

        item.generateStockReport();
    }

    private static void manageGrocery() {
        System.out.println("\n=== Grocery Management ===");
        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price per Unit: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Supplier: ");
        String supplier = scanner.nextLine();
        System.out.print("Enter Expiration Date (YYYY-MM-DD): ");
        String expDateStr = scanner.nextLine();
        LocalDate expDate = LocalDate.parse(expDateStr);

        GroceryItem item = new GroceryItem(itemId, itemName, quantity, price, category, supplier, expDate);
        item.generateStockReport();
    }

    private static void manageFurniture() {
        System.out.println("\n=== Furniture Management ===");
        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price per Unit: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Supplier: ");
        String supplier = scanner.nextLine();
        System.out.print("Enter Weight (kg): ");
        double weight = scanner.nextDouble();

        FurnitureItem item = new FurnitureItem(itemId, itemName, quantity, price, category, supplier, weight);
        System.out.print("Is the item well packed? (true/false): ");
        boolean isPacked = scanner.nextBoolean();
        item.setWellPacked(isPacked);
        item.generateStockReport();
    }

    private static void managePerishableItems() {
        System.out.println("\n=== Perishable Items Management ===");
        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price per Unit: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Supplier: ");
        String supplier = scanner.nextLine();
        System.out.print("Enter Production Date (YYYY-MM-DD): ");
        String prodDateStr = scanner.nextLine();
        LocalDate prodDate = LocalDate.parse(prodDateStr);
        System.out.print("Enter Shelf Life (days): ");
        int shelfLife = scanner.nextInt();

        PerishableItem item = new PerishableItem(itemId, itemName, quantity, price, category, supplier, prodDate, shelfLife);
        item.generateStockReport();
    }

    private static void manageProducts() {
        System.out.println("\n=== Product Management ===");
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Supplier: ");
        String supplier = scanner.nextLine();
        System.out.print("Enter Stock Quantity: ");
        int stockQuantity = scanner.nextInt();

        try {
            Product product = new Product(productId, productName, brand, supplier, stockQuantity);
            System.out.println("Product created successfully: " + product);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void manageSuppliers() {
        System.out.println("\n=== Supplier Management ===");
        System.out.print("Enter Supplier ID: ");
        String supplierId = scanner.nextLine();
        System.out.print("Enter Company Name: ");
        String companyName = scanner.nextLine();
        System.out.print("Enter Contact Person: ");
        String contactPerson = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        try {
            Supplier supplier = new Supplier(supplierId, companyName, contactPerson, phone, email);
            System.out.println("Supplier created successfully: " + supplier);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void manageWarehouse() {
        System.out.println("\n=== Warehouse Management ===");
        System.out.print("Enter Warehouse ID: ");
        String warehouseId = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Manager Name: ");
        String managerName = scanner.nextLine();

        try {
            Warehouse warehouse = new Warehouse(warehouseId, location, capacity, managerName);
            System.out.println("Warehouse created successfully: " + warehouse);

            while (true) {
                System.out.println("\n1. Add Stock Movement");
                System.out.println("2. View Current Stock");
                System.out.println("3. View Stock History");
                System.out.println("4. Back to Main Menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Item ID: ");
                        String itemId = scanner.nextLine();
                        System.out.print("Enter Quantity Change: ");
                        int quantity = scanner.nextInt();
                        try {
                            warehouse.addStockMovement(itemId, quantity);
                            System.out.println("Stock movement added successfully");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Current Stock: " + warehouse.getStockMovement());
                        break;
                    case 3:
                        System.out.println("Stock History:");
                        for (String history : warehouse.getStockHistory()) {
                            System.out.println(history);
                        }
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}