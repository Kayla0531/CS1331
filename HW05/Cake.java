/**
 * representing a Cake, inherits Dessert.
 *
 * @author Jiayiw
 * @version 1.0
 */
public class Cake extends Dessert {
    private String frosting;
    /**
     * constructor that takes in flavor, sweetness and frosting.
     *
     * @param flavor  the flavor of the Cake
     * @param sweetness  the sweetness of the Cake
     * @param frosting  the frosting of the Cake
     */
    public Cake(String flavor, double sweetness, String frosting) {
        super(flavor, sweetness);
        this.frosting = frosting;
    }
    /**
     * constructor that only takes in flavor.
     *
     * @param flavor  the flavor of the Cake
     */
    public Cake(String flavor) {
        this(flavor, 45.0, "vanilla");
    }
    /**
     * gives a String description of the Cake Object.
     *
     * @return a String representing the Cake Object
     */
    public String toString() {
        return String.format("This is a %s cake with a %s frosting and has a sweetness of %.2f.",
            getFlavor(), getFrosting(), getSweetness());
    }
    /**
     * overrides equals() from Object.
     *
     * @param other  the Cake to be compared with this
     * @return true if this equals to other; false if not
     */
    @Override
    public boolean equals(Object other) {
        if (super.equals(other) && other instanceof Cake) {
            Cake o = (Cake) other;
            return this.getFrosting().equals(o.getFrosting());
        }
        return false;
    }
    /**
     * gets frosting of this Cake.
     *
     * @return frosting of this Cake
     */
    public String getFrosting() {
        return this.frosting;
    }
    /**
     * changes frosting of this Cake.
     *
     * @param newVal  new value of frosting
     */
    public void setFrosting(String newVal) {
        this.frosting = newVal;
    }
}