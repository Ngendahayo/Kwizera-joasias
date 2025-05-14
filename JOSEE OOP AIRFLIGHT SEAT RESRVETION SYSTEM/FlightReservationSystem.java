import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlightReservationSystem {
    private static List<Flight> flights = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        initializeFlights();
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getValidIntInput("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    displayAvailableFlights();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewReservation();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    running = false;
                    System.out.println("\nThank you for using the Flight Reservation System!");
                    break;
            }
        }
        scanner.close();
    }

    private static void initializeFlights() {
        // International flights
        flights.add(new Flight("F001", "RwandAir", "Kigali", "London", LocalDate.now().plusDays(1), 850.00));
        flights.add(new Flight("F002", "RwandAir", "Kigali", "Dubai", LocalDate.now().plusDays(2), 750.00));
        flights.add(new Flight("F003", "RwandAir", "Kigali", "New York", LocalDate.now().plusDays(3), 1200.00));
        
        // African regional flights
        flights.add(new Flight("F004", "RwandAir", "Kigali", "Nairobi", LocalDate.now().plusDays(1), 250.00));
        flights.add(new Flight("F005", "RwandAir", "Kigali", "Addis Ababa", LocalDate.now().plusDays(2), 280.00));
        flights.add(new Flight("F006", "RwandAir", "Kigali", "Johannesburg", LocalDate.now().plusDays(3), 450.00));
        
        // Other major airlines
        flights.add(new Flight("F007", "Ethiopian Airlines", "Addis Ababa", "Kigali", LocalDate.now().plusDays(1), 270.00));
        flights.add(new Flight("F008", "Kenya Airways", "Nairobi", "Kigali", LocalDate.now().plusDays(2), 260.00));
        flights.add(new Flight("F009", "South African Airways", "Johannesburg", "Kigali", LocalDate.now().plusDays(3), 440.00));
        
        // International connections
        flights.add(new Flight("F010", "Turkish Airlines", "Istanbul", "Kigali", LocalDate.now().plusDays(2), 780.00));
        flights.add(new Flight("F011", "Qatar Airways", "Doha", "Kigali", LocalDate.now().plusDays(3), 820.00));
        flights.add(new Flight("F012", "Emirates", "Dubai", "Kigali", LocalDate.now().plusDays(4), 850.00));
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Flight Reservation System ===");
        System.out.println("1. View Available Flights");
        System.out.println("2. Make a Reservation");
        System.out.println("3. View Reservation");
        System.out.println("4. Cancel Reservation");
        System.out.println("5. Exit");
        System.out.println("===============================");
    }

    private static void displayAvailableFlights() {
        System.out.println("\n=== Available Flights ===");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    private static void makeReservation() {
        displayAvailableFlights();
        String flightNumber = getValidFlightNumber();
        Flight selectedFlight = findFlight(flightNumber);

        if (selectedFlight == null) {
            System.out.println("Invalid flight number!");
            return;
        }

        System.out.println("\nEnter Passenger Details:");
        String name = getValidName();
        String email = getValidEmail();
        String phone = getValidPhone();

        Passenger passenger = new Passenger(name, email, phone);
        
        // Select seat class
        SeatClass seatClass = selectSeatClass();
        int seatNumber = getValidSeatNumber(selectedFlight, seatClass);

        Reservation reservation = new Reservation(passenger, selectedFlight, seatClass, seatNumber);
        reservations.add(reservation);
        selectedFlight.reserveSeat(seatClass, seatNumber);

        System.out.println("\nReservation successful!");
        System.out.println("Reservation ID: " + reservation.getReservationId());
        System.out.println(reservation);
    }

    private static SeatClass selectSeatClass() {
        System.out.println("\nAvailable Seat Classes:");
        for (SeatClass seatClass : SeatClass.values()) {
            System.out.printf("%d. %s Class\n", seatClass.ordinal() + 1, seatClass);
        }
        
        int choice = getValidIntInput("Select seat class (1-3): ", 1, 3);
        return SeatClass.values()[choice - 1];
    }

    private static int getValidSeatNumber(Flight flight, SeatClass seatClass) {
        while (true) {
            System.out.printf("Enter %s class seat number (1-%d): ", 
                    seatClass, flight.getTotalSeatsCount(seatClass));
            try {
                int seatNumber = Integer.parseInt(scanner.nextLine());
                if (seatNumber >= 1 && seatNumber <= flight.getTotalSeatsCount(seatClass)) {
                    if (flight.isSeatAvailable(seatClass, seatNumber)) {
                        return seatNumber;
                    }
                    System.out.println("Seat is already reserved!");
                } else {
                    System.out.println("Invalid seat number!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private static void viewReservation() {
        String reservationId = getValidReservationId();
        Reservation reservation = findReservation(reservationId);

        if (reservation != null) {
            System.out.println("\n=== Reservation Details ===");
            System.out.println(reservation);
        } else {
            System.out.println("Reservation not found!");
        }
    }

    private static void cancelReservation() {
        String reservationId = getValidReservationId();
        Reservation reservation = findReservation(reservationId);

        if (reservation != null) {
            reservation.getFlight().cancelSeat(reservation.getSeatClass(), reservation.getSeatNumber());
            reservations.remove(reservation);
            System.out.println("Reservation cancelled successfully!");
        } else {
            System.out.println("Reservation not found!");
        }
    }

    // Helper methods for input validation
    private static int getValidIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private static String getValidFlightNumber() {
        while (true) {
            System.out.print("Enter flight number: ");
            String input = scanner.nextLine().trim();
            if (input.matches("F\\d{3}")) {
                return input;
            }
            System.out.println("Invalid flight number format! Use format F001");
        }
    }

    private static String getValidName() {
        while (true) {
            System.out.print("Enter passenger name: ");
            String input = scanner.nextLine().trim();
            if (input.matches("[A-Za-z ]{2,50}")) {
                return input;
            }
            System.out.println("Invalid name! Use only letters and spaces (2-50 characters)");
        }
    }

    private static String getValidEmail() {
        while (true) {
            System.out.print("Enter email: ");
            String input = scanner.nextLine().trim();
            if (input.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                return input;
            }
            System.out.println("Invalid email format!");
        }
    }

    private static String getValidPhone() {
        while (true) {
            System.out.print("Enter phone number: ");
            String input = scanner.nextLine().trim();
            if (input.matches("\\d{10}")) {
                return input;
            }
            System.out.println("Invalid phone number! Enter 10 digits");
        }
    }

    private static String getValidReservationId() {
        while (true) {
            System.out.print("Enter reservation ID: ");
            String input = scanner.nextLine().trim();
            if (input.matches("R\\d{4}")) {
                return input;
            }
            System.out.println("Invalid reservation ID format! Use format R0001");
        }
    }

    private static Flight findFlight(String flightNumber) {
        return flights.stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);
    }

    private static Reservation findReservation(String reservationId) {
        return reservations.stream()
                .filter(r -> r.getReservationId().equals(reservationId))
                .findFirst()
                .orElse(null);
    }
} 