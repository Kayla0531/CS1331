import java.util.ArrayList;
/**
 * defines Bob's behaviors.
 *
 * @author Jiayiw
 * @version 1.5
 */
public class Bob {
    /**
     * checks if the sweetness and flavors of each individual dessert object in Store 1 are all present.
     *
     * @param store1  the first store to be compared
     * @param store2  the second store to be compared
     * @return true if sweetness and flavors seen in store1 are all present, false otherwise
     */
    public static boolean compareStores(Store store1, Store store2) {
        int i1 = 0;
        int i2 = 0;
        ArrayList<Dessert> arr1 = store1.getDesserts();
        ArrayList<Dessert> arr2 = store2.getDesserts();
        while (i1 < arr1.size() && i2 < arr2.size()) {
            Dessert d1 = arr1.get(i1);
            Dessert d2 = arr2.get(i2);
            int compared = d1.compareTo(d2);
            if (compared == 0) {
                i1++;
                i2++;
            } else if (compared > 0) {
                i2++;
            } else {
                break;
            }
        }
        return false;
    }
    /**
     * sorts the given store object, and helps Bob find his dessert, runs in O(n^2) time.
     *
     * @param store  the given store object to be sorted and looked at
     * @param dessert  the given dessert object to look for in store
     * @return true or false depending on whether Bob could find dessert
     */
    public static boolean shop(Store store, Dessert dessert) {
        store.sortStore();
        Dessert result = store.findDessert(dessert);
        if (result == null) {
            return false;
        }
        return true;
    }
}