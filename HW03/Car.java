/**
 * represents a regular car.
 *
 * @author Jiayiw
 * @version 1.0
 */
public class Car extends Vehicle {
    private double rate;
    private double fees;
    private int maxNumMiles;
    /**
     * constructor that takes in id, numMiles, passengers, rate, fees, maxNumMiles.
     *
     * @param id  representing the identifier of the Vehicle, should kept secret
     * @param numMiles  representing the number of miles the Vehicle has travelled
     * @param passengers  an array of String representing passengers aboard, each item represents a name
     * @param rate  representing price of using the car to go 1 mile
     * @param fees  representing a one-time fee to use the car once
     * @param maxNumMiles  representing the total number of miles that the car can drive before retiring
     */
    public Car(String id, int numMiles, String[] passengers, double rate, double fees, int maxNumMiles) {
        super(id, numMiles, passengers);
        this.rate = rate;
        this.fees = fees;
        this.maxNumMiles = maxNumMiles;
    }
    /**
     * constructor that takes in id, numMiles, maxNumMiles.
     *
     * @param id  representing the identifier of the Vehicle, should kept secret
     * @param numMiles  representing the number of miles the Vehicle has travelled
     * @param maxNumMiles  representing the total number of miles that the car can drive before retiring
     */
    public Car(String id, int numMiles, int maxNumMiles) {
        this(id, numMiles, new String[4], 10, 15, maxNumMiles);
    }
    /**
     * constructor that takes in id.
     *
     * @param id  representing the identifier of the Vehicle, should kept secret
     */
    public Car(String id) {
        this(id, 0, new String[4], 10, 15, 200);
    }
    /**
     * checks if this Car can drive the given distance.
     *
     * @param distance  representing the distance to be driven
     * @return true if the Car can drive the distance given, false if not
     */
    public boolean canDrive(int distance) {
        if (distance < 0 || (this.numMiles + distance) > this.maxNumMiles) {
            return false;
        }
        return true;
    }
    /**
     * calculates the cost for the Car to drive if the distance can be driven.
     *
     * @param distance  representing the distance to be driven
     * @return a double representing the cost of this drive
     */
    public double calculateCost(int distance) {
        if (!canDrive(distance)) {
            return -1;
        }
        return rate * distance + fees; // ?? is the fee added or??
    }
    /**
     * updates passengers with the given list of customers if the distance can be driven.
     * cannot update if the given array of people exceeds the capacity of passengers
     *
     * @param distance  representing the distance to be driven
     * @param people representing a list of names
     * @return true if the Car can drive the distance given and people can fit, false if not
     */
    public boolean addPassenger(int distance, String[] people) {
        if (canDrive(distance) && (peopleCount + people.length <= this.passengers.length)) {
            super.chargeRide(distance);
            int index = 0;
            for (String name : people) {
                while (index < this.passengers.length && this.passengers[index] != null) {
                    index++;
                }
                if (index < this.passengers.length && this.passengers[index] == null) {
                    this.passengers[index] = name;
                    peopleCount++;
                }
            }
            return true;
        }
        return false;
    }
    /**
     * overrides equals() from Object.
     *
     * @param other  the Object to be compared with this
     * @return true if this equals to other; false if not
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Car && super.equals(other)) {
            Car carOther = (Car) other;
            return carOther.rate == this.rate && carOther.fees == this.fees && carOther.maxNumMiles == this.maxNumMiles;
        }
        return false;
    }
    /**
     * gives a String description of this Car Object.
     *
     * @return a String representing this Car Object
     */
    public String toString() {
        return "Car " + super.toString() + " It can only drive " + maxNumMiles + " miles." + " It costs "
            + super.printable(rate) + " dollars per mile and there is a one-time fee of "
            + super.printable(fees) + " dollars.";
    }
    /**
     * getter of rate of this Object.
     *
     * @return rate of this Object
     */
    public double getRate() {
        return this.rate;
    }
    /**
     * changes rate.
     *
     * @param newRate  new value of earnings
     */
    public void setRate(double newRate) {
        this.rate = newRate;
    }
    /**
     * getter of fees of this Object.
     *
     * @return fees of this Object
     */
    public double getFees() {
        return this.fees;
    }
    /**
     * changes fees.
     *
     * @param newFees  new value of earnings
     */
    public void setFees(double newFees) {
        this.fees = newFees;
    }
    /**
     * getter of maxNumMiles of this Object.
     *
     * @return maxNumMiles of this Object
     */
    public int getMaxNumMiles() {
        return this.maxNumMiles;
    }
    /**
     * changes maxNumMiles.
     *
     * @param newMaxNum  new value of earnings
     */
    public void setMaxNumMiles(int newMaxNum) {
        this.maxNumMiles = newMaxNum;
    }
}