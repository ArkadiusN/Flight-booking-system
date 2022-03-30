public abstract class Person {
    // Vars
    private String name;
    private int passportNumber;

    //Getters and Setters.
    public String getName() {
        return name;
    }
    public int getPassportNumber() {
        return passportNumber;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Person(String name, int passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    //Reusable method.
    public abstract double calculatePersonWeight();
}

