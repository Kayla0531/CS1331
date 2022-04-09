/**
 * stores details about a paper product in general.
 *
 * @author Jiayiw
 * @version 1.0
 */
public class PaperProduct {
    private final String name; // should be final, default "A4", invalid if empty or null
    private int numberOfSheets = 500; // invalid if negative
    private final double weightOfUnitSheet; // should be final, default 0.25, invalid if negative
    private static int totalProductsToShip = 10; // should not be negative
    public static final double COST_PER_GRAM = 0.025;
    /**
     * constructor that takes in name, numberOfSheets, weightOfUnitSheet.
     *
     * @param name  name of the PaperProduct Object, defaulted to A4 if invalid (blank String or null)
     * @param numberOfSheets  count of sheets of the Object, defaulted to 500 if invalid(negative)
     * @param weightOfUnitSheet  weight of each sheet of the Object, final, default to 0.25 if invalid (negative)
     */
    public PaperProduct(String name, int numberOfSheets, double weightOfUnitSheet) {
        this.name = (name != null && !name.isEmpty()) ? name : "A4";
        setNumberOfSheets(numberOfSheets);
        this.weightOfUnitSheet = weightOfUnitSheet < 0 ? 0.25 : weightOfUnitSheet;
    }
    /**
     * constructor that takes in name, numberOfSheets.
     * weightOfUnitSheet set to default 0.25
     *
     * @param name  name of the PaperProduct Object
     * @param numberOfSheets  count of sheets of the Object
     */
    public PaperProduct(String name, int numberOfSheets) {
        this(name, numberOfSheets, 0.25);
    }
    /**
     * constructor that takes in name.
     * numOfSheets set to default 500
     * weightOfUnitSheet set to default 0.25
     *
     * @param name  name of the PaperProduct Object
     */
    public PaperProduct(String name) {
        this(name, 500, 0.25);
    }
    /**
     * constructor that copies the given PaperProduct object.
     *
     * @param product  the given PaperProduct Object to be copied
     */
    public PaperProduct(PaperProduct product) {
        this.name = product.getName();
        this.numberOfSheets = product.getNumberOfSheets();
        this.weightOfUnitSheet = product.getWeightOfUnitSheet();
    }
    /**
     * calculates and returns total weight of the product.
     *
     * @return total weight of the product
     */
    public double totalWeight() {
        return this.numberOfSheets * this.weightOfUnitSheet;
    }
    /**
     * calculates and returns total cost of the product.
     *
     * @return total cost of the product
     */
    public double totalCost() {
        return totalWeight() * COST_PER_GRAM;
    }
    /**
     * helper method, returns a double that is rounded to 2 decimal places.
     *
     * @param value  the value to be rounded
     * @return the value rounded to 2 decimal places
     */
    public double printedValue(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    /**
     * gives a String description of the PaperProduct Object.
     *
     * @return a string representing the PaperProduct Object
     */
    public String paperString() {
        return printedValue(totalWeight()) + "g of " + this.name + " for $" + printedValue(totalCost());
    }
    /**
     * does the shipping action, decreases totalProductsToShip for each successful shipping.
     *
     * @param company  the name of the company that needs to be shipped to
     * @return a string indicating the status of shipment
     */
    public String ship(String company) {
        if (this.totalProductsToShip > 0) {
            this.totalProductsToShip--;
            return "Shipped " + paperString() + " to " + company + ".";
        } else {
            return "Cannot ship any items, Warehouse is empty!";
        }
    }
    /**
     * getter of name of this Object.
     *
     * @return name of this Object
     */
    public String getName() {
        return this.name;
    }
    /**
     * returns number of sheets.
     *
     * @return number of sheets of this Object
     */
    public int getNumberOfSheets() {
        return this.numberOfSheets;
    }
    /**
     * returns weight Of Unit Sheet.
     *
     * @return weight Of Unit Sheet of this Object
     */
    public double getWeightOfUnitSheet() {
        return this.weightOfUnitSheet;
    }
    /**
     * getter of totalProductsToShip.
     *
     * @return totalProductsToShip
     */
    public static int getTotalProductsToShip() {
        return totalProductsToShip;
    }
    /**
     * changes numberOfSheets.
     *
     * @param newNum  the new numberOfSheets value if valid
     */
    public void setNumberOfSheets(int newNum) {
        if (newNum >= 0) {
            this.numberOfSheets = newNum;
        } else {
            this.numberOfSheets = 500;
        }
    }
}