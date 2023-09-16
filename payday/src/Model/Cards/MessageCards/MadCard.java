package Model.Cards.MessageCards;

import Model.Model;
import Model.Player.Player;

public class MadCard extends MessageCard{
    /**
     * Constructor for creating a Take from Neighbor card
     *
     * @param message String containing the message of the card
     * @param choice String containing the choice of the card
     * @param value   Integer describing the value of the card in euros
     * @param icon    String containing the card Icon URL
     */
    public MadCard(String type, String message, String choice , int value, String icon) {
        super(type, message, choice, value, icon);
    }


    public void cardAction(Player recievingPlayer, Model m) {

        Player payingPlayer;

        if(recievingPlayer == m.getP1()) {
            payingPlayer = m.getP2();
        } else {
            payingPlayer = m.getP1();
        }

        recievingPlayer.updateBalance(this.getValue());
        payingPlayer.updateBalance(-this.getValue());

        while(payingPlayer.getBalance() < 0) {      //if player doesn't have enough money
            payingPlayer.updateLoanAmount(1000);
            payingPlayer.updateBalance(1000);
        }

    }
}
