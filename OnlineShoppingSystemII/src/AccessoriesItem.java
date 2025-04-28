import java.util.ArrayList;
import java.util.List;

public class AccessoriesItem extends ShoppingItem {
    private String category;
    private List<Review> reviews;
    private double averageRating;

    public static class Review {
        private Customer customer;
        private int rating; // 1-5 stars
        private String comment;
        private String date;

        public Review(Customer customer, int rating, String comment, String date) {
            this.customer = customer;
            this.rating = Math.min(5, Math.max(1, rating)); // Ensure rating is between 1-5
            this.comment = comment;
            this.date = date;
        }

        // Getters
        public Customer getCustomer() { return customer; }
        public int getRating() { return rating; }
        public String getComment() { return comment; }
        public String getDate() { return date; }

        @Override
        public String toString() {
            return String.format("Rating: %d/5\nBy: %s\nDate: %s\nComment: %s\n",
                    rating, customer.customerName(), date, comment);
        }
    }

    public AccessoriesItem(String itemId, String itemName, String itemDescription, double price,
                           int stockAvailable, String category) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.category = category;
        this.reviews = new ArrayList<>();
        this.averageRating = 0.0;
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
        invoice.append("=== Accessories Invoice ===\n")
                .append(super.toString()).append("\n")
                .append("Category: ").append(category).append("\n")
                .append("Average Rating: ").append(String.format("%.1f/5.0", averageRating)).append("\n")
                .append("Number of Reviews: ").append(reviews.size()).append("\n")
                .append("\nCustomer Details:\n")
                .append(customer.toString());
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 &&
                category != null && !category.trim().isEmpty();
    }

    public void addReview(Customer customer, int rating, String comment, String date) {
        Review review = new Review(customer, rating, comment, date);
        reviews.add(review);
        updateAverageRating();
    }

    private void updateAverageRating() {
        if (reviews.isEmpty()) {
            averageRating = 0.0;
            return;
        }

        double sum = reviews.stream()
                .mapToInt(Review::getRating)
                .sum();
        averageRating = sum / reviews.size();
    }

    public String getReviewsSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== Reviews for ").append(itemName).append(" ===\n")
                .append("Average Rating: ").append(String.format("%.1f/5.0", averageRating)).append("\n")
                .append("Total Reviews: ").append(reviews.size()).append("\n\n");

        for (Review review : reviews) {
            summary.append(review.toString()).append("\n");
        }

        return summary.toString();
    }

    // Getters
    public String getCategory() { return category; }
    public List<Review> getReviews() { return new ArrayList<>(reviews); }
    public double getAverageRating() { return averageRating; }
}