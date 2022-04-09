import java.util.ArrayList;
/**
 * Stores the data for the dessert store, sells all kinds of dessert.
 *
 * @author Jiayiw
 * @version 2.2
 */
public class Store {
    private String name;
    private ArrayList<Dessert> desserts;
    /**
     * constructor that takes in name.
     *
     * @param name  represents name of the store
     */
    public Store(String name) {
        this.name = name;
        this.desserts = new ArrayList<Dessert>();
    }
    /**
     * addes the given item to the back of desserts, runs in O(1) time.
     *
     * @param item  the dessert to be added
     */
    public void addDessert(Dessert item) {
        desserts.add(item);
    }
    /**
     * removes the given item from desserts, runs in O(n) time.
     *
     * @param item  the dessert to be removed
     * @return item if removal successful, null otherwise
     */
    public Dessert removeDessert(Dessert item) {
        for (int index = 0; index < desserts.size(); index++) {
            Dessert d = desserts.get(index);
            if (d.equals(item)) {
                return desserts.remove(index);
            }
        }
        return null;
    }
    /**
     * looks for the given item in desserts, runs in O(log n) time.
     *
     * @param item  the dessert to look for
     * @return item if successfully found, null otherwise
     */
    public Dessert findDessert(Dessert item) {
        int index;
        int left = 0;
        int right = desserts.size();
        while (left < right) {
            index = (left + right) / 2;
            Dessert curr = desserts.get(index);
            int compareRes = curr.compareTo(item);
            if (compareRes == 0) {
                return curr;
            } else if (compareRes > 0) {
                right = index;
            } else {
                left = index + 1;
            }
        }
        return null;
    }
    /**
     * sorts desserts in ascending order based on sweetness and flavor, runs in (O^2) time.
     */
    public void sortStore() {
        // selection sort
        for (int i = 0; i < desserts.size(); i++) {
            int j = locOfSmallest(desserts, i, desserts.size());
            desserts = swap(desserts, i, j);
        }
        /**
        for (int i = 1; i < desserts.size(); i++) {
            int j = i - 1;
            Dessert iVal = desserts.get(i);
            while (j >= 0 && (desserts.get(j)).compareTo(desserts.get(j + 1)) > 0) {
                desserts.set(j + 1, desserts.get(j));
                j--;
            }
            desserts.set(j + 1, iVal);
        }
        */
    }
    /**
     * helper method of sortStore.
     *
     * @param list  the list wil elements to be swapped
     * @param i  the first element
     * @param j  the second element
     * @return the given list with elements swapped
     */
    public ArrayList<Dessert> swap(ArrayList<Dessert> list, int i, int j) {
        Dessert temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        return list;
    }
    /**
     * helper method of sortStore.
     *
     * @param list  the list with elements to be looked at
     * @param start  the starting index
     * @param end  the ending index
     * @return the index of the smallest element within the range
     */
    public int locOfSmallest(ArrayList<Dessert> list, int start, int end) {
        int minIndex = start;
        Dessert min = list.get(start);
        for (int j = minIndex; j < end; j++) {
            Dessert curr = list.get(j);
            if (curr.compareTo(min) < 0) {
                min = curr;
                minIndex = j;
            }
        }
        return minIndex;
    }
    /**
     * takes in item and return the count of desserts in the store
     * that is greater or equal to the Dessert passed in, runs in (O^2) time.
     *
     * @param item a valid Dessert object to be compared with
     * @return the count of desserts in the store greater or equal to the Dessert passed in
     */
    public int checkStore(Dessert item) {
        int count = 0;
        for (int index = 0; index < desserts.size(); index++) {
            Dessert curr = desserts.get(index);
            if (curr.compareTo(item) >= 0) {
                count = desserts.size() - index;
                break;
            }
        }
        return count;
    }
    /**
     * gets name of this Store.
     *
     * @return name of this Store
     */
    public String getName() {
        return this.name;
    }
    /**
     * gets desserts of this Store.
     *
     * @return desserts of this Store
     */
    public ArrayList<Dessert> getDesserts() {
        return this.desserts;
    }
    /**
     * changes name of this Store.
     *
     * @param newVal  new value of name
     */
    public void setName(String newVal) {
        this.name = newVal;
    }
    /**
     * changes desserts of this Store.
     *
     * @param newVal  new value of desserts
     */
    public void setDesserts(ArrayList<Dessert> newVal) {
        this.desserts = newVal;
    }
}