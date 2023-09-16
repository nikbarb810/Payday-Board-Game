package Model.Cards;

/**
 * Abstract class describing a card of the game
 */

public abstract class Card {
    private final String message;
    private final int value;
    private final String icon;

    /**
     * Constructor for creating a card
     * @param message String containing the message of the card
     * @param value Integer describing the value of the card in euros
     * @param icon  String containing the card Icon URL
     */
    public Card(String message, int value, String icon) {
        this.message = message;
        this.value = value;
        this.icon = icon;
    }

    /**
     * Accessor
     * @return String containing the card's message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Accessor
     * @return Integer describing the value of the card in euros
     */
    public int getValue() {
        return value;
    }

    /**
     * Accessor
     * @return String containing the card's Icon URL
     */
    public String getIcon() {
        return icon;
    }

}
