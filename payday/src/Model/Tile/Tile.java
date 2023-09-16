package Model.Tile;

/**
 * Abstract class that will be used to create every type of Tile
 *
 */

public abstract class Tile {

    private final String icon;

    /**
     *
     * @param icon String containing the tiles' icon URL
     */
    public Tile(String icon) {
        this.icon = icon;
    }

    /**
     * Accessor
     * @return String containing the tiles' icon URL
     */
    public String getIcon() {
        return icon;
    }
}
