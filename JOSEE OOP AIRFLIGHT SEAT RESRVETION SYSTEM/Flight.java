import java.time.LocalDate;
import java.util.BitSet;
import java.util.EnumMap;
import java.util.Map;

public class Flight {
    private String flightNumber;
    private String airline;
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private double basePrice;
    private Map<SeatClass, BitSet> seatAvailability;
    private Map<SeatClass, Integer> seatCapacity;
    private static int nextFlightNumber = 1;

    public Flight(String flightNumber, String airline, String departureCity, String arrivalCity, 
                 LocalDate departureDate, double basePrice) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.basePrice = basePrice;
        
        // Initialize seat capacities based on typical aircraft configurations
        this.seatCapacity = new EnumMap<>(SeatClass.class);
        this.seatCapacity.put(SeatClass.ECONOMY, 150);
        this.seatCapacity.put(SeatClass.BUSINESS, 30);
        this.seatCapacity.put(SeatClass.FIRST_CLASS, 10);
        
        // Initialize seat availability
        this.seatAvailability = new EnumMap<>(SeatClass.class);
        for (SeatClass seatClass : SeatClass.values()) {
            BitSet seats = new BitSet(seatCapacity.get(seatClass));
            seats.set(0, seatCapacity.get(seatClass), true);
            seatAvailability.put(seatClass, seats);
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getPriceForSeatClass(SeatClass seatClass) {
        return basePrice * seatClass.getPriceMultiplier();
    }

    public boolean isSeatAvailable(SeatClass seatClass, int seatNumber) {
        if (seatNumber < 1 || seatNumber > seatCapacity.get(seatClass)) {
            return false;
        }
        return seatAvailability.get(seatClass).get(seatNumber - 1);
    }

    public boolean reserveSeat(SeatClass seatClass, int seatNumber) {
        if (isSeatAvailable(seatClass, seatNumber)) {
            seatAvailability.get(seatClass).clear(seatNumber - 1);
            return true;
        }
        return false;
    }

    public boolean cancelSeat(SeatClass seatClass, int seatNumber) {
        if (seatNumber < 1 || seatNumber > seatCapacity.get(seatClass)) {
            return false;
        }
        if (!isSeatAvailable(seatClass, seatNumber)) {
            seatAvailability.get(seatClass).set(seatNumber - 1);
            return true;
        }
        return false;
    }

    public int getAvailableSeatsCount(SeatClass seatClass) {
        return seatAvailability.get(seatClass).cardinality();
    }

    public int getTotalSeatsCount(SeatClass seatClass) {
        return seatCapacity.get(seatClass);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Flight: %s | Airline: %s | From: %s | To: %s | Date: %s\n",
                flightNumber, airline, departureCity, arrivalCity, departureDate));
        
        for (SeatClass seatClass : SeatClass.values()) {
            sb.append(String.format("%s Class: Available %d/%d seats | Price: $%.2f\n",
                    seatClass,
                    getAvailableSeatsCount(seatClass),
                    getTotalSeatsCount(seatClass),
                    getPriceForSeatClass(seatClass)));
        }
        return sb.toString();
    }
} 