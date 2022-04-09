/**
 * representing a Hippo, inherits Pet.
 * can be buffed for each time it faints an enemy Pet
 *
 * @author Jiayiw
 * @version 1.1
 */
public class Hippo extends Pet {
    private int health;
    private int attack;
    private int buff;
    /**
     * constructor that takes in health, attack and buff.
     *
     * @param health  representing the health points(hp) for the Hippo, default to 1 if given nonpositive value
     * @param attack  representing the attack points(ap) for the Hippo, default 0 if given negative
     * @param buff  increase to this Hippo's health and attack, defaults to 0 if given negative
     */
    public Hippo(int health, int attack, int buff) {
        super(health, attack);
        this.buff = buff > 0 ? buff : 0;
    }
    /**
     * constructor that takes in no args.
     * defaults to hp == 7, ap == 4, buff == 2
     */
    public Hippo() {
        this(7, 4, 2);
    }
    /**
     * gets this Hippo buffed when it makes an enemy Pet faint.
     */
    public void getBuffed() {
        this.setHealth(this.getHealth() + buff);
        this.setAttack(this.getAttack() + buff);
    }
    /**
     * attacks another Pet and updates their hp.
     *
     * @param other  representing another Pet that is attacked by this Pet
     */
    @Override
    public void attackPet(Pet other) {
        other.getAttacked(this.getAttack());
        if (other.hasFainted()) {
            this.getBuffed();
        }
    }
    /**
     * gives a String description of the Hippo.
     *
     * @return a String describing the Hippo
     */
    @Override
    public String toString() {
        return "Hippo:" + super.toString() + "/" + buff;
    }
    /**
     * gets buff value for this Hippo.
     *
     * @return buff value of this Hippo
     */
    public int getBuff() {
        return this.buff;
    }
    /**
     * changes buff value of this Hippo.
     *
     * @param newVal  new buff value
     */
    public void setBuff(int newVal) {
        if (newVal >= 0) {
            this.buff = newVal;
        }
    }
}