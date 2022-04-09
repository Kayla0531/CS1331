/**
 * representing a IceCream, inherits Dessert.
 *
 * @author Jiayiw
 * @version 1.0
 */
public class IceCream extends Dessert {
    private int scoops;
    private boolean cone;
    /**
     * constructor that takes in flavor, sweetness, scoops and cone.
     *
     * @param flavor  the flavor of the IceCream
     * @param sweetness  the sweetness of the IceCream
     * @param scoops  the number of scoops of the IceCream
     * @param cone  represents whether the IceCream comes with a cone
     */
    public IceCream(String flavor, double sweetness, int scoops, boolean cone) {
        super(flavor, sweetness);
        this.scoops = scoops;
        this.cone = cone;
    }
    /**
     * constructor that takes in scoops and cone.
     *
     * @param scoops  the number of scoops of the IceCream
     * @param cone  represents whether the IceCream comes with a cone
     */
    public IceCream(int scoops, boolean cone) {
        this("vanilla", 45.0, scoops, cone);
    }
    /**
     * constructor that takes in nothing.
     * defaults flavor to "vanilla", sweetness to 45.0, scoops to 1, cone to false.
     */
    public IceCream() {
        this("vanilla", 45.0, 1, false);
    }
    /**
     * gives a String description of the IceCream Object.
     *
     * @return a String representing the IceCream Object
     */
    public String toString() {
        String word = cone ? "has" : "does not";
        return String.format("This is a %s ice cream with %d scoops and %s have a cone.",
            getFlavor(), getScoops(), word);
    }
    /**
     * overrides equals() from Object.
     *
     * @param other  the IceCream to be compared with this
     * @return true if this equals to other; false if not
     */
    @Override
    public boolean equals(Object other) {
        if (super.equals(other) && other instanceof IceCream) {
            IceCream o = (IceCream) other;
            return this.getScoops() == o.getScoops() && this.getCone() == o.getCone();
        }
        return false;
    }
    /**
     * gets scoops of this IceCream.
     *
     * @return scoops of this IceCream
     */
    public int getScoops() {
        return this.scoops;
    }
    /**
     * gets cone of this IceCream.
     *
     * @return cone of this IceCream
     */
    public boolean getCone() {
        return this.cone;
    }
    /**
     * changes scoops of this IceCream.
     *
     * @param newVal  new value of scoops
     */
    public void setScoops(int newVal) {
        this.scoops = newVal;
    }
    /**
     * changes cone of this IceCream.
     *
     * @param newVal  new value of cone
     */
    public void setCone(boolean newVal) {
        this.cone = newVal;
    }
}