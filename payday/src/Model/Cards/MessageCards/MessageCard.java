package Model.Cards.MessageCards;

import Model.Cards.Card;
import Model.Model;
import Model.Player.Player;

/**
 * Abstract class that will be used for implementing every type of Message Card
 */

public abstract class MessageCard extends Card {

    private String choice;
    private String type;
    /**
     * Constructor for creating a card
     * @param type String containing the type of the card
     * @param message String containing the message of the card
     * @param choice String containing the choice of the card ******
     * @param value   Integer describing the value of the card in euros
     * @param icon    String containing the card's Icon URL
     */
    public MessageCard(String type, String message, String choice, int value, String icon) {
        super(message, value, icon);
        this.choice = choice;
        this.type = type;
    }

    public String getChoice() {
        return choice;
    }

    public String getType() {
        return type;
    }

    public abstract void cardAction(Player p, Model m);
}
