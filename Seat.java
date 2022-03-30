public class Seat {
    // Vars
    private int row;
    private int seat;
    private String flyingClass;
    private Passenger allocatedTo;

    // Getters
    public int getRow() {
        return row;
    }
    public int getSeat() {
        return seat;
    }
    public String getFlyingClass() {
        return flyingClass;
    }
    public Passenger getAllocatedTo() {
        return allocatedTo;
    }

    // Setters
    public void setRow(int row) {
        this.row = row;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public void setFlyingClass(String flyingClass) {
        this.flyingClass = flyingClass;
    }
    public void setAllocatedTo(Passenger allocatedTo) {
        this.allocatedTo = allocatedTo;
    }

    // Constructor for Seat Object
    public Seat(int row, int seat, String flyingClass) {
        this.row = row;
        this.seat = seat;
        this.flyingClass = flyingClass;
    }
}
