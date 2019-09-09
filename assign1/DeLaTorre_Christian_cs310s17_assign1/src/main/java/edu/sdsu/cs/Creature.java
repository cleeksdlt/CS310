package edu.sdsu.cs;

/**
 * assign1
 * Christian De La Torre
 * account: cssc0650
 * San Diego State Univeristy
 * CS310 Data Structures, Spring 2017
 * Date: 02/21/17
 * assign1
 * This program implements the Comparable interface in order to do the compareTo method
 * and return a value to Driver class, and contains all of the pokemons attributes and characteristics
 */

public class Creature implements Comparable<Creature> {
    String name;
    String type1;
    String type2;
    int total;
    int hp;
    int attack;
    int defense;
    int spAtk;
    int spDef;
    int speed;
    int generation;
    boolean legendary;
    boolean isheroic;
    boolean isloser;
    int win;
    int lose;

    /**
     * Sets attributes of a certain pokemon to tempArray to be later stored within creatureData ArrayList
     * @param line
     */
    public Creature(String line) {
        String [] tempArray = line.split(",");
        this.name =  tempArray[1];
        this.type1 = tempArray[2];
        this.type2 = tempArray[3];
        this.total = Integer.parseInt(tempArray[4]);
        this.hp = Integer.parseInt(tempArray[5]);
        this.attack = Integer.parseInt(tempArray[6]);
        this.defense = Integer.parseInt(tempArray[7]);
        this.spAtk = Integer.parseInt(tempArray[8]);
        this.spDef = Integer.parseInt(tempArray[9]);
        this.speed = Integer.parseInt(tempArray[10]);
        this.generation = Integer.parseInt(tempArray[11]);
        this.legendary = Boolean.parseBoolean(tempArray[12]);
        this.win = 0;
        this.lose = 0;
        this.isheroic = false;
        this.isloser = false;
    }

    /**
     * Implements the Comparable interface and compares the first pokemon with the
     * second pokemon in the compareTo method. Which then returns a value and sets
     * the winner and loser within the Fight method.
     * @param creature2
     * @return
     */
    public int Fight (Creature creature2) {
        int comparison = this.combatScore() - creature2.combatScore();
        int value = -1;
        if (comparison == 0) {
            int naturalorder = this.compareTo(creature2);
            if (naturalorder > 0) {
                value = 0;
                this.win++;
                creature2.lose++;
                this.attack = this.attack + experience(creature2.total);
                this.defense = this.defense + experience(creature2.total);
                if (isHeroic(this.win, this.legendary) == true) {
                    this.isheroic = true;
                }
                if (checkLegendary(creature2.lose, creature2.legendary) == true) {
                   creature2.isloser = true;
                }
                return value;
            }
            if (naturalorder < 0) {
                value = 1;
                creature2.win++;
                this.lose++;
                creature2.attack = creature2.attack + experience(this.total);
                creature2.defense = creature2.defense + experience(this.total);
                if (isHeroic(creature2.win, creature2.legendary) == true) {
                    creature2.isheroic = true;
                }
                if (checkLegendary(this.lose, this.legendary) == true) {
                    this.isloser = true;
                }
            } else {
                int alphabetical = this.name.compareTo(creature2.name);
                if (alphabetical > 0) {
                    value = 0;
                    this.win++;
                    creature2.lose++;
                    this.attack = this.attack + experience(creature2.total);
                    this.defense = this.defense + experience(creature2.total);
                    if (isHeroic(this.win, this.legendary) == true) {
                        this.isheroic = true;
                    }
                    if (checkLegendary(creature2.lose, creature2.legendary) == true) {
                        creature2.isloser = true;
                    }
                    return value;
                } else if (alphabetical < 0) {
                    value = 1;
                    creature2.win++;
                    this.lose++;
                    creature2.attack = creature2.attack + experience(this.total);
                    creature2.defense = creature2.defense + experience(this.total);
                    if (isHeroic(creature2.win, creature2.legendary) == true) {
                        creature2.isheroic = true;
                    }
                    if (checkLegendary(this.lose, this.legendary) == true) {
                        this.isloser = true;
                    }
                }
            }
        }
        if (comparison > 0) {
            value = 0;
            this.win++;
            creature2.lose++;
            this.attack = this.attack + experience(creature2.total);
            this.defense = this.defense + experience(creature2.total);
            if (isHeroic(this.win, this.legendary) == true) {
                this.isheroic = true;
            }
            if (checkLegendary(creature2.lose, creature2.legendary) == true) {
                creature2.isloser = true;
            }
        }
        if (comparison < 0) {
            value = 1;
            creature2.win++;
            this.lose++;
            creature2.attack = creature2.attack + experience(this.total);
            creature2.defense = creature2.defense + experience(this.total);
            if (isHeroic(creature2.win, creature2.legendary) == true) {
                creature2.isheroic = true;
            }
            if (checkLegendary(this.lose, this.legendary) == true) {
                this.isloser = true;
            }
        }
        return value;
    }

    /**
     * Writes to outfile
     * @return
     */
    @Override
    public String toString () {
        String output;
        if(this.isheroic) {
            output = ("Heroic " + name +  "[Type: " + type1 + " " + type2 + "]");
        } else {
            output = (name + "[Type: " + type1 + " " + type2 + "]");
        }
        return output;
    }

    /**
     * Compares the first pokemon with the second pokemon in order to get a value
     * and compare the value to different if-statements
     * @param obj
     * @return
     */
    @Override
    public int compareTo(Creature obj) {
        int creature1 = this.hp + this.attack + this.defense + this.spAtk + this.spDef + this.speed;
        int creature2 = obj.hp + obj.attack + obj.defense + obj.spAtk + obj.spDef + obj.speed;
        return (creature1 - creature2);
    }

    /**
     * Returns the combat score to both the first and second pokemon
     * @return
     */
    public int combatScore () {
        return speed/2 * (attack + spAtk/2) + (defense + spDef/2);
    }

    /**
     * Returns the gained experience of winning pokemon
     * @param experience - returns the amount of experience the pokemon has won
     * @return
     */
    public int experience (int experience) {
        return experience / 2;
    }

    /**
     * After 3 wins, checks to see if non-legendary pokemon can be considered for
     * heroic status or not and returns true or false values.
     * @param win - the amount of times the pokemon has won
     * @param legendary - the status of the pokemon
     * @return
     */
    public boolean isHeroic (int win, boolean legendary) {
        if (win == 3 && legendary == false) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if losing pokemon is legendary or not, to determine
     * whether pokemon has officially lost or not.
     * @param lose - the amount of times the pokemon has lost
     * @param legendary - the status of the pokemon
     * @return
     */
    public boolean checkLegendary (int lose, boolean legendary) {
        if (lose == 3 && legendary == true) {
                return true;
        }
        return false;
    }
}
