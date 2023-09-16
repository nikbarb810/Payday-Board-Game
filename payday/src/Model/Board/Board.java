package Model.Board;

import Model.Tile.Tile;

/**
 * Class implements the board of the game containing all the tiles
 */
public class Board {

    private static final int TILE_COUNT = 32;

    private Tile[] board;

    public Board() {
        board = new Tile[TILE_COUNT];
    }

    /**
     * Transformer
     * <p>
     * Sets input Tile into input index on the board
     * @param t input Tile
     * @param index input index
     * <p>
     * Precondition: index smaller than board.size()
     */
    public void setTile(Tile t,int index) {
        board[index] = t;
    }

    /**
     * Observer
     * <p>
     * Method returns the Tile in given index
     * @param index input index
     * @return Tile
     * <p>
     * Precondition: index smaller than board.size()
     */
    public Tile getTile(int index) {
        return board[index];
    }

    /**
     * Accessor
     * @return size of board
     */
    public int getBoardCount() {return TILE_COUNT;}

    public Tile[] getBoard() {return board;}
}
