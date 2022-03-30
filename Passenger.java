public class Passenger extends Person {
    // Vars
    private int holdBags;
    private String flightClass;

    // Calls super constructor.
    public Passenger(String name, int passportNumber, String flightClass,int holdBags) {
        super(name, passportNumber);
        this.holdBags = holdBags;
        this.flightClass = flightClass;
    }

    // Getters
    public int getHoldBags() {
        return holdBags;
    }
    public String getFlightClass() {
        return flightClass;
    }

    // Setters
    public void setHoldBags(int holdBags) {
        this.holdBags = holdBags;
    }
    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    // It returns the weight of individual based on this person
    // flight class. First (87.5 plus 25 for each bag),
    // Economy (80 plus 25 for each bag).
    public double calculatePersonWeight() {
        if(flightClass.equals("first")){
            return 87.5  + (25 * getHoldBags());
        }
        else if(flightClass.equals("economy")){
                return 80 + (25 * getHoldBags());
        }
        return 0;
    }
}// END OF CLASS
