package Model.Deck;

import Model.Cards.Card;
import Model.Cards.DealCards.DealCard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class containing 2 ArrayLists that simulates the dealDeck
 * <p>
 * First one is used to hold every card that is available
 * Second one is used to hold every card that has been discarded
 */
public class DealDeck {
    private ArrayList<DealCard> activeStack;
    private ArrayList<DealCard> playedStack;

    public DealDeck() {
        activeStack = new ArrayList<DealCard>();
        playedStack = new ArrayList<DealCard>();
    }

    /**
     * Transformer
     * <p>
     * Adds input card to the active deck
     * @param c DealCard to be added
     */
    public void addToActiveDeck(DealCard c) {activeStack.add(c);}

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
    public DealCard drawCard() {
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
    public void removeCard(DealCard c) {
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
    public void moveToPlayed(DealCard c) {
       // if(!activeStack.contains(c)) throw new IllegalArgumentException();

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
