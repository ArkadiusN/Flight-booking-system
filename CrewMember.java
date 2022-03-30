public class CrewMember extends Person{
    // Calls super constructor.
    public CrewMember(String name, int passportNumber) {
        super(name, passportNumber);
    }

    // It returns weight of all crew members
    // so 80000 + 525 = 80525;
    // 7 members * 75 = 525.
    public double calculatePersonWeight() {
        return 75;
    }
}//END OF CLASS
