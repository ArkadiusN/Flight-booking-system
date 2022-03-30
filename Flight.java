import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Flight{
    // Vars
    private int flightNumber;
    private Aircraft craft;
    private String startLocation;
    private String endLocation;
    private double distance;
    public ArrayList<Seat> seats;
    public ArrayList<CrewMember> crew;

    // Getters
    public int getFlightNumber() {
        return flightNumber;
    }
    public Aircraft getCraft() {
        return craft;
    }
    public String getStartLocation() {
        return startLocation;
    }
    public String getEndLocation() {
        return endLocation;
    }
    public double getDistance() {
        return distance;
    }

    // Setters
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }
    public void setCraft(Aircraft craft) {
        this.craft = craft;
    }
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     *
     * @param flightNumber number assigned to flight to differentiate them.
     * @param craft an aircraft and all details about it.
     * @param startLocation a location of takeoff.
     * @param endLocation a location of landing.
     * @param distance a distance to travel through.
     */
    public Flight(int flightNumber, Aircraft craft, String startLocation, String endLocation, double distance) {
        this.flightNumber = flightNumber;
        this.craft = craft;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;
        seats = new ArrayList<Seat>();
        crew= new ArrayList<CrewMember>();

        // Use the file, create new object and
        // add it to seats arrayList based on the flight class.
        try {
            Scanner in = new Scanner(craft.getLayoutFile());
            int num = 0; //Used to go through each seat.
            while(in.hasNextLine()){
                String tempContRow = in.nextLine();
                String[] tempCont = tempContRow.split(",");
                for (int i = 0; i < tempCont.length; i++) {
                        if (tempCont[i].equals("F")) {
                            seats.add(new Seat(i, num , "first"));
                        } else if (tempCont[i].equals("E")) {
                            seats.add(new Seat(i, num , "economy"));
                        }
                }
                num++;
            }
        }catch (FileNotFoundException e){
            System.out.println("Can't find layout file.");
        }
    }

    /** Doesn't work when upgrading or downgrading passengers,
     * I believe thus there is a difference comparing to appendix.
     * @return a calculation of the Aircraft weight.
     */
    public double calculateTakeOffWeight(){
        double takeoff = craft.getCraftWeight();

        // Calculating the weight of passengers.
        for (Seat seat : seats) {
            if (seat.getAllocatedTo() != null) {
                takeoff += seat.getAllocatedTo().calculatePersonWeight();
            }
        }

        // Calculating the weight of the crew members.
        for (CrewMember crewMember : crew) {
            takeoff += crewMember.calculatePersonWeight();
        }

        // Check if the aircraft is not too heavy.
        if(takeoff < craft.getMaximumTakeoffWeight()){
            return takeoff;
        }
        return -1;
    }


    /** This function also upgrades or downgrades the passenger's flight class if necessary.
     * @param passenger a current passenger that needs to be allocated to seat based on his/her class.
     * @return allocated seat of this passenger
     */
    public int bookSeat(Passenger passenger) {
        // Assigning the correct class to passengers.
        for (Seat seat : seats) {
            if (passenger.getFlightClass().equals(seat.getFlyingClass()) && seat.getAllocatedTo() == null) {
                seat.setAllocatedTo(passenger);
                return 1;
            }
        }

        // Downgrading or upgrading passenger's flight class looking
        // at the seats still available.
        if(passenger.getFlightClass().equals("first")){
            for (Seat seat : seats) {
                if (seat.getAllocatedTo() == null) {
                    seat.setAllocatedTo(passenger);
                    return 3;
                }
            }
        }else{
            for (Seat seat : seats) {
                if (seat.getAllocatedTo() == null) {
                    seat.setAllocatedTo(passenger);
                    return 2;
                }
            }
        }
        return -1;
    }

    /**
     * @return all information needed for each flight
     */
    public String toString() {
        String linesSec = "-".repeat(13);  // Long line for layout.
        String nextL = "\n";               // Next line.
        int countFC = 0;         // Counter First Class.
        int countEC= 0;          // Counter Economy Class.
        int countES =0;          // Counter Empty Seats.

        // Count all the first class passengers.
        for (Seat seat : seats) {
            if (seat.getFlyingClass().equals("first") && seat.getAllocatedTo() != null) {
                countFC += 1;
            }
            // Count all the economy class passengers.
            else if (seat.getFlyingClass().equals("economy") && seat.getAllocatedTo() != null) {
                countEC += 1;
            }
            // Count all the empty seats.
            else {
                countES += 1;
            }
        }

        return (nextL + linesSec + nextL + "*" + " Flight #"
                + flightNumber + " *" + nextL + linesSec + nextL
                + "From: " + startLocation + nextL
                + "To: " + endLocation + nextL
                + "Distance: " + distance + nextL
                + "First class passengers: " + countFC + nextL
                + "Economy passengers: " + countEC + nextL
                + "Unallocated seats: " + countES + nextL
                + "Crew: " + crew.get(0).getName() + "," +
                crew.get(1).getName()+ "," +
                crew.get(2).getName()+ "," +
                crew.get(3).getName()+ "," + nextL +
                crew.get(4).getName()+ "," +
                crew.get(5).getName()+ "," +
                crew.get(6).getName()+ nextL
                + craft.toString() + nextL);
    }
}// END OF CLASS
