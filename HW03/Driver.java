/**
 * driver, contains and runs all the classes.
 *
 * @author Jiayiw
 * @version 1.0
 */
public class Driver {
    /**
     * main method, involves testing codes.
     *
     * @param args  System input, nothing taken in for this main method
     */
    public static void main(String[] args) {
        Car car1 = new Car("11111");
        Car car2 = new Car("22222", 0, new String[4], 10.4, 16.77, 200);
        // checking toString() and addPassenger()
        System.out.println(car1.toString());
        System.out.println(car2.toString());
        car2.addPassenger(5, new String[] {"alpha", "beta", "omega"});
        System.out.println(car2.toString());
        car2.addPassenger(11, new String[] {"Jiayi"});
        System.out.println(car2.toString());
        car2.addPassenger(10, new String[] {"CAT", "DOG", "FROG"});
        System.out.println(car2.toString());
        // checking equal()
        System.out.println("1 & 2 : " + car2.equals(car1));
        Car car3 = new Car("11111", 0, new String[4], 10, 15, 200);
        System.out.println("2 & 3 : " + car2.equals(car3));
        System.out.println("1 & 3 : " + car3.equals(car1));
        Bus bus1 = new Bus("BUS ONE", "west campus");
        Bus bus2 = new Bus("BUS TWO", 15, "east campus", 5);
        bus2.addPassenger(80, new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "shouldnt be here"});
        System.out.println(bus1.toString());
        System.out.println(bus2.toString());
        System.out.println("1 & 2" + bus1.equals(bus2));
        Bus bus3 = new Bus("BUS ONE", 0, "west campus", 2);
        System.out.println("1 & 3" + bus1.equals(bus3));
    }
}