import java.util.*;
/**
 * This a main class to test Person.java
 *
 * @author Natan Woldemichael
 * @version 1.0
 * @see
 */
// Class representing the airline reservation system
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Flight> flights = new ArrayList<>();
    private static Map<String, Passenger> passengers = new HashMap<>();

    public static void main(String[] args) {
        initializeFlights();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("----- Airline Reservation System -----");
            System.out.println("1. View available flights");
            System.out.println("2. Make a reservation");
            System.out.println("3. View passenger details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewAvailableFlights();
                    break;
                case 2:
                    makeReservation();
                    break;

                case 3:
                    viewPassengerDetails();
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }

        System.out.println("Thank you for using the Airline Reservation System!");
    }

    private static void initializeFlights() {
        // Initialize flights with dummy data
        flights.add(new Flight("F101", "Flight 101", "New York", "London", "2023-07-06 08:00", 200));
        flights.add(new Flight("F102", "Flight 102", "London", "Paris", "2023-07-07 10:30", 150));
        // Add more flights as needed
    }

    private static void viewAvailableFlights() {
        System.out.println("----- Available Flights -----");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    private static void makeReservation() {
        System.out.println("----- Make a Reservation -----");
        System.out.print("Enter the flight number: ");
        String flightNumber = scanner.nextLine();
        Flight flight = findFlight(flightNumber);

        if (flight == null) {
            System.out.println("Flight not found!");
            return;
        }

        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        if (passengers.containsKey(passengerName)) {
            System.out.println("Passenger already exists!");
            return;
        }

        System.out.print("Enter passenger age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter passenger gender (M/F): ");
        char gender = scanner.nextLine().charAt(0);

        Passenger passenger = new Passenger(passengerName, age, gender);
        passengers.put(passengerName, passenger);
        flight.addPassenger(passenger);

        System.out.println("Reservation successful!");
    }

    private static void viewPassengerDetails() {
        System.out.println("----- Passenger Details -----");
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        Passenger passenger = passengers.get(passengerName);

        if (passenger == null) {
            System.out.println("Passenger not found!");
        } else {
            System.out.println(passenger);
        }
    }

    private static Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
}

// Class representing a Flight
class Flight {
    private String flightNumber;
    private String flightName;
    private String source;
    private String destination;
    private String departureTime;
    private int capacity;
    private List<Passenger> passengers;

    public Flight(String flightNumber, String flightName, String source, String destination, String departureTime, int capacity) {
        this.flightNumber = flightNumber;
        this.flightName = flightName;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.passengers = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    @Override
    public String toString() {
        return "Flight Number: " + flightNumber +
                ", Flight Name: " + flightName +
                ", Source: " + source +
                ", Destination: " + destination +
                ", Departure Time: " + departureTime +
                ", Capacity: " + capacity +
                ", Available Seats: " + (capacity - passengers.size());
    }
}

// Class representing a Passenger
class Passenger {
    private String name;
    private int age;
    private char gender;

    public Passenger(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", Age: " + age +
                ", Gender:" + gender;
    }
}