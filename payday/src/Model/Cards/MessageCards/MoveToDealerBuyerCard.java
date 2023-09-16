package Model.Cards.MessageCards;

import Model.Board.Board;
import Model.Model;
import Model.Player.Player;
import Model.Tile.BuyerTile;
import Model.Tile.DealTile;

public class MoveToDealerBuyerCard extends MessageCard {
    /**
     * Constructor for creating a Move to Dealer/Buyer card
     *
     * @param message String containing the message of the card
     * @param choice String containing the choice of the card
     * @param value   Integer describing the value of the card in euros
     * @param icon    String containing the card Icon URL
     */
    public MoveToDealerBuyerCard(String type, String message, String choice, int value, String icon) {
        super(type, message, choice, value, icon);
    }

    /**
     * Updates Player's position to next DealTile/BuyerTile it encounters; <br>
     * If it doesn't find any, position stays the same
     *
     * @param p Player that will be moved
     * @param m Model of the game
     */
    public void cardAction(Player p, Model m) {
        Board b = m.getBoard();
        for(int i = p.getPosition(); i < 31; i++) {
            if(b.getTile(i) instanceof DealTile || b.getTile(i) instanceof BuyerTile) {
                p.setPosition(i);
                break;
            }
        }
    }
}
