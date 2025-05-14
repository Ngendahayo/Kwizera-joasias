import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private static int nextReservationId = 1;
    private String reservationId;
    private Passenger passenger;
    private Flight flight;
    private SeatClass seatClass;
    private int seatNumber;
    private LocalDateTime reservationDateTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Reservation(Passenger passenger, Flight flight, SeatClass seatClass, int seatNumber) {
        this.reservationId = String.format("R%04d", nextReservationId++);
        this.passenger = passenger;
        this.flight = flight;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.reservationDateTime = LocalDateTime.now();
    }

    public String getReservationId() {
        return reservationId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public double getTotalPrice() {
        return flight.getPriceForSeatClass(seatClass);
    }

    @Override
    public String toString() {
        return String.format("Reservation ID: %s\n%s\n%s\nSeat: %s Class #%d\nTotal Price: $%.2f\nReservation Date: %s",
                reservationId,
                passenger,
                flight,
                seatClass,
                seatNumber,
                getTotalPrice(),
                reservationDateTime.format(formatter));
    }
}