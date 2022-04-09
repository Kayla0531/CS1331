/**
 * representing a field where Pet battles occur.
 *
 * @author Jiayiw
 * @version 1.5
 */
public class PetBattlefield {
    private Pet[] firstTeam = new Pet[5]; // null entries are allowed
    private Pet[] secondTeam = new Pet[5]; // null entries are allowed
    /**
     * constructor that takes in firstTeam and secondTeam.
     *
     * @param firstTeam  representing the first team involved in the battle
     * @param secondTeam  representing the second team involved in the battle
     */
    public PetBattlefield(Pet[] firstTeam, Pet[] secondTeam) {
        if (firstTeam.length <= 5 && secondTeam.length <= 5) {
            this.firstTeam = firstTeam;
            this.secondTeam = secondTeam;
        }
    }
    /**
     * gives a String description of the field.
     *
     * @return a String describing the field
     */
    @Override
    public String toString() {
        String r = "First Team: ";
        r = toStrHelper(r, firstTeam);
        r += " vs Second Team: ";
        return toStrHelper(r, secondTeam);
    }
    /**
     * helper method, turns teams into String descriptions.
     *
     * @param r  a given String to be added on
     * @param team  the team to be turn into String
     * @return String r with a team description added
     */
    private String toStrHelper(String r, Pet[] team) {
        for (int index = 0; index < team.length; index++) {
            Pet p = team[index];
            if (p == null) {
                r += "Empty";
            } else {
                r += p.toString();
            }
            if (index < team.length - 1) {
                r += ", ";
            }
        }
        return r;
    }
    /**
     * compares the two teams in the field by comparing each Pet on corresponding indexes.
     */
    public void compareTeams() {
        int mark1 = 0;
        int mark2 = 0;
        int index = 0;
        Pet pet1;
        Pet pet2;
        int compareResult;
        while (index < Math.max(firstTeam.length, secondTeam.length)) {
            if (index >= firstTeam.length) {
                pet1 = null;
            } else {
                pet1 = firstTeam[index];
            }
            if (index >= secondTeam.length) {
                pet2 = null;
            } else {
                pet2 = secondTeam[index];
            }
            if (pet1 == null) {
                if (pet2 == null) {
                    index++;
                    continue;
                } else {
                    compareResult = pet2.compareTo(pet1);
                }
            } else {
                compareResult = pet1.compareTo(pet2);
            }
            if (compareResult > 0) {
                // when the Pet in firstTeam wins
                mark1++;
            } else if (compareResult < 0) {
                mark2++;
            }
            index++;
        }
        if (mark1 == mark2) {
            System.out.println("It is an even match.");
            return;
        }
        String winner = (mark1 > mark2) ? "first" : "second";
        System.out.printf("The %s team will probably win.%n", winner);
    }
    /**
     * performs the battle, continues until one of the team has only fainted pets.
     */
    public void battle() {
        int index1 = 0;
        int index2 = 0;
        boolean allNull1 = true;
        boolean allNull2 = true;
        while (index1 < firstTeam.length && index2 < secondTeam.length) {
            Pet pet1 = firstTeam[index1];
            if (pet1 == null) {
                index1++;
                continue;
            } else {
                allNull1 = false;
            }
            Pet pet2 = secondTeam[index2];
            if (pet2 == null) {
                index2++;
                continue;
            } else {
                allNull2 = false;
            }
            //System.out.println(pet1.toString() + " VERSUS " + pet2.toString());
            pet1.attackPet(pet2);
            pet2.attackPet(pet1);
            if (pet1.getHealth() <= 0) {
                firstTeam[index1] = null;
                index1++;
            }
            if (pet2.getHealth() <= 0) {
                secondTeam[index2] = null;
                index2++;
            }
        }
        if ((allNull1 && allNull2) || (index1 == firstTeam.length && index2 == secondTeam.length)) {
            System.out.println("Both teams fainted.");
        } else if (allNull1 || index1 == firstTeam.length) {
            System.out.println("The second team won!");
        } else {
            System.out.println("The first team won!");
        }
    }
    /**
     * gets first team in this field.
     *
     * @return first team in this field
     */
    public Pet[] getFirstTeam() {
        return firstTeam;
    }
    /**
     * changes value of firstTeam.
     *
     * @param newTeam  new value of this team
     */
    public void setFirstTeam(Pet[] newTeam) {
        firstTeam = newTeam;
    }
    /**
     * gets second team in this field.
     *
     * @return second team in this field
     */
    public Pet[] getSecondTeam() {
        return secondTeam;
    }
    /**
     * changes value of secondTeam.
     *
     * @param newTeam  new value of this team
     */
    public void setSecondTeam(Pet[] newTeam) {
        secondTeam = newTeam;
    }
    /**
     * main method, involves testing codes.
     *
     * @param args  System input, nothing taken in for this main method
     */
    public static void main(String[] args) {
        Pet[] t1 = new Pet[] {new Hippo(2, 2, 2), new Skunk(1, 3, 3), new Turtle(10, 50, true)};
        Pet[] t2 = new Pet[] {new Hippo(3, 3, 3), new Skunk(-9, 3, 3), new Turtle(10, 5, true)};
        PetBattlefield field = new PetBattlefield(t1, t2);
        field.compareTeams();
        System.out.println(field.toString());
        field.battle();
        System.out.println(field.toString());
        System.out.println("NEW BATTLE ============================================");
        Pet[] t3 = new Pet[] {new Hippo(2, 2, 2), new Skunk(1, 3, 3), new Turtle(2, 5, true)};
        Pet[] t4 = new Pet[] {new Hippo(), new Skunk(), null, new Turtle()};
        // Pet[] t3 = new Pet[] {null, null, new Hippo(), null, null, null};
        // Pet[] t4 = new Pet[] {null, null, new Hippo()};
        PetBattlefield field2 = new PetBattlefield(t3, t4);
        field2.compareTeams();
        System.out.println(field2.toString());
        field2.battle();
        System.out.println(field2.toString());
    }
}