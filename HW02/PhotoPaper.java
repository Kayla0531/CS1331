/**
 * stores details about photo paper stock.
 * child class of PaperProduct
 *
 * @author Jiayiw
 * @version 1.1
 */
public class PhotoPaper extends PaperProduct {
    private double glossiness = 70;
    /**
     * constructor that takes in name, numberOfSheets, weightOfUnitSheet, glossiness.
     *
     * @param name  name of the PhotoPaper Object, defaulted to "A4" if invalid (blank String or null)
     * @param numberOfSheets  count of sheets of the Object, defaulted to 500 if invalid(negative)
     * @param weightOfUnitSheet  weight of each sheet of the Object, final, default to 0.25 if invalid (negative)
     * @param glossiness  glossiness of the PhotoPaper Object, defaulted to 70 if invalid (range [0, 100])
     */
    public PhotoPaper(String name, int numberOfSheets, double weightOfUnitSheet, double glossiness) {
        super(name, numberOfSheets, weightOfUnitSheet);
        setGlossiness(glossiness);
    }
    /**
     * constructor that takes in name, numOfSheets.
     *
     * @param name  name of the PhotoPaper Object
     * @param numberOfSheets  count of sheets of the Object
     */
    public PhotoPaper(String name, int numberOfSheets) {
        super(name, numberOfSheets);
    }
    /**
     * constructor that takes in name only.
     *
     * @param name  name of the PhotoPaper Object
     */
    public PhotoPaper(String name) {
        super(name);
    }
    /**
     * constructor that copies the given PhotoPaper Object.
     *
     * @param photo  the given PhotoPaper Object to be copied
     */
    public PhotoPaper(PhotoPaper photo) {
        super(photo);
        this.glossiness = photo.getGlossiness();
    }
    /**
     * returns the printable value of glossiness rounded to 2 decimal places.
     *
     * @return glossiness rounded to 2 decimal places
     */
    public double printedGlossiness() {
        return super.printedValue(this.glossiness);
    }
    /**
     * gives a String description of the PhotoPaper Object.
     *
     * @return a string representing the PhotoPaper Object
     */
    public String photoString() {
        return printedGlossiness() + "% glossy and " + super.paperString();
    }
    /**
     * does the shipping action of a PhotoPaper Object.
     *
     * @param company  the name of the company that needs to be shipped to
     * @return a string indicating the status of shipment
     */
    public String shipPhoto(String company) {
        String newLine = "Paper is " + printedGlossiness() + "% glossy. ";
        return newLine + super.ship(company);
    }
    /**
     * getter of glossiness.
     *
     * @return glossiness of this PhotoPaper Object
     */
    public double getGlossiness() {
        return this.glossiness;
    }
    /**
     * changes glossiness of this PhotoPaper Object.
     *
     * @param newGlossiness  the new value of glossiness if valid
     */
    public void setGlossiness(double newGlossiness) {
        if (newGlossiness >= 0 && newGlossiness <= 100) {
            this.glossiness = newGlossiness;
        } else {
            this.glossiness = 70;
        }
    }
}