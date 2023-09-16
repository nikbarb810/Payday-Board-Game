package Model;

import Model.Board.Board;
import Model.Deck.DealDeck;
import Model.Deck.MessageDeck;
import Model.Jackpot.Jackpot;
import Model.Player.Player;

/**
 * Class is used to bundle every object of the game model into one class.
 */
public class Model {

    public Player p1;
    public Player p2;
    public Board board;
    public MessageDeck messageDeck;
    public DealDeck dealDeck;
    public Jackpot jackpot;

    /**
     * Default constructor for our game model
     */
    public Model() {
        p1 = new Player("Player1");
        p2 = new Player("Player2");

        board = new Board();

        messageDeck = new MessageDeck();
        dealDeck = new DealDeck();

        jackpot = new Jackpot();
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Board getBoard() {
        return board;
    }

    public MessageDeck getMessageDeck() {
        return messageDeck;
    }

    public DealDeck getDealDeck() {
        return dealDeck;
    }

    public Jackpot getJackpot() {
        return jackpot;
    }
}
