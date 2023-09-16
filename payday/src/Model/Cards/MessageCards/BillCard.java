package Model.Cards.MessageCards;

import Model.Model;
import Model.Player.Player;

public class BillCard extends MessageCard{
    /**
     * Constructor for creating a Bill card
     *
     * @param message String containing the message of the card
     * @param choice String containing the choice of the card
     * @param value   Integer describing the value of the card in euros
     * @param icon    String containing the card Icon URL
     */
    public BillCard(String type,String message, String choice, int value, String icon) {
        super(type,message, choice, value, icon);
    }

    /**
     *  Adds card's value into bill amount of a Player
     * @param p Input Player
     */
    public void cardAction(Player p, Model m) {
        p.updateBillsAmount(this.getValue());

    }
}
