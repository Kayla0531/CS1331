public class Driver{ 
    public static void main(String[] args){ 
        GarageOwner me = new GarageOwner("Jiayi", 17);
        Car[] myCars = { new Car(2013, "Volvo", "XC60"), new Car(2004, "Fiat", "500", "yellow", 50), new Car()};
        Garage myGarage = new Garage(me, myCars);
        myGarage.sellCar(0);
        System.out.printf("I have got %d cars!%n", me.getCarsOwned());
        myGarage.showCertainCars(60);
        Garage enzosGarage = new Garage();
        System.out.println(enzosGarage.getOwner().getCarsOwned());
        System.out.println(enzosGarage.getCarCatalogue()[0]);
    } 
} 