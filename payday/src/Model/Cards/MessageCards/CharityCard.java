package Model.Cards.MessageCards;

import Model.Jackpot.Jackpot;
import Model.Model;
import Model.Player.Player;

public class CharityCard extends MessageCard{
    /**
     * Constructor for creating a Charity card
     *
     * @param message String containing the message of the card
     * @param choice String containing the choice of the card
     * @param value   Integer describing the value of the card in euros
     * @param icon    String containing the card Icon URL
     */
    public CharityCard(String type, String message, String choice, int value, String icon) {
        super(type,message, choice, value, icon);
    }


    public void cardAction(Player p, Model m) {

        Jackpot jackpot = m.getJackpot();
        jackpot.updateBalance(this.getValue());
        p.updateBalance(-this.getValue());

        while(p.getBalance() < 0) {     //if player doesn't have enough money
            p.updateBalance(1000);
            p.updateLoanAmount(1000);
        }

    }
}
