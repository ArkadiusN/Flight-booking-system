import java.io.File;

public class Aircraft {
    // Vars
    private String make;
    private String model;
    private String tailNumber;
    private double craftWeight;
    private double maximumTakeoffWeight;
    private File layoutFile;

    // Getters
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getTailNumber() {
        return tailNumber;
    }
    public double getCraftWeight() {
        return craftWeight;
    }
    public double getMaximumTakeoffWeight() {
        return maximumTakeoffWeight;
    }
    public File getLayoutFile() {
        return layoutFile;
    }

    // Setters
    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }
    public void setCraftWeight(double craftWeight) {
        this.craftWeight = craftWeight;
    }
    public void setMaximumTakeoffWeight(double maximumTakeoffWeight) {
        this.maximumTakeoffWeight = maximumTakeoffWeight;
    }
    public void setLayoutFile(File layoutFile) {
        this.layoutFile = layoutFile;
    }

    // Aircraft constructor
    public Aircraft(String make, String model, String tailNumber, double craftWeight, double maximumTakeoffWeight, File layoutFile) {
        this.make = make;
        this.model = model;
        this.tailNumber = tailNumber;
        this.craftWeight = craftWeight;
        this.maximumTakeoffWeight = maximumTakeoffWeight;
        this.layoutFile = layoutFile;
    }

    /**
     * Overwritten toString method.
     * @return a data as required.
     */
    public String toString() {
        return "Make: " + make  +
                ", Model: " + model  +
                ", TailNumber: " + tailNumber  +
                ", Weight: " + craftWeight +
                ", Maximum takeoff" + "\n" + "weight: " + maximumTakeoffWeight;
    }
}// END OF CLASS
