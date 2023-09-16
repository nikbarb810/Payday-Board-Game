package Model.Cards.MessageCards;

import Model.Model;
import Model.Player.Player;

public class AdvertisementCard extends MessageCard{
    /**
     * Constructor for creating an Advertisement card
     *
     * @param message String containing the message of the card
     * @param choice String containing the choice of the card
     * @param value   Integer describing the value of the card in euros
     * @param icon    String containing the card Icon URL
     */
    public AdvertisementCard(String type, String message, String choice, int value, String icon) {
        super(type,message, choice, value, icon);
    }

    public void cardAction(Player p1, Model m) {
        p1.updateBalance(this.getValue());

    }
}
