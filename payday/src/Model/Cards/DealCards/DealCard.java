package Model.Cards.DealCards;

import Model.Cards.Card;
import Model.Deck.DealDeck;
import Model.Model;
import Model.Player.Player;

/**
 * Class that will be used to implement every Deal Card
 */
public class DealCard extends Card {
    private int cost;

    /**
     * Constructor for creating a Deal card
     *
     * @param message String containing the message of the card
     * @param value   Integer describing the value of the card in euros
     * @param cost    Integer describing the cost of the card in euros
     * @param icon    String containing the card Icon URL
     */
    public DealCard(String message, int cost, int value, String icon) {
        super(message, value, icon);
        this.cost = cost;
    }

    /**
     * Accessor
     * @return int describing the cost of the card in euros
     */
    public int getCost() {
        return cost;
    }

    /**
     * Accessor
     * @param amount new cost amount
     */
    public void setCost(int amount) {
        cost = amount;
    }


    /**
     *
     * @param p Player who drew this Deal Card
     * @param didBuy Choice of player to purchase Deal Card
     */
    public void cardAction(Player p, DealDeck deck, boolean didBuy) {
        if(didBuy) {
            p.updateBalance(-this.cost);
            p.addPlayerDealCard(this);

            while(p.getBalance() < 0) {     //if player doesn't have enough money
                p.updateBalance(1000);
                p.updateLoanAmount(1000);
            }
        } else {
            deck.moveToPlayed(this);
        }
    }
}
