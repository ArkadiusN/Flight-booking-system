import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * Implementation of a flight system that considers different aspects of a successful and unsuccessful flight.
 * @author Arkadiusz Nowacki
 * @version 17.0.1
**/
public class Main {
    // Create a fleet and flight array.
    public static ArrayList<Aircraft> fleet = new ArrayList();
    public static ArrayList<Flight> flights = new ArrayList();

    // Colors for differentiation of text.
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_WHITE = "\u001B[0m";
    public static final String TEXT_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        System.out.println(TEXT_BLUE + "PAP airways flight allocation system \n\nAdding fleet to system... \n\n");
        // Create three aircraft in the fleet.
        fleet.add(new Aircraft("Skycoach", "387", "PAP-JX1", 800000,
                817000, new File("387Seats.txt")));
        fleet.add(new Aircraft("Skycoach", "387", "PAP-JX2", 800000,
                817000, new File("387Seats.txt")));
        fleet.add(new Aircraft("Lambdadier", "293", "PAP-JA1", 324334,
                336900, new File("293Seats.txt")));
        fleet.add(new Aircraft("Lambdadier", "293", "PAP-JA2", 324334,
                336900, new File("293Seats.txt")));

        // Reassign some values of aircraft.
        fleet.get(3).setModel("293-8");
        fleet.get(3).setTailNumber("PAP-JA3");
        fleet.get(3).setCraftWeight(324380);

        // Create several flight using the aircraft.
        System.out.println("Adding flights...\n\n");
        flights.add(new Flight(1, fleet.get(0), "Heathrow", "JFK", 5149));
        flights.add(new Flight(2, fleet.get(1), "Gatwick", "Indira Gandhi", 6759));
        flights.add(new Flight(3, fleet.get(0), "JFK", "Heathrow", 5149));
        flights.add(new Flight(4, fleet.get(2), "Heathrow", "Charles De Gaulle", 482));
        flights.add(new Flight(5, fleet.get(2), "Charles De Gaulle", "Malpensa", 563));
        flights.add(new Flight(6, fleet.get(2), "Malpensa", "Edinburgh", 2300));
        flights.add(new Flight(7, fleet.get(1), "Indira Gandhi", "Schiphol", 6213));
        flights.add(new Flight(8, fleet.get(2), "Edinburgh", "Madrid", 1300));

        // Reassign flight four.
        flights.get(4).setCraft(fleet.get(3));
        flights.get(4).setStartLocation("Orly");

        // Test file handling
        System.out.println(TEXT_RED + "Testing file handling, should fail gracefully" + TEXT_WHITE);
        Aircraft fa = new Aircraft("Skycoach", "377", "PAP-JXX", 800000,
                817000, new File("377Seats.txt"));
        Flight ff = new Flight(999, fa, "Heathrow", "Gatwick", 28);

        System.out.println(TEXT_BLUE + "\n\nAdding Passengers...");
        // Add some passengers.
        File[] passengerData = {new File("flight1.txt"),
                new File("flight2.txt"),
                new File("flight3.txt"),
                new File("flight4.txt"),
                new File("flight5.txt"),
                new File("flight6.txt"),
                new File("flight7.txt"),
                new File("flight8.txt")};

        Scanner in;
        for (int i = 0; i < passengerData.length; i++) {
            File passengerFile = passengerData[i];

            System.out.println(TEXT_GREEN + "\nEntering passengers for flight #" + (i + 1) + TEXT_WHITE + "\n");

            try {
                in = new Scanner(passengerFile);
                while (in.hasNextLine()) {
                    String p_str = in.nextLine();
                    String[] p_data = p_str.split(",");
                    if (p_data[0].equals("passenger")) {
                        // Make an attempt to add a booking to the flight.
                        int bookingOutcome = processPassenger(p_data, flights.get(i));
                        if (bookingOutcome == 2) {
                            System.out.println("upgrading " + p_data[1]);
                        } else if (bookingOutcome == 3) {
                            System.out.println("downgrading " + p_data[1]);
                        } else if (bookingOutcome == -1) {
                            System.out.println(TEXT_RED + "plane full can't book " + TEXT_WHITE + p_data[1]);
                        }
                    } else {
                        flights.get(i).crew.add(new CrewMember(p_data[1], Integer.parseInt(p_data[2])));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("can't find file");
                System.exit(0);
            }

        }

        // As last check takeoff weights and print a summary of the flight accordingly.
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
            double flightWeight = flights.get(i).calculateTakeOffWeight();
            if (flightWeight == -1) {
                System.out.println(TEXT_RED + "flight #" + (i + 1) + " is too heavy to take off!" + TEXT_WHITE);
            } else {
                System.out.println(TEXT_GREEN + "flight #" + (i + 1) + " weighs " + flightWeight + "KG" + TEXT_WHITE);
            }
        }
    }

    /**
     *
     * @param p_data current passenger data.
     * @param f a current flight.
     * @return allocated seat in the flight.
     */
    public static int processPassenger(String[] p_data, Flight f) {
        return f.bookSeat(new Passenger(p_data[1], Integer.parseInt(p_data[2]), p_data[3], Integer.parseInt(p_data[4])));
    }
}
