public class GarageOwner {
    private String name;
    private int age;
    private int carsOwned = 0;
    public GarageOwner(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int newAge) {
        this.age = newAge;
    }
    public int getCarsOwned() {
        return this.carsOwned;
    }
    public void setCarsOwned(int newNum) {
        this.carsOwned = newNum;
    }
}