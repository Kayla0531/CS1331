/**
 * representing a Pet, should be able to be compared.
 * implementing Comparable
 *
 * @author Jiayiw
 * @version 1.2
 */
public abstract class Pet implements Comparable<Pet> {
    private int health;
    private int attack;
    /**
     * constructor that takes in health and attack.
     *
     * @param health  representing the health points(hp) for the Pet, default to 1 if given nonpositive value
     * @param attack  representing the attack points(ap) for the Pet, default 0 if given negative
     */
    public Pet(int health, int attack) {
        this.health = (health > 0) ? health : 1;
        this.attack = (attack >= 0) ? attack : 0;
    }
    /**
     * checks if this Pet has fainted.
     *
     * @return true if hp is nonpositive, false if not
     */
    public boolean hasFainted() {
        return this.health <= 0;
    }
    /**
     * decreases hp by the amount of damage taken.
     *
     * @param damage  representing the amount of damage the Pet is being attacked for
     */
    public void getAttacked(int damage) {
        this.setHealth(this.getHealth() - damage);
    }
    /**
     * attacks another Pet and updates their hp.
     *
     * @param other  representing another Pet that is attacked by this Pet
     */
    public void attackPet(Pet other) {
        other.getAttacked(this.attack);
    }
    /**
     * gives a String description of the Pet.
     *
     * @return a String describing the Pet
     */
    public String toString() {
        return attack + "/" + health;
    }
    /**
     * compares this Pet to another given Pet based on the sum of their hp and ap.
     *
     * @param pet  the other Pet to be compared with
     * @return a positive int if this Pet is greater than the other Pet, negative is less, 0 if equal
     */
    public int compareTo(Pet pet) {
        if (pet == null) {
            return 999;
        }
        return (this.health + this.attack) - (pet.getHealth() + pet.getAttack());
    }
    /**
     * gets health points of this Pet.
     *
     * @return hp of this Pet
     */
    public int getHealth() {
        return this.health;
    }
    /**
     * gets attack points of this Pet.
     *
     * @return ap of this Pet
     */
    public int getAttack() {
        return this.attack;
    }
    /**
     * changes health points of this Pet.
     *
     * @param newVal  new value of hp for this Pet
     */
    public void setHealth(int newVal) {
        this.health = newVal;
    }
    /**
     * changes attack points of this Pet.
     *
     * @param newVal  new value of ap for this Pet
     */
    public void setAttack(int newVal) {
        this.attack = newVal;
    }
}