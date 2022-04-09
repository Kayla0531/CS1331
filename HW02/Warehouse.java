/**
 * driver, contains and runs all the classes.
 *
 * @author Jiayiw
 * @version 1.0
 */
public class Warehouse {
    /**
     * main method, involves testing codes.
     *
     * @param args  System input, nothing taken in for this main method
     */
    public static void main(String[] args) {
        /**
         * testing PaperProduct.java .
        PaperProduct firstProd = new PaperProduct("first");
        System.out.println(firstProd.paperString());
        PaperProduct secProd = new PaperProduct(null, -5);
        System.out.println(secProd.paperString());
        PaperProduct thirdProd = new PaperProduct("third", 1500, 0.1);
        System.out.println(thirdProd.paperString());
        PaperProduct firstCopied = new PaperProduct(firstProd);
        System.out.println(firstCopied.paperString());
        firstProd.setNumberOfSheets(-50);
        System.out.println(firstProd.paperString());
        firstProd.setNumberOfSheets(900);
        System.out.println(firstProd.paperString());
        System.out.println("copied:  "+ firstCopied.paperString());
        System.out.println(firstProd.ship("comp1"));
        System.out.println(secProd.ship("firm2"));
        System.out.println(thirdProd.ship("me"));
        System.out.println(firstCopied.ship("kap"));
        System.out.println(firstProd.ship("comp1"));
        System.out.println(secProd.ship("firm2"));
        System.out.println(thirdProd.ship("me"));
        System.out.println(firstCopied.ship("kap"));
        System.out.println("fine==============================");
         */
        /**
         * testing PhotoPaper.java .
        PhotoPaper firstProd = new PhotoPaper("photo1");
        System.out.println(firstProd.photoString());
        PhotoPaper secProd = new PhotoPaper("photo2", 1000);
        System.out.println(secProd.photoString());
        PhotoPaper thirdProd = new PhotoPaper("photo3", 1500, 0.5, 130);
        System.out.println(thirdProd.photoString());
        System.out.println(firstProd.shipPhoto("comp1"));
        System.out.println(secProd.shipPhoto("firm2"));
        System.out.println(thirdProd.shipPhoto("me"));
        System.out.println(PaperProduct.getTotalProductsToShip());
        System.out.println("fine==============================");
         */
        /**
         * testing DiscountedPaper.java & GoldenTicket.java .
         */
        System.out.println(null instanceof PaperProduct);
        PaperProduct firstProd = new PaperProduct("disc1");
        PhotoPaper secProd = new PhotoPaper("disc2", 700);
        DiscountedPaper thirdProd = new DiscountedPaper("disc3", 900, 0.4, 20, null);
        GoldenTicket ticket1 = new GoldenTicket("my phrase", 19);
        DiscountedPaper fourthProd = new DiscountedPaper("disc4", 900, 0.4, 20, ticket1);
        GoldenTicket ticket2 = new GoldenTicket(null, 80);
        System.out.println(secProd.paperString());
        PaperProduct newSec = (PaperProduct) secProd;
        System.out.println(newSec.paperString());
        DiscountedPaper newNewSec = (DiscountedPaper) newSec;
        
        /**
        ticket2.setDiscount(10);
        secProd.setDiscount(12);
        thirdProd.setDiscount(199);
        System.out.println("ticket 2: ///" + ticket2.ticketString());
        System.out.println("2ndprod::::: " + secProd.getDiscount());
        System.out.println("3rdprod::::: " + thirdProd.getDiscount());
         */
    }
}