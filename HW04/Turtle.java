/**
 * representing a Turtle, inherits Pet.
 * can start with melonArmor, prevents up to 20 damage for 1st attack
 *
 * @author Jiayiw
 * @version 1.3
 */
public class Turtle extends Pet {
    private int health;
    private int attack;
    private boolean melonArmor;
    /**
     * constructor that takes in health, attack and melonArmor.
     *
     * @param health  representing the health points(hp) for the Turtle, default to 1 if given nonpositive value
     * @param attack  representing the attack points(ap) for the Turtle, default 0 if given negative
     * @param melonArmor  representing whether melon armor is present, which blocks up to 20 damage for once
     */
    public Turtle(int health, int attack, boolean melonArmor) {
        super(health, attack);
        this.melonArmor = melonArmor;
    }
    /**
     * constructor that takes in no args.
     * defaults to hp == 4, ap == 2, melonArmor == true
     */
    public Turtle() {
        this(4, 2, true);
    }
    /**
     * decreases hp by the amount of damage taken.
     * a melon armor can block up to 20 damage for once
     *
     * @param damage  representing the amount of damage the Turtle is being attacked for
     */
    @Override
    public void getAttacked(int damage) {
        if (this.melonArmor) {
            if (damage > 20) {
                this.setHealth(this.getHealth() - (damage - 20));
            }
            this.setMelonArmor(false);
        } else {
            this.setHealth(this.getHealth() - damage);
        }
    }
    /**
     * gives a String description of the Turtle.
     *
     * @return a String describing the Turtle
     */
    @Override
    public String toString() {
        return "Turtle:" + super.toString() + "/" + melonArmor;
    }
    /**
     * gets melon armor status of this Turtle.
     *
     * @return melon armor status of this Turtle
     */
    public boolean getMelonArmor() {
        return melonArmor;
    }
    /**
     * changes melon armor status of this Turtle.
     *
     * @param newVal  new value of melon armor status
     */
    public void setMelonArmor(boolean newVal) {
        melonArmor = newVal;
    }
}