# Flight Seat Reservation System

A Java-based Flight Seat Reservation System that allows users to manage flight reservations with a user-friendly console interface.

## Features

- View available flights
- Make flight reservations
- View reservation details
- Cancel reservations
- Input validation for all fields
- Seat availability tracking
- Date validation
- User-friendly interface
- Price information for all flights
- Multiple airline support

## Requirements

- Java Development Kit (JDK) 8 or higher
- Any text editor or IDE (e.g., Eclipse, IntelliJ IDEA, VS Code)

## How to Compile and Run

1. Make sure you have Java installed on your system
2. Open a terminal/command prompt
3. Navigate to the directory containing the Java files
4. Compile all Java files:
   ```
   javac *.java
   ```
5. Run the program:
   ```
   java FlightReservationSystem
   ```

## Program Structure

The program consists of the following classes:

1. `FlightReservationSystem.java` - Main class with the program entry point and user interface
2. `Flight.java` - Manages flight information and seat availability
3. `Passenger.java` - Stores passenger information
4. `Reservation.java` - Handles reservation details and management

## Usage

1. When you start the program, you'll see a menu with the following options:
   - View Available Flights
   - Make a Reservation
   - View Reservation
   - Cancel Reservation
   - Exit

2. Follow the on-screen prompts to perform your desired action.

3. The program includes validation for:
   - Flight numbers (format: F001)
   - Passenger names (letters and spaces only)
   - Email addresses
   - Phone numbers (10 digits)
   - Seat numbers
   - Reservation IDs (format: R0001)

## Available Airlines and Routes

The system includes flights from the following airlines:

### RwandAir
- International Routes:
  - Kigali to London
  - Kigali to Dubai
  - Kigali to New York
- Regional Routes:
  - Kigali to Nairobi
  - Kigali to Addis Ababa
  - Kigali to Johannesburg

### Other Airlines
- Ethiopian Airlines (Addis Ababa - Kigali)
- Kenya Airways (Nairobi - Kigali)
- South African Airways (Johannesburg - Kigali)
- Turkish Airlines (Istanbul - Kigali)
- Qatar Airways (Doha - Kigali)
- Emirates (Dubai - Kigali)

## Sample Data

The program comes pre-loaded with sample flights:
- F001: New York to London
- F002: London to Paris
- F003: Paris to Tokyo

## Error Handling

The program includes comprehensive error handling for:
- Invalid input formats
- Non-existent flights
- Already reserved seats
- Invalid reservation IDs
- Date validation
- Price validation 