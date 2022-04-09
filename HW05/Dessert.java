/**
 * The superclass for dessert classes.
 *
 * @author Jiayiw
 * @version 1.1
 */
public abstract class Dessert implements Comparable<Dessert> {
    private String flavor;
    private double sweetness;
    /**
     * constructor that takes in flavor and sweetness.
     *
     * @param flavor  the flavor of the Dessert
     * @param sweetness  the sweetness of the Dessert
     */
    public Dessert(String flavor, double sweetness) {
        this.flavor = flavor;
        this.sweetness = sweetness;
    }
    /**
     * constructor that takes in nothing.
     * defaults flavor to "vanilla", sweetness to 25.0
     */
    public Dessert() {
        this("vanilla", 25.0);
    }
    /**
     * gives a String description of the Dessert Object.
     *
     * @return a String representing the Dessert Object
     */
    public String toString() {
        return String.format("This is a %s dessert with a sweetness of %.2f.", flavor, sweetness);
    }
    /**
     * overrides equals() from Object.
     *
     * @param other  the Dessert to be compared with this
     * @return true if this equals to other; false if not
     */
    public boolean equals(Object other) {
        if (this == null || !(other instanceof Dessert)) {
            return false;
        } else {
            Dessert otherDessert = (Dessert) other;
            return this.getFlavor().equals(otherDessert.getFlavor())
                && this.getSweetness() == otherDessert.getSweetness();
        }
    }
    /**
     * compares this Dessert to another given Dessert based on their sweetness and flavor.
     *
     * @param other  the other Dessert to be compared with
     * @return 0 if equal, a positive int if this Dessert is greater than the other Dessert, vice versa
     */
    public int compareTo(Dessert other) {
        if (this.getSweetness() == other.getSweetness()) {
            return this.getFlavor().compareTo(other.getFlavor());
        }
        return (int) (this.getSweetness() - other.getSweetness());
    }
    /**
     * gets flavor of this Dessert.
     *
     * @return flavor of this Dessert
     */
    public String getFlavor() {
        return this.flavor;
    }
    /**
     * gets sweetness of this Dessert.
     *
     * @return sweetness of this Dessert
     */
    public double getSweetness() {
        return this.sweetness;
    }
    /**
     * changes flavor of this Dessert.
     *
     * @param newVal  new value of flavor
     */
    public void setFlavor(String newVal) {
        this.flavor = newVal;
    }
    /**
     * changes sweetness of this Dessert.
     *
     * @param newVal  new value of sweetness
     */
    public void setSweetness(double newVal) {
        this.sweetness = newVal;
    }
}