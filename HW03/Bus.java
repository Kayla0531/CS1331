/**
 * represents a regular bus.
 *
 * @author Jiayiw
 * @version 1.0
 */
public class Bus extends Vehicle {
    private String location;
    private int stopsPerMile;
    /**
     * constructor that takes in id, numMiles, location, stopsPerMile.
     *
     * @param id  representing the identifier of the Vehicle, should kept secret
     * @param numMiles  representing the number of miles the Vehicle has travelled
     * @param location  represents the general area of the bus route
     * @param stopsPerMile  represents how many times a bus stops in one mile
     */
    public Bus(String id, int numMiles, String location, int stopsPerMile) {
        super(id, numMiles, new String[20]);
        this.location = location;
        this.stopsPerMile = stopsPerMile;
    }
    /**
     * constructor that takes in id, location.
     *
     * @param id  representing the identifier of the Vehicle, should kept secret
     * @param location  represents the general area of the bus route
     */
    public Bus(String id, String location) {
        this(id, 0, location, 2);
    }
    /**
     * checks if this Bus can drive the given distance.
     *
     * @param distance  representing the distance to be driven
     * @return true if the Bus can drive the distance given, false if not
     */
    public boolean canDrive(int distance) {
        return distance >= 0;
    }
    /**
     * calculates the cost for the Bus to drive if the distance can be driven.
     *
     * @param distance  representing the distance to be driven
     * @return a double representing the cost of this drive
     */
    public double calculateCost(int distance) {
        if (!canDrive(distance)) {
            return -1;
        }
        return distance * 3 / stopsPerMile;
    }
    /**
     * updates passengers with the given list of customers if the distance can be driven.
     * fits people in by sequence
     *
     * @param distance  representing the distance to be driven
     * @param people representing a list of names
     * @return true if the Bus can drive the distance given, false if not
     */
    public boolean addPassenger(int distance, String[] people) {
        if (!canDrive(distance)) {
            return false;
        }
        numMiles += distance; // updates numMiles here, once for each trip
        int index = 0;
        for (String person : people) {
            while (index < passengers.length && passengers[index] != null) {
                index++;
            }
            if (index >= passengers.length) {
                break;
            } else {
                passengers[index] = person;
                chargeRide(distance);
                continue;
            }
        }
        return true;
    }
    /**
     * charges a ride and addes the charge to earningss.
     * overrides chargeRide() from Vehicle, only updates earnings for this class
     *
     * @param distance  representing the distance to be driven
     */
    @Override
    public void chargeRide(int distance) {
        if (canDrive(distance)) {
            this.setEarnings(this.getEarnings() + calculateCost(distance));
        }
    }
    /**
     * overrides equals() from Object.
     *
     * @param other  the Object to be compared with this
     * @return true if this equals to other; false if not
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Bus && super.equals(other)) {
            Bus busOther = (Bus) other;
            return busOther.location.equals(this.location) && busOther.stopsPerMile == this.stopsPerMile;
        }
        return false;
    }
    /**
     * gives a String description of this Bus Object.
     *
     * @return a String representing this Bus Object
     */
    public String toString() {
        return "Bus " + super.toString() + " This bus drives around " + location
            + " and makes " + stopsPerMile + " stops per mile.";
    }
    /**
     * getter of location of this Object.
     *
     * @return location of this Object
     */
    public String getLocation() {
        return this.location;
    }
    /**
     * changes location.
     *
     * @param newLocation  new location
     */
    public void setLocation(String newLocation) {
        this.location = newLocation;
    }
    /**
     * getter of stopsPerMile of this Object.
     *
     * @return stopsPerMile of this Object
     */
    public int getStopsPerMile() {
        return this.stopsPerMile;
    }
    /**
     * changes stopsPerMile.
     *
     * @param newStops  new value of stopsPerMile
     */
    public void setStopsPerMile(int newStops) {
        this.stopsPerMile = newStops;
    }
}