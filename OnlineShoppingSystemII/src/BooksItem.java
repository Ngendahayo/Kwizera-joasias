public class BooksItem extends ShoppingItem {
    private String isbn;
    private String edition;
    private String author;
    private String printQuality;

    public BooksItem(String itemId, String itemName, String itemDescription, double price,
                     int stockAvailable, String isbn, String edition, String author, String printQuality) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.isbn = isbn;
        this.edition = edition;
        this.author = author;
        this.printQuality = printQuality;
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
        invoice.append("=== Book Invoice ===\n")
                .append(super.toString()).append("\n")
                .append("ISBN: ").append(isbn).append("\n")
                .append("Edition: ").append(edition).append("\n")
                .append("Author: ").append(author).append("\n")
                .append("Print Quality: ").append(printQuality).append("\n")
                .append("\nCustomer Details:\n")
                .append(customer.toString());
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 &&
                validateISBN() &&
                edition != null && !edition.trim().isEmpty() &&
                author != null && !author.trim().isEmpty() &&
                printQuality != null && !printQuality.trim().isEmpty();
    }

    private boolean validateISBN() {
        if (isbn == null || isbn.trim().isEmpty()) {
            return false;
        }

        // Remove hyphens and spaces
        String cleanISBN = isbn.replaceAll("[-\\s]", "");

        // Check for ISBN-10 or ISBN-13
        if (cleanISBN.length() != 10 && cleanISBN.length() != 13) {
            return false;
        }

        // Basic format check (all digits, except last character can be 'X' for ISBN-10)
        if (cleanISBN.length() == 10) {
            return cleanISBN.substring(0, 9).matches("\\d+") &&
                    (cleanISBN.charAt(9) == 'X' || Character.isDigit(cleanISBN.charAt(9)));
        } else {
            return cleanISBN.matches("\\d+");
        }
    }

    // Getters
    public String getIsbn() { return isbn; }
    public String getEdition() { return edition; }
    public String getAuthor() { return author; }
    public String getPrintQuality() { return printQuality; }
}