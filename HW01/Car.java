public class Car {
    private int year;
    private String make; // the brand
    private String model;
    private String color;
    private int conditionCategory; //should be within 40-100
    private boolean isRestored = false;
    public Car(int year, String make, String model, String color, int conditionCategory) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        if (conditionCategory > 100 || conditionCategory < 40) {
            conditionCategory = 80;
        }
        if (conditionCategory >= 90) {
            System.out.println("Perfect");
            isRestored = true;
        } else if (conditionCategory >= 80) {
            System.out.println("Excellent");
        } else if (conditionCategory >= 70) {
            System.out.println("Fine");
        } else if (conditionCategory >= 60) {
            System.out.println("Very Good");
        } else if (conditionCategory >= 50) {
            System.out.println("Good");
        } else {
            System.out.println("Driver");
        }
        this.conditionCategory = conditionCategory;
    }
    public Car(int year, String make, String model) {
        this(year, make, model, "blue", 80);
    }
    public Car() {
        this(1960, "Jaguar", "E-Type", "silver", 89);
    }
    public int getYear() {
        return this.year;
    }
    public void setYear(int newNum) {
        this.year = newNum;
    }
    public String getMake() {
        return this.make;
    }
    public void setMake(String newMake) {
        this.make = newMake;
    }
    public String getColor() {
        return this.color;
    }
    public void setColor(String newColor) {
        this.color = newColor;
    }
    public String getModel() {
        return this.model;
    }
    public void setModel(String newModel) {
        this.model = newModel;
    }
    public int getConCat() {
        return this.conditionCategory;
    }
    public void setConCat(int newConCat) {
        this.conditionCategory = newConCat;
    }
}
