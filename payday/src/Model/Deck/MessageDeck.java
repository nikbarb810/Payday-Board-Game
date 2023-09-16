package Model.Deck;

import Model.Cards.Card;
import Model.Cards.MessageCards.MessageCard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class containing 2 Arraylists that simulates a deck.
 * <p>
 * First ArrayList is used to hold every card that is available to be played.<br>
 * Second ArrayList is used to hold every card that has been played already.
 */

public class MessageDeck {

    private ArrayList<MessageCard> activeStack; // arraylist containing all available  Cards
    private ArrayList<MessageCard> playedStack; // arraylist containing all played Cards

    /**
     * Constructor
     */
    public MessageDeck() {
        activeStack = new ArrayList<MessageCard>();
        playedStack = new ArrayList<MessageCard>();
    }

    public ArrayList<MessageCard> getActiveStack() {
        return activeStack;
    }

    public int getActiveSize() {
        return activeStack.size();
    }

    /**
     * Transformer
     * <p>
     * Adds input Card to the active deck
     * @param c Card to be added
     */
    public void addToActiveDeck(MessageCard c) {
        activeStack.add(c);
    }

    /**
     * Observer
     * @return true if active deck is empty; false otherwise
     */
    public boolean isEmpty() {return activeStack.isEmpty();}

    /**
     * Draws Card from the top of the deck<p>
     * Method DOES NOT remove card from active deck.<p>
     * User MUST manually:<br> 1.Discard it using removeCard or <br> 2.Move it to played deck using moveToPlayed.
     * @return Top Card
     */
    public MessageCard drawCard() {
        if(activeStack.isEmpty()) {
            reshuffleDeck();
        }
        return activeStack.remove(activeStack.size() - 1);
    }



    /**
     * Transformer<p>
     * Remove Input Card from active deck.
     * @param c Input Card
     */
    public void removeCard(MessageCard c) {
        if(!activeStack.contains(c))
            throw new IllegalArgumentException("Card does not exist in active deck!");

        activeStack.remove(c);
    }


    /**
     * Transformer
     * <p>
     * Adds input card to played deck.
     * @param c card to be copied/removed
     *
     *
     */
    public void moveToPlayed(MessageCard c) {
        activeStack.remove(c);
        playedStack.add(c);
    }

    /**
     * Transformer
     *
     * <p>
     *Adds all played cards to active deck and reshuffles it
     */
    public void reshuffleDeck() {
        if(activeStack.isEmpty() && playedStack.isEmpty())
            throw new RuntimeException("Both active and played decks are empty!");

        activeStack.addAll(playedStack);
        playedStack.clear();

        Collections.shuffle(activeStack);
    }
}
