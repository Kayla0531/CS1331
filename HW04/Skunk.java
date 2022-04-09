/**
 * representing a Skunk, inherits Pet.
 * can weaken other Pets before attacking for a limited number of times
 *
 * @author Jiayiw
 * @version 1.0
 */
public class Skunk extends Pet {
    private int health;
    private int attack;
    private int numSpray;
    /**
     * constructor that takes in health, attack and numSpray.
     *
     * @param health  representing the health points(hp) for the Hippo, default to 1 if given nonpositive value
     * @param attack  representing the attack points(ap) for the Hippo, default 0 if given negative
     * @param numSpray  should not be negative, initially default to 0 if negative
     */
    public Skunk(int health, int attack, int numSpray) {
        super(health, attack);
        this.numSpray = numSpray > 0 ? numSpray : 0;
    }
    /**
     * constructor that takes in no args.
     * defaults to hp == 5, ap == 3, numSpray == 1
     */
    public Skunk() {
        this(5, 3, 1);
    }
    /**
     * sprays other and weaken it by reducing its hp and ap by one third.
     *
     * @param other  representing another Pet that is to be sprayed
     */
    public void sprayPet(Pet other) {
        other.setAttack(other.getAttack() * 2 / 3);
        other.setHealth(other.getHealth() * 2 / 3);
        this.numSpray--;
    }
    /**
     * attacks another Pet and updates their hp after spraying (if available).
     *
     * @param other  representing another Pet that is to be attacked
     */
    @Override
    public void attackPet(Pet other) {
        if (numSpray > 0) {
            sprayPet(other);
        }
        other.getAttacked(this.getAttack());
    }
    /**
     * gives a String description of the Skunk.
     *
     * @return a String describing the Skunk
     */
    @Override
    public String toString() {
        return "Skunk:" + super.toString() + "/" + numSpray;
    }
    /**
     * gets numSpray value for this Skunk.
     *
     * @return numSpray value of this Skunk
     */
    public int getNumSpray() {
        return this.numSpray;
    }
    /**
     * changes numSpray value of this Skunk.
     *
     * @param newVal  new numSpray value
     */
    public void setNumSpray(int newVal) {
        this.numSpray = newVal;
    }
}