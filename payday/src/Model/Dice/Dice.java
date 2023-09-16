package Model.Dice;

import java.util.Random;

/**
 * Class is responsible for modeling a dice
 */
public class Dice {

    private String Icon;
    private int num;
    private boolean wasRolled;

    /**
     * Constructor of each player's dice. <br>
     * Default value of dice is 1.
     *
     */
    public Dice() {
        num = 1;
        wasRolled = false;
    }

    /**
     *
     * @return random number in range 1-6
     */
    public int roll() {
        Random rand = new Random();
        num = rand.nextInt(6) + 1;
        return num;
    }

    public int getNum() {
        return num;
    }

    public boolean WasRolled() {
        return wasRolled;
    }

    public void setWasRolled(boolean wasRolled) {
        this.wasRolled = wasRolled;
    }

    /**
     * Accessor
     * @return Icon URL of dice
     */
    public String getIcon() {
        return Icon;
    }

    /**
     * Transformer
     * @param Icon new Icon URL of dice
     */
    public void setIcon(String Icon) {
        this.Icon = Icon;
    }
}
