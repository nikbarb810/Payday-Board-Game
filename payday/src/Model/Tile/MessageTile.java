package Model.Tile;

public class MessageTile extends Tile{

    private final int drawCount;

    /**
     * @param icon String containing the tiles' icon URL
     * @param drawCount Integer describing the number of cards that will be drawn
     */
    public MessageTile(String icon, int drawCount) {
        super(icon);
        this.drawCount = drawCount;
    }

    /**
     * Accessor
     * @return Integer of drawn cards
     */
    public int getDrawCount() {
        return drawCount;
    }


}
