package View;

import Model.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Class, extending Jpanel, creates a panel that will display all info for each player
 */
public class PlayerBoxPanel extends JPanel {

    //all the variables and gui components we need
    private Player p;

    private JLabel nameLabel;

    private JLabel balanceField;
    private JLabel loanField;
    private JLabel billField;

    private JButton rollDiceButton;
    private JButton dealCardsButton;
    private JButton loanButton;

    private JLabel dice;


    /**
     * Default Constructor for a JPanel that will display Player's information<br>
     * Will be filled to initialize default values for each swing object.
     *
     * @param p Player that will be displayed
     */
    public PlayerBoxPanel(Player p) {
        this.p = p;
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        setLayout(null);

        //setup each label of player panel
        nameLabel = new JLabel();
        balanceField = new JLabel();
        loanField = new JLabel();
        billField = new JLabel();

        rollDiceButton = new JButton();
        dealCardsButton = new JButton();
        loanButton = new JButton();

        dice = new JLabel();


        nameLabel.setBounds(3,0,250,50);
        nameLabel.setText(p.getName());
        nameLabel.setFont(new Font("Serif",Font.BOLD,28));
        add(nameLabel);

        balanceField.setBounds(3,70,190,20);
        balanceField.setText("Money : " + p.getBalance() + " Euros");
        balanceField.setFont(new Font("Serif",Font.PLAIN,20));
        add(balanceField);

        loanField.setBounds(3,95,190,20);
        loanField.setText("Loan : " + p.getLoanAmount() + " Euros");
        loanField.setFont(new Font("Serif",Font.PLAIN,20));
        add(loanField);

        billField.setBounds(3,120,190,20);
        billField.setText("Bills: " + p.getBillsAmount() + " Euros");
        billField.setFont(new Font("Serif",Font.PLAIN,20));
        add(billField);

        rollDiceButton.setText("Roll Dice");
        rollDiceButton.setBounds(3,170,160,30);
        add(rollDiceButton);

        dealCardsButton.setText("My Deal Cards");
        dealCardsButton.setBounds(3,205,160,30);
        add(dealCardsButton);

        loanButton.setText("Get Loan");
        loanButton.setBounds(3,240,160,30);
        add(loanButton);


        String URL = "resources/images/dice-1.jpg";
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(85,85,Image.SCALE_SMOOTH));
        dice.setIcon(imgIcon);
        dice.setBounds(185,180,85,85);
        add(dice);

    }

    public void update() {
        updateBalanceField();
        updateLoanField();
        updateBillField();
        updateDiceLabel();
    }

    /**
     * Update Player's balance field
     *
     */
    private void updateBalanceField() {
        balanceField.setText("Money : " + p.getBalance() + " Euros");
    }

    /**
     * Update Player's loan field
     *
     */
    private void updateLoanField() {
        loanField.setText("Loan : " + p.getLoanAmount() + " Euros");
    }

    /**
     * Update Player's bill field
     *
     */
    private void updateBillField() {
        billField.setText("Bills : " + p.getBillsAmount() + " Euros");
    }

    /**
     * Update Dice Icon
     *
     */
    private void updateDiceLabel() {
        String URL = "resources/images/dice-" + p.getDice().getNum() + ".jpg";
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(85,85,Image.SCALE_SMOOTH));
        dice.setIcon(imgIcon);
        dice.setBounds(185,180,85,85);
        add(dice);
    }

    public JButton getRollDiceButton() {
        return rollDiceButton;
    }

    public JButton getDealCardsButton() {
        return dealCardsButton;
    }

    public JButton getLoanButton() {
        return loanButton;
    }

}

