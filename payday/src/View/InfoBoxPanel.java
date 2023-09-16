package View;

import Model.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class is responsible for creating the info box of our GUI
 */
public class InfoBoxPanel extends JTextArea {


    private String[] infoMessages; // Arraylist that will hold each message
    /**
     * Constructor used to create default instance of our Info Box
     */
    public InfoBoxPanel() {
        super();
        infoMessages = new String[5];
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoMessages[0] = "Info Box";
        setText(infoMessages[0]);
        setFont(new Font("Serif",Font.PLAIN,16));
    }

    public void updatePlayer(Player currPlayer) {
        infoMessages[1] = "Current Month: " + currPlayer.getCurrentMonth();
        infoMessages[2] = "Turn: " + currPlayer.getName();
        if(infoMessages[3] == null ) {
            infoMessages[3] = "";
        }
        if(infoMessages[4] == null) {
            infoMessages[4] = "";
        }
        setText(infoMessages[0] + "\n\n" + infoMessages[1] + "\n" + infoMessages[2] + "\n" + infoMessages[3]);
        setFont(new Font("Serif",Font.PLAIN,16));
    }

    /**
     * Update Info Box to show new message
     *
     * @param message new message to be displayed
     */
    public void updateMessage(String message) {

        infoMessages[3] = infoMessages[4];
        infoMessages[4] = message;
        setText(infoMessages[0] + "\n\n" + infoMessages[1] + "\n" + infoMessages[2] + "\n" + infoMessages[3] + "\n" + infoMessages[4]);
        setFont(new Font("Serif",Font.PLAIN,16));
    }
}
