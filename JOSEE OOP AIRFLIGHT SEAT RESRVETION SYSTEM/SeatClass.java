public enum SeatClass {
    ECONOMY(1.0),
    BUSINESS(2.5),
    FIRST_CLASS(4.0);

    private final double priceMultiplier;

    SeatClass(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    @Override
    public String toString() {
        return name().replace('_', ' ');
    }
} 