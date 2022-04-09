public class Garage {
    private GarageOwner theOwner;
    private Car[] carCatalogue;
    public Garage(GarageOwner theOwner, Car[] carCatalogue) {
        this.theOwner = theOwner;
        this.carCatalogue = carCatalogue;
        int count = 0;
        for (Car car : carCatalogue) {
            if (car != null) {
                count++;
            }
        }
        this.theOwner.setCarsOwned(count);
    }
    public Garage() {
        this(new GarageOwner("Enzo Ferrari", 35), new Car[4]);
    }
    public Car addCar(int index, Car newCar) {
        if (index >= this.carCatalogue.length || index < 0 || newCar == null) {
            System.out.println("Cannot add a car to this spot.");
            return null;
        }
        Car prev = this.carCatalogue[index];
        if (prev == null) {
            System.out.printf("A %s %d %s %s was just parked here.%n", newCar.getColor(),
                newCar.getYear(), newCar.getMake(), newCar.getModel());
            this.carCatalogue[index] = newCar;
            this.theOwner.setCarsOwned(this.theOwner.getCarsOwned() + 1);
        } else {
            System.out.printf("There was a %s %d %s %s here before.%n", prev.getColor(),
                prev.getYear(), prev.getMake(), prev.getModel());
            this.carCatalogue[index] = newCar;
        }
        return prev;
    }
    public Car sellCar(int index) {
        if (index >= this.carCatalogue.length || index < 0 || this.carCatalogue[index] == null) {
            System.out.println("There was no car to sell!");
        } else {
            Car sold = this.carCatalogue[index];
            this.carCatalogue[index] = null;
            System.out.printf("%s just sold a %s %d %s %s.%n", this.theOwner.getName(),
                sold.getColor(), sold.getYear(), sold.getMake(), sold.getModel());
            this.theOwner.setCarsOwned(this.theOwner.getCarsOwned() - 1);
            return sold;
        }
        return null;
    }
    public void showCertainCars(int boundary) {
        for (Car car : this.carCatalogue) {
            if (car != null && car.getConCat() > boundary) {
                System.out.printf("A %s %d %s %s with a condition category of %d.%n",
                    car.getColor(), car.getYear(), car.getMake(), car.getModel(), car.getConCat());
            }
        }
    }
    public GarageOwner getOwner() {
        return this.theOwner;
    }
    public void setOwner(GarageOwner newOwner) {
        this.theOwner = newOwner;
    }
    public Car[] getCarCatalogue() {
        return this.carCatalogue;
    }
    public void setCarCatalogue(Car[] newCarCatalogue) {
        this.carCatalogue = newCarCatalogue;
    }
}