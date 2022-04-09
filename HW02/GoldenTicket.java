/**
 * stores details about Golden Tickets that customers use with Discounted Stock.
 *
 * @author Jiayiw
 * @version 1.0
 */
public class GoldenTicket {
    private String catchphrase = "Congrats!"; // invalid if empty or null
    private double discount = 15.0; // invalid if out of range (0,25]
    /**
     * constructor that takes in catchphrase, discount.
     *
     * @param catchphrase  represents the catchphrase printed on ticket
     * @param discount  represents discount offered in percent
     */
    public GoldenTicket(String catchphrase, double discount) {
        setCatchphrase(catchphrase);
        setDiscount(discount);
    }
    /**
     * gives a String description of the GoldenTicket Object.
     *
     * @return a string representing the GoldenTicket Object
     */
    public String ticketString() {
        double printedDiscount = Math.round(discount * 100.0) / 100.0;
        return "Golden Ticket with a " + printedDiscount + "% discount! " + catchphrase;
    }
    private boolean isValidString(String str) {
        return str != null && !str.isEmpty();
    }
    private boolean isValidDiscount(double disc) {
        return disc > 0 && disc <= 25;
    }
    /**
     * getter of catchphrase.
     *
     * @return catchphrase of this GoldenTicket Object
     */
    public String getCatchphrase() {
        return this.catchphrase;
    }
    /**
     * changes catchphrase of this GoldenTicket Object.
     *
     * @param newLine  the new value of catchphrase if valid
     */
    public void setCatchphrase(String newLine) {
        if (newLine != null && !newLine.isEmpty()) {
            this.catchphrase = newLine;
        } else {
            this.catchphrase = "Congrats!";
        }
    }
    /**
     * getter of discount.
     *
     * @return discount of this GoldenTicket Object
     */
    public double getDiscount() {
        return this.discount;
    }
    /**
     * changes discount of this GoldenTicket Object.
     *
     * @param newDiscount  the new value of discount if valid
     */
    public void setDiscount(double newDiscount) {
        if (isValidDiscount(newDiscount)) {
            this.discount = newDiscount;
        } else {
            this.discount = 15.0;
        }
    }
}