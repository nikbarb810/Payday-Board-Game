package View;

import Model.Model;
import Model.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Class is responsible only for creating the GUI.<br>
 * Everything else from initializing the default instance to controlling the logic of the game MUST be done in the Controller.
 *
 */

public class View {

    private JFrame frame; //frame of our GUI

    private PlayerBoxPanel p1; // GUI of player1
    private PlayerBoxPanel p2; // GUI of player2

    private JLabel logoLabel;

    private BoardPanel board; //board GUI

    private InfoBoxPanel infoBox; //infobox GUI

    private JButton messageCardButton;
    private JButton dealCardButton;

    private Model m;


    /**
     * Constructor used to create default instance of our GUI
     * @param m Model of the game that will be displayed
     */
    public View(Model m) {
        this.m = m;
        frame = new JFrame();
        initFrame();

        //set both players' box panels
        p1 = new PlayerBoxPanel(m.getP1());
        p1.setBounds(1450,70,300,280);
        frame.add(p1);

        p2 = new PlayerBoxPanel(m.getP2());
        p2.setBounds(1450,700,300,280);
        frame.add(p2);

        //set board and jackpot panel
        board = new BoardPanel(m);
        board.setBounds(20,150,1260,920);
        frame.add(board);

        //set infobox panel
        infoBox = new InfoBoxPanel();
        infoBox.setBounds(1450,400,300,150);
        frame.add(infoBox);

        //set logo label
        logoLabel = new JLabel();
        String URL = "resources/images/logo.png";
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(700,165,Image.SCALE_SMOOTH));
        logoLabel.setIcon(imgIcon);
        logoLabel.setBounds(310,0,imgIcon.getIconWidth(),imgIcon.getIconHeight());
        frame.add(logoLabel);

        //set messageCard button
        messageCardButton = new JButton();
        URL = "resources/images/mailCard.png";
        imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(145,70,Image.SCALE_SMOOTH));
        messageCardButton.setIcon(imgIcon);
        messageCardButton.setBounds(1450,590,145,70);
        frame.add(messageCardButton);

        //set dealCard button
        dealCardButton = new JButton();
        URL = "resources/images/dealCard.png";
        imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(145,70,Image.SCALE_SMOOTH));
        dealCardButton.setIcon(imgIcon);
        dealCardButton.setBounds(1605,590,145,70);
        frame.add(dealCardButton);

        frame.setVisible(true);
    }

    /**
     * Method sets up our basic frame that will hold every object of our GUI
     */
    private void initFrame() {
        frame.setTitle("Payday");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1920,1080);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.darkGray);

        //set background image
        String URL = "resources/images/bg_green.png";
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(1920,1080,Image.SCALE_SMOOTH));
        frame.setContentPane(new JLabel(imgIcon));
    }

    /**
     * Method updates all fields (labels,textfields,pawns,jackpot) and repaints main frame
     */
    public void update() {
        p1.update();
        p2.update();
        board.update();

        frame.repaint();
        frame.validate();
    }

    /**
     * Updates Player's information of info panel
     * @param p Player to be displayed
     */
    public void updateInfoPanelPlayer(Player p) {
        infoBox.updatePlayer(p);
    }

    /**
     * Method updates last line of info panel
     * @param message String to be displayed
     */
    public void updateInfoPanelMessage(String message) {
        infoBox.updateMessage("--> " + message);
    }


    public PlayerBoxPanel getP1() {
        return p1;
    }

    public PlayerBoxPanel getP2() {
        return p2;
    }

    public JButton getMessageCardButton() {
        return messageCardButton;
    }

    public JButton getDealCardButton() {
        return dealCardButton;
    }

}
