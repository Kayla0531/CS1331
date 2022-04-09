/**
 * represents any Vehicle to get around the city.
 *
 * @author Jiayiw
 * @version 1.0
 */
public abstract class Vehicle {
    private final String id;
    private double earnings; // represents total amount of money gained by this Vehicle
    protected int numMiles;
    protected String[] passengers; // each item represents a name
    protected int peopleCount = 0;
    /**
     * constructor that takes in id, numMiles, passengers.
     *
     * @param id  representing the identifier of the Vehicle, should kept secret
     * @param numMiles  representing the number of miles the Vehicle has travelled
     * @param passengers  an array of String representing passengers aboard, each item represents a name
     */
    public Vehicle(String id, int numMiles, String[] passengers) {
        this.earnings = 0;
        this.id = id;
        this.numMiles = numMiles;
        this.passengers = passengers;
        for (String p : passengers) {
            if (p != null) {
                peopleCount++;
            }
        }
    }
    /**
     * constructor that takes in id, passengers.
     * numMiles set to default 0
     *
     * @param id  representing the identifier of the Vehicle, should kept secret
     * @param passengers  an array of String representing passengers aboard, each item represents a name
     */
    public Vehicle(String id, String[] passengers) {
        this(id, 0, passengers);
    }
    /**
     * checks if this Vehicle can drive the given distance.
     *
     * @param distance  representing the distance to be driven
     * @return true if the Vehicle can drive the distance given, false if not
     */
    public abstract boolean canDrive(int distance);
    /**
     * calculates the cost for the Vehicle to drive if the distance can be driven.
     *
     * @param distance  representing the distance to be driven
     * @return a double representing the cost of this drive
     */
    public abstract double calculateCost(int distance);
    /**
     * updates passengers with the given list of customers if the distance can be driven.
     *
     * @param distance  representing the distance to be driven
     * @param customers representing a list of names
     * @return true if the Vehicle can drive the distance given, false if not
     */
    public abstract boolean addPassenger(int distance, String[] customers);
    /**
     * charges a ride and addes the charge to earnings, also updating numMiles.
     *
     * @param distance  representing the distance to be driven
     */
    public void chargeRide(int distance) {
        if (canDrive(distance)) {
            this.numMiles += distance;
            this.earnings += calculateCost(distance);
        }
    }
    /**
     * overrides equals() from Object.
     *
     * @param other  the Object to be compared with this
     * @return true if this equals to other; false if not
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Vehicle) {
            Vehicle vOther = (Vehicle) other;
            return this.id.equals(vOther.id) && this.numMiles == vOther.numMiles;
        }
        return false;
    }
    /**
     * gives a String description of the Vehicle Object.
     *
     * @return a String representing the Vehicle Object
     */
    public String toString() {
        String strEarnings = printable(earnings);
        return id + " has driven " + numMiles + " miles and has earned " + strEarnings + " dollars.";
    }
    /**
     * helper method, returns a double that is rounded to 2 decimal places.
     *
     * @param num  the value to be rounded
     * @return num rounded to 2 decimal places
     */
    protected String printable(double num) {
        num = Math.round(num * 100);
        num = num / 100;
        String strNum = String.format("%.2f", num);
        return strNum;
    }
    /**
     * getter of earnings of this Object.
     *
     * @return earnings of this Object
     */
    public double getEarnings() {
        return this.earnings;
    }
    /**
     * changes earning.
     *
     * @param newEarnings  new value of earnings
     */
    public void setEarnings(double newEarnings) {
        this.earnings = newEarnings;
    }
    /**
     * getter of numMiles of this Object.
     *
     * @return numMiles of this Object
     */
    public int getNumMiles() {
        return this.numMiles;
    }
    /**
     * changes numMiles.
     *
     * @param newNum  new value of numMiles
     */
    public void setNumMiles(int newNum) {
        this.numMiles = newNum;
    }
}