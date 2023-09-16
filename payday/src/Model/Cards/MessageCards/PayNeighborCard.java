package Model.Cards.MessageCards;

import Model.Model;
import Model.Player.Player;

public class PayNeighborCard extends MessageCard{

    /**
     * Constructor for creating a Pay Neighbor card
     *
     * @param message String containing the message of the card
     * @param choice String containing the choice of the card
     * @param value   Integer describing the value of the card in euros
     * @param icon    String containing the card Icon URL
     */
    public PayNeighborCard(String type, String message, String choice, int value, String icon) {
        super(type, message, choice, value, icon);
    }


    public void cardAction(Player payingPlayer, Model m) {

        Player recievingPlayer;
        if(payingPlayer == m.getP1()) {
            recievingPlayer = m.getP2();
        } else {
            recievingPlayer = m.getP1();
        }
        payingPlayer.updateBalance(-this.getValue());

        while(payingPlayer.getBalance() < 0) {      //if player doesn't have enough money
            payingPlayer.updateBalance(1000);
            payingPlayer.updateLoanAmount(1000);
        }
        recievingPlayer.updateBalance(this.getValue());
    }
}
