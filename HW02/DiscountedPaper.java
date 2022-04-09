/**
 * stores details about discounted paper stock.
 * child class of PaperProduct
 *
 * @author Jiayiw
 * @version 1.0
 */
public class DiscountedPaper extends PaperProduct {
    private double discount = 15.0; // invalid if out of range (0,50]
    private GoldenTicket ticket = null;
    /**
     * constructor that takes in name, numberOfSheets, weightOfUnitSheet, discount, ticket.
     *
     * @param name  name of the DiscountedPaper Object, defaulted to "A4" if invalid (blank String or null)
     * @param numberOfSheets  count of sheets of the Object, defaulted to 500 if invalid(negative)
     * @param weightOfUnitSheet  weight of each sheet of the Object, final, default to 0.25 if invalid (negative)
     * @param discount  represents the discount of the Paper Product in percent
     * @param ticket  represents whether the product has a Golden Ticket attached
     */
    public DiscountedPaper(String name, int numberOfSheets, double weightOfUnitSheet, double discount,
        GoldenTicket ticket) {
        super(name, numberOfSheets, weightOfUnitSheet);
        setDiscount(discount);
        this.ticket = ticket;
    }
    /**
     * constructor that takes in name, numberOfSheets.
     *
     * @param name  name of the DiscountedPaper Object, defaulted to "A4" if invalid (blank String or null)
     * @param numberOfSheets  count of sheets of the Object, defaulted to 500 if invalid(negative)
     */
    public DiscountedPaper(String name, int numberOfSheets) {
        super(name, numberOfSheets);
    }
    /**
     * constructor that takes in name.
     *
     * @param name  name of the DiscountedPaper Object, defaulted to "A4" if invalid (blank String or null)
     */
    public DiscountedPaper(String name) {
        super(name);
    }
    /**
     * constructor that copies the given DiscountedPaper Object.
     *
     * @param newPaper  the given DiscountedPaper Object to be copied
     */
    public DiscountedPaper(DiscountedPaper newPaper) {
        super(newPaper);
        this.discount = newPaper.getDiscount();
        this.ticket = new GoldenTicket(newPaper.getTicket().getCatchphrase(), newPaper.getTicket().getDiscount());
    }
    /**
     * calculates and returns total cost of the product after the discount.
     *
     * @return discounted cost of the product
     */
    public double discountedCost() {
        double discountedCurrCost = super.totalCost() * (100.0 - this.discount) / 100.0;
        if (this.ticket != null) {
            discountedCurrCost *= (100.0 - this.ticket.getDiscount()) / 100.0;
        }
        return discountedCurrCost;
    }
    /**
     * does the shipping action, decreases totalProductsToShip for each successful shipping.
     *
     * @param company  the name of the company that needs to be shipped to
     * @return a string indicating the status of shipment
     */
    public String shipDiscounted(String company) {
        String newLine = " The total cost after the discount is " + super.printedValue(discountedCost()) + ".";
        return super.ship(company) + newLine;
    }
    /**
     * gives a String description of the discounting of the product.
     *
     * @return a string indicating the accounting details of the product
     */
    public String botherAccounting() {
        double ticketDiscount = (this.ticket == null) ? 0 : this.ticket.getDiscount();
        return "Discounted Paper Product:\n"
            + "Original Cost: " + super.printedValue(super.totalCost()) + "\n"
            + "Final Cost: " + super.printedValue(discountedCost()) + "\n"
            + "Original Discount: " + super.printedValue(this.discount) + "%\n"
            + "Golden Ticket Discount: " + super.printedValue(ticketDiscount) + "%";
    }
    /**
     * getter of discount of this DiscountedPaper Object.
     *
     * @return discount of this DiscountedPaper Object
     */
    public double getDiscount() {
        return this.discount;
    }
    /**
     * changes discount of this DiscountedPaper Object.
     *
     * @param newDiscount  the new value of discount
     */
    public void setDiscount(double newDiscount) {
        if (newDiscount > 0 && newDiscount <= 50) {
            this.discount = newDiscount;
        } else {
            this.discount = 15.0;
        }
    }
    /**
     * getter of ticket of this DiscountedPaper Object.
     *
     * @return ticket of this DiscountedPaper Object
     */
    public GoldenTicket getTicket() {
        return this.ticket;
    }
    /**
     * changes ticket of this DiscountedPaper Object.
     *
     * @param newTicket  the new value of ticket
     */
    public void setTicket(GoldenTicket newTicket) {
        this.ticket = newTicket;
    }
}