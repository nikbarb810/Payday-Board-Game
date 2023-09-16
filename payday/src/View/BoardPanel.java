package View;

import Model.Board.Board;
import Model.Jackpot.Jackpot;
import Model.Model;
import Model.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Class is responsible for creating the JLayeredPane that will hold our board and pawn GUI
 */
public class BoardPanel extends JLayeredPane {

    private Model m;

    private GridBagConstraints gbc; //constraints of our panel

    private JLabel[] boardLabel;

    private JLabel pawnA; // pawn of player A
    private JLabel pawnB; // pawn of player B

    private JLabel jackpotTile;

    /**
     * Constructor used to initialize default state of board
     * @param m Model of game that will be displayed
     *
     */
    public BoardPanel(Model m) {
        this.m = m;
        this.boardLabel = new JLabel[m.getBoard().getBoardCount()];
        gbc = new GridBagConstraints();

        loadBoard(m.getBoard(),m.getJackpot());
        setOpaque(false);

    }

    private void loadBoard(Board b, Jackpot j) {
        setLayout(new GridBagLayout());

        String[] datesStr = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        gbc.insets = new Insets(1,1,1,1);   //set distance between cells

        gbc.gridx = 0;
        gbc.gridy = 0;

        //set first tile of board(start tile)
        JLabel date =  new JLabel();
        date.setText("Start");
        date.setPreferredSize(new Dimension(170,30));
        date.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        date.setBackground(Color.yellow);
        date.setOpaque(true);
        add(date,gbc);

        String URL = b.getTile(0).getIcon();
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(170,140,Image.SCALE_SMOOTH));

        boardLabel[0] = new JLabel();
        boardLabel[0].setIcon(imgIcon);
        boardLabel[0].setBorder(BorderFactory.createLineBorder(Color.black));
        boardLabel[0].setOpaque(true);

        gbc.gridy = 1;

        add(boardLabel[0],gbc,Integer.valueOf(2));

        //set pawn of player A
        pawnA = new JLabel();
        URL = "resources/images/pawn_blue.png";
        imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));

        pawnA.setIcon(imgIcon);
        pawnA.setOpaque(false);
        pawnA.setHorizontalAlignment(SwingConstants.LEFT);
        //pawnA.setHorizontalAlignment(SwingConstants.LEFT);
        add(pawnA,gbc,Integer.valueOf(0));

        //set pawn of Player B
        pawnB = new JLabel();
        URL = "resources/images/pawn_yellow.png";
        imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));

        pawnB.setIcon(imgIcon);
        pawnB.setOpaque(false);
        pawnB.setHorizontalAlignment(SwingConstants.RIGHT);
        //pawnB.setHorizontalAlignment(SwingConstants.RIGHT);

        add(pawnB,gbc,Integer.valueOf(0));



        // load all tiles into JLabel array
        int x = 1;
        int y = 0;
        int strIndex = 0;
        for(int i = 1; i < 32; i++) {

            gbc.gridx = x;
            gbc.gridy = y;
            date = new JLabel();
            date.setText(datesStr[strIndex] + " " + i);
            date.setPreferredSize(new Dimension(170,30));
            date.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            date.setBackground(Color.yellow);
            date.setOpaque(true);
            add(date,gbc);

            URL = b.getTile(i).getIcon();
            imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(170,140,Image.SCALE_SMOOTH));


            boardLabel[i] = new JLabel();
            boardLabel[i].setIcon(imgIcon);

            //set restraints
            gbc.gridy = y + 1;

            add(boardLabel[i],gbc);

            x++;
            if(x == 7) {
                x = 0;
                y += 2;
            }
            strIndex++;
            if(strIndex == 7) {
                strIndex = 0;
            }

        }
        //set Jackpot Tile
        jackpotTile = new JLabel();
        URL = "resources/images/jackpot.png";
        imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(280,120,Image.SCALE_SMOOTH));
        jackpotTile.setIcon(imgIcon);
        jackpotTile.setText("Jackpot: " + j.getBalance() + " Euros");
        jackpotTile.setFont(new Font("Serif",Font.BOLD,25));
        jackpotTile.setForeground(Color.WHITE);

        gbc.gridwidth = 3;
        gbc.gridx = x;

        add(jackpotTile,gbc);

    }

    public void update() {
        updatePawn(m.getP1(),m.getP1().getPosition());
        updatePawn(m.getP2(),m.getP2().getPosition());
        jackpotTile.setText("Jackpot: " + m.getJackpot().getBalance() + " Euros");

    }

    /**
     * Update position of pawn to new one
     * @param p Player's pawn that will move
     * @param position index of new position
     */
    private void updatePawn(Player p, int position) {
        gbc.gridwidth = 1;
        gbc.gridx =(position % 7);
        gbc.gridy = ((position / 7) * 2) + 1;
        if(p.getName().equals("Player1")) {
            add(pawnA,gbc,Integer.valueOf(0));
        } else {
            add(pawnB,gbc,Integer.valueOf(0));
        }
    }



}
