package Controller;


import Model.Cards.DealCards.DealCard;
import Model.Cards.MessageCards.*;
import Model.Dice.Dice;
import Model.Model;
import Model.Player.Player;
import Model.Tile.*;
import View.View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


import static java.lang.Integer.parseInt;

/**
 * Class is responsible for the logic of the game as well as coordinating the interactions between our model and our view
 */
public class Controller implements ActionListener {

    private Model model; //our model

    private Player currPlayer; //object describes whose player's turn it is
    private Player currOpponent; // object describes current opponent
    private int monthsToPlay;

    private View view;  //our gui


    /**
     * Constructor for Controller that is used to initialize default behavior<br>
     * Constructor also sets Message,Deal cards,Tiles,Players' turn,actionListeners and actionEvents
     *
     */
    public Controller() {
        model = new Model();

        decideFirstTurn();

        setDealDeck();
        setMessageDeck();
        setTiles();
        setMonthsPlayed();

        view = new View(model);
        setListeners();
        view.updateInfoPanelPlayer(currPlayer);
        view.updateInfoPanelMessage(currPlayer.getName() + " Roll Dice");
    }

    public static void main(String[] args) {

        Controller c = new Controller();

    }

    /**
     * Method loads Deal Cards into  dealDeck
     */
    private void setDealDeck()  {

        String path = "resources/dealCards.csv";
        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {

            String row;
            row = csvReader.readLine(); // skip first line
            while ((row = csvReader.readLine()) != null) {
                String[] values = row.split(","); // store each element of row into value array

                DealCard dealCard = new DealCard(values[2],parseInt(values[3]),parseInt(values[4]),values[5]);
                model.dealDeck.addToActiveDeck(dealCard); // add card to deck
            }
            model.dealDeck.reshuffleDeck();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method loads Message Cards into messageDeck
     */
    private void setMessageDeck() {
        String path = "resources/mailCards.csv";
        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {

            String row;
            row = csvReader.readLine(); // skip first line
            while ((row = csvReader.readLine()) != null) {
                String[] values = row.split(","); // store each element of row into value array

                switch(values[1]) {
                    case "Αdvertisement":
                        AdvertisementCard ac = new AdvertisementCard(values[0],values[2],values[3],parseInt(values[4]),values[5]);
                        model.messageDeck.addToActiveDeck(ac);
                        break;
                    case "Bill":
                        BillCard bc = new BillCard(values[0],values[2],values[3],parseInt(values[4]),values[5]);
                        model.messageDeck.addToActiveDeck(bc);
                        break;
                    case "Charity":
                        CharityCard cc = new CharityCard(values[0],values[2],values[3],parseInt(values[4]),values[5]);
                        model.messageDeck.addToActiveDeck(cc);
                        break;
                    case "PayTheNeighbor":
                        PayNeighborCard pc = new PayNeighborCard(values[0],values[2],values[3],parseInt(values[4]),values[5]);
                        model.messageDeck.addToActiveDeck(pc);
                        break;
                    case "MadMoney":
                        MadCard mc = new MadCard(values[0],values[2],values[3],parseInt(values[4]),values[5]);
                        model.messageDeck.addToActiveDeck(mc);
                        break;
                    case "MoveToDealBuyer":
                        MoveToDealerBuyerCard dbc = new MoveToDealerBuyerCard(values[0],values[2],values[3],parseInt(values[4]),values[5]);
                        model.messageDeck.addToActiveDeck(dbc);
                        break;
                }

            }
            model.messageDeck.reshuffleDeck();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method loads Tiles and sets up the board.
     */
    private void setTiles() {
        //Load all tiles except start,payday into tmpBoard
        ArrayList<Tile> tmpBoard = new ArrayList<Tile>();

        for(int i = 0; i < 4; i++) {
            MessageTile t = new MessageTile("resources/images/Tile_mc1.png",1);
            tmpBoard.add(t);
        }

        for(int i = 0; i < 4; i++) {
            MessageTile t = new MessageTile("resources/images/Tile_mc2.png",2);
            tmpBoard.add(t);
        }

        for(int i = 0; i < 5; i++) {
            DealTile t = new DealTile("resources/images/Tile_deal.png");
            tmpBoard.add(t);
        }

        for(int i = 0; i < 2; i++) {
            SweepstakesTile t = new SweepstakesTile("resources/images/Tile_sweep.png");
            tmpBoard.add(t);

            RadioTile rt = new RadioTile("resources/images/Tile_radio.png");
            tmpBoard.add(rt);

            CasinoTile ct = new CasinoTile("resources/images/Tile_casino.png");
            tmpBoard.add(ct);

            YardTile yt = new YardTile("resources/images/Tile_yard.png");
            tmpBoard.add(yt);
        }

        for(int i = 0; i < 3; i++) {
            LotteryTile lt = new LotteryTile("resources/images/Tile_lottery.png");
            tmpBoard.add(lt);
        }

        for(int i = 0; i < 6; i++) {
            BuyerTile bt = new BuyerTile("resources/images/Tile_buyer.png");
            tmpBoard.add(bt);
        }
        Collections.shuffle(tmpBoard);

        //set starting tile
        StartTile st = new StartTile("resources/images/Tile_start.png");
        model.board.setTile(st,0);

        //set random board tiles
        for(int i = 0; i < tmpBoard.size(); i++) {
            model.board.setTile(tmpBoard.get(i), i + 1);
        }

        //set payday tile
        PaydayTile pt = new PaydayTile("resources/images/Tile_pay.png");
        model.board.setTile(pt,31);
    }


    /**
     * Method is responsible for creating all listeners that will be used for GUI
     */
    private void setListeners() {
        view.getP1().getRollDiceButton().addActionListener(this);
        view.getP2().getRollDiceButton().addActionListener(this);

        view.getP1().getLoanButton().addActionListener(this);
        view.getP2().getLoanButton().addActionListener(this);

        view.getP1().getDealCardsButton().addActionListener(this);
        view.getP2().getDealCardsButton().addActionListener(this);


        view.getDealCardButton().addActionListener(this);
        view.getMessageCardButton().addActionListener(this);
    }

    private void setMonthsPlayed() {
        String[] months = {"1","2","3"};
        JComboBox comboBox = new JComboBox(months);

        JOptionPane.showMessageDialog(null,comboBox,"Choose Months",JOptionPane.OK_OPTION);
        if(comboBox.getSelectedItem().equals("1")) {
            monthsToPlay = 1;
        } else if(comboBox.getSelectedItem().equals("2")) {
            monthsToPlay = 2;
        } else {
            monthsToPlay = 3;
        }

    }

    /**
     * Method checks to see if game is over
     * @return true if it is; false otherwise
     */
    private boolean isGameFinished() {
        return model.p1.isFinished() && model.p2.isFinished();
    }

    /**
     * Method calculates who is the winner of the game
     * @return Player who won; a new instance of Player if tie
     */
    public Player getWinner() {
        if(model.p1.getScore() > model.p2.getScore()) {
            return model.p1;
        } else if (model.p1.getScore() < model.p2.getScore()) {
            return model.p2;
        } else {
            return new Player("Tie");
        }
    }

    private void decideFirstTurn() {
        Random rand = new Random();
        int turn = rand.nextInt(2);
        if(turn == 0) {
            currPlayer = model.p1;
            currOpponent = model.p2;
        } else {
            currPlayer = model.p2;
            currOpponent =  model.p1;
        }
    }

    /**
     * Method is responsible for deciding whose turn it is
     */
    private void decideNextTurn() {
        if(model.p1 == currPlayer && !model.p2.isFinished()) { //if this was p1's turn and p2 is still playing
            Dice tmpDice = model.p2.getDice();
            tmpDice.setWasRolled(false);
            model.p2.setDice(tmpDice);

            model.p2.setCompletedAction(false);
            currPlayer = model.p2;          //next turn is p2's

            Dice oppDice = model.p1.getDice();
            oppDice.setWasRolled(true);
            currOpponent = model.p1;
        } else if(model.p2 == currPlayer && !model.p1.isFinished()) { //if this was p2's turn and p1 is still playing
            Dice tmpDice = model.p1.getDice();
            tmpDice.setWasRolled(false);
            model.p1.setDice(tmpDice);
            model.p1.setCompletedAction(false);
            currPlayer = model.p1;         //next turn is p1's

            Dice oppDice = model.p2.getDice();
            oppDice.setWasRolled(true);
            currOpponent = model.p2;
        } else if(model.p1 == currPlayer && model.p2.isFinished() && !model.p1.isFinished()) { //if player2 is finished and player1 hasn't
            model.p1.getDice().setWasRolled(false);
            model.p1.setCompletedAction(false);
        } else if(model.p2 == currPlayer && model.p1.isFinished() && !model.p2.isFinished()) { //if player1 is finished and player2 hasn't
            model.p2.getDice().setWasRolled(false);
            model.p2.setCompletedAction(false);
        } else if(model.p1 == currPlayer && model.p1.isFinished()) {    //if this was player1's last turn
            model.p2.getDice().setWasRolled(false);
            model.p2.setCompletedAction(false);
            currPlayer = model.p2;
        } else if(model.p2 == currPlayer && model.p2.isFinished()) {    //if this was player2's last turn
            model.p1.getDice().setWasRolled(false);
            model.p1.setCompletedAction(false);
            currPlayer = model.p1;
        }
        if(isGameFinished()) {    //if both players are finished
            Player winner = getWinner();
            String message;
            if(winner.getName() != "Tie") {
                 message = "And the winner is " + winner.getName();
            } else {
                 message = "Game finished in a tie";
            }

            JOptionPane.showMessageDialog(null,message,"Winner",JOptionPane.OK_OPTION);
            model.p1.getDice().setWasRolled(true);
            model.p2.getDice().setWasRolled(true);

            model.p1.setCompletedAction(true);
            model.p2.setCompletedAction(true);

        }
        view.updateInfoPanelPlayer(currPlayer);
        if(isGameFinished()) {
            view.updateInfoPanelMessage("Game is finished");
        } else {
            view.updateInfoPanelMessage(currPlayer.getName() + " Roll Dice");
        }

    }

    /**
     * Method is responsible for handling events when a player is in a Sunday tile.
     */
    private void sundayMatch(Player p) {

        String URL = "resources/images/Barcelona_Real.jpg";
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(300,170,Image.SCALE_SMOOTH));

        Object[] options = {"1:   2.00","Χ:   2.00","2:   2.00","Το εχω κοψει"};

        int choice = JOptionPane.showOptionDialog(null,"Στοιχηματισε 500 ευρω για το ντερμπι Barcelona-Real","Stoiximan",JOptionPane.OK_OPTION,0,imgIcon,options,options[0]);

        if(choice != 3) {
            int roll = p.getDice().roll();
            if(choice == 0 && roll < 3 || choice == 1 && (roll > 2 && roll < 5) || choice == 2 && roll > 4) {
                p.updateBalance(500);
                view.updateInfoPanelMessage(p.getName() + " rolled " + roll + " and won 500 Euros");
                Object[] option = {"Κερδισες 500 Ευρω"};
                JOptionPane.showOptionDialog(null,"Δασκαλοοοοοος","Ποδοσφαιρικος αγωνας Κυριακης",JOptionPane.OK_OPTION,0,imgIcon,option,option[0]);

            } else {
                p.updateBalance(-500);
                if(p.getBalance() < 0) {  //if player doesn't have enough money to pay interest
                    p.updateBalance(1000);
                    p.updateLoanAmount(1000);
                }
                view.updateInfoPanelMessage(p.getName() + " rolled " + roll + " and lost 500 Euros");
                Object[] option = {"Εχασες 500 Ευρω"};
                JOptionPane.showOptionDialog(null,"Κουβας και παλι","Ποδοσφαιρικος αγωνας Κυριακης",JOptionPane.OK_OPTION,0,imgIcon,option,option[0]);
            }
        }
    }

    /**
     * Method is responsible for handling events when a player is in a Thursday tile.
     */
    private void thursdayCrypto(Player p) {

        String URL = "resources/images/crypto.jpg";
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH));

        Object[] options = {"Πονταρε στο κρυπτονομισμα","Παραβλεψε το πονταρισμα"};

        int choice = JOptionPane.showOptionDialog(null,"Πονταρισμα σε κρυπτονομισματα","Crypto Thursday",JOptionPane.OK_OPTION,0,imgIcon,options,options[0]);
        if(choice == 0) {
            int roll = p.getDice().roll();
            if(roll < 3) {
                p.updateBalance(-300);
                view.updateInfoPanelMessage(p.getName() + " rolled " + roll + " and lost 300 Euros");

                Object[] option = {"Εχασες 300 Ευρω"};
                int tmp = JOptionPane.showOptionDialog(null,"Το κρυπτονομισμα ητανε scam!","Crypto Thursday",JOptionPane.OK_OPTION,0,imgIcon,option,option[0]);
            } else if(roll > 4) {
                p.updateBalance(300);
                view.updateInfoPanelMessage(p.getName() + " rolled " + roll + " and won 300 Euros");
                Object[] option = {"Κερδισες 300 Ευρω"};
                int tmp = JOptionPane.showOptionDialog(null,"Το κρυπτονομισμα εκτοξευτηκε!","Crypto Thursday",JOptionPane.OK_OPTION,0,imgIcon,option,option[0]);
            } else {
                Object[] option = {"Παρε τα λεφτα σου πισω"};
                int tmp = JOptionPane.showOptionDialog(null,"Το κρυπτονομισμα εμεινε σταθερο!","Crypto Thursday",JOptionPane.OK_OPTION,0,imgIcon,option,option[0]);
            }
            view.update();
        }

    }

    private void takeLoanDialog(Player p) {
        String[] options = {"1000","2000","3000","4000","5000","6000","7000","8000","9000"};

        JComboBox comboBox = new JComboBox(options);
        JOptionPane.showMessageDialog(null, comboBox, "Choose Loan Amount", JOptionPane.OK_OPTION);

        int amount = Integer.parseInt((String) comboBox.getSelectedItem());
        p.updateBalance(amount);
        p.updateLoanAmount(amount);
    }

    private void dealCardsDialog(Player p) {
        ArrayList<DealCard> dc = p.getPlayerCards();

        //setup options
        int optionsCount = dc.size();
        if(optionsCount > 0) {
            String[] options = new String[optionsCount];
            for (int i = 0; i < optionsCount; i++) {
                options[i] = dc.get(i).getMessage() + " Value:" + dc.get(i).getValue();
            }
            JComboBox comboBox = new JComboBox(options);
            JOptionPane.showMessageDialog(null, comboBox, "Player's Deal Cards", JOptionPane.OK_OPTION);

            String choice = (String) comboBox.getSelectedItem();
            for (DealCard dealCard : dc) {
                String tmpChoice = dealCard.getMessage() + " Value:" + dealCard.getValue();
                if (tmpChoice.equals(choice)) {      //if player's choice matches this card

                    String URL = "resources/images/" + dealCard.getIcon();
                    ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH));

                    JOptionPane.showMessageDialog(null,dealCard.getMessage() + "\nΤιμη Αγορας: " + dealCard.getCost() + "\nΤιμη Πωλησης: " + dealCard.getValue(),"Καρτα συμφωνιας",JOptionPane.OK_OPTION,imgIcon);
                    break;
                }
            }
        }
    }

    private void loanPaymentDialog(Player p) {
        int loanAmount = p.getLoanAmount();
        int balance = p.getBalance();
        view.update();

        int optionsCount;

        if(balance >= loanAmount) {     //if player has enough money to fully pay loan
            optionsCount = (loanAmount / 1000) + 1;
        } else {        //if he doesnt
            optionsCount = (balance / 1000) + 1;
        }

        String options[] = new String[optionsCount];
        for(int i = 0; i < optionsCount; i++) {
            options[i] = Integer.toString(i * 1000);    //player's options that will be displayed
        }
        if(options.length != 1) {
            JComboBox comboBox = new JComboBox(options);
            JOptionPane.showMessageDialog(null, comboBox, "Loan Payment", JOptionPane.OK_OPTION);

            int payment = Integer.parseInt((String) comboBox.getSelectedItem());
            p.updateBalance(-payment);
            p.updateLoanAmount(-payment);
        }

    }



    private void sweepstakesTileAction(Player p) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    int roll = p.getDice().roll();
                    p.updateBalance(1000*roll);
                    view.update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void lotteryTileAction(Player p1, Player p2) {
        String[] options = {"1","2","3","4","5","6"};

        JComboBox comboBox = new JComboBox(options);
        JOptionPane.showMessageDialog(null,comboBox,p1.getName() + " choose a number",JOptionPane.OK_OPTION);

        int choice1 = Integer.parseInt((String) comboBox.getSelectedItem()); //player1's selected number

        JOptionPane.showMessageDialog(null,comboBox,p2.getName() + " choose a number",JOptionPane.OK_OPTION);

        int choice2 = Integer.parseInt((String) comboBox.getSelectedItem()); //player2's selected number

        int roll = p1.getDice().roll(); //first roll of dice
        if(roll == choice1) {
            p1.updateBalance(1000);
            String message = p1.getName() + " won 1000 Euros";
            view.updateInfoPanelMessage(message);
        } else if(roll == choice2) {
            p2.updateBalance(1000);
            String message = p2.getName() + " won 1000 Euros";
            view.updateInfoPanelMessage(message);
        }

        while(roll != choice1 && roll != choice2) {
            roll = p1.getDice().roll();
            if(roll == choice1) {
                p1.updateBalance(1000);
                String message = p1.getName() + " won 1000 Euros";
                view.updateInfoPanelMessage(message);
            } else if(roll == choice2) {
                p2.updateBalance(1000);
                String message = p2.getName() + " won 1000 Euros";
                view.updateInfoPanelMessage(message);
            }
        }
        view.update();
    }

    private void radioTileAction(Player p1, Player p2) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    int rollp1 = p1.getDice().roll(); //roll player1's dice
                    view.update();

                    int rollp2 = p2.getDice().roll(); //roll player2's dice
                    view.update();

                    while(rollp1 == rollp2) {       //repeat until one is bigger
                        rollp1 = p1.getDice().roll();
                        view.update();

                        rollp2 = p2.getDice().roll();
                        view.update();
                    }

                    Thread.sleep(1000);
                    if(rollp1 > rollp2) {
                        p1.updateBalance(1000);
                        view.updateInfoPanelMessage(p1.getName() + " Won 1000 Euros");
                        view.update();
                    } else {
                        p2.updateBalance(1000);
                        view.updateInfoPanelMessage(p2.getName() + " Won 1000 Euros");
                        view.update();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void buyerTileAction(Player p) {
        ArrayList<DealCard> dc = p.getPlayerCards();

        //setup options
        int optionsCount = dc.size();
        if(optionsCount > 0) {
            String[] options = new String[optionsCount];
            for(int i = 0; i < optionsCount; i++) {
                options[i] = dc.get(i).getMessage() + " Value:" + dc.get(i).getValue();
            }
            JComboBox comboBox = new JComboBox(options);
            JOptionPane.showMessageDialog(null,comboBox,"Sell a Deal Card",JOptionPane.OK_OPTION);

            //traverse player's hand
            String choice = (String) comboBox.getSelectedItem();
            for(int i = 0; i < optionsCount; i++) {
                String tmpChoice = dc.get(i).getMessage() + " Value:" + dc.get(i).getValue();
                if(tmpChoice.equals(choice)) {      //if player's choice matches this card
                    p.updateBalance(dc.get(i).getValue());
                    p.removePlayerCard(i);
                    break;
                }
            }
        }
    }

    private void casinoTileAction(Player p, int roll) {
        if(roll % 2 == 1) {
            p.updateBalance(-500);
            model.jackpot.updateBalance(500);
            view.updateInfoPanelMessage(p.getName() + " rolled odd and payed 500 Euros to Jackpot");
            while(p.getBalance() < 0) {      //if player doesn't have enough money
                p.updateLoanAmount(1000);
                p.updateBalance(1000);
            }
        } else {
            p.updateBalance(500);
            view.updateInfoPanelMessage(p.getName() + " rolled even and won 500 Euros");
        }
    }

    private void yardTileAction(Player p) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    int roll = p.getDice().roll();
                    p.updateBalance(-100*roll);
                    if(p.getBalance() < 0) {
                        p.updateBalance(1000);
                        p.updateLoanAmount(1000);
                    }
                    DealCard dealCard = model.dealDeck.drawCard();
                    p.addPlayerDealCard(dealCard);

                    String URL = "resources/images/" + dealCard.getIcon();
                    ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH));

                    dealCard.setCost(100*roll);

                    JOptionPane.showMessageDialog(null,dealCard.getMessage() + "\nΤιμη Αγορας: " + dealCard.getCost() + "\nΤιμη Πωλησης: " + dealCard.getValue(),"Καρτα συμφωνιας",JOptionPane.OK_OPTION,imgIcon);

                    view.update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void paydayTileAction(Player p) {
        p.updateBalance(3500); //update balance with salary

        p.updateBalance(-p.getBillsAmount()); //pay bills
        p.setBillsAmount(0);

        p.updateBalance(-p.getBillsAmount() * (10/100)); //pay loan interest

        while(p.getBalance() < 0) {  //if player doesn't have enough money to pay interest
            p.updateBalance(1000);
            p.updateLoanAmount(1000);
        }

        loanPaymentDialog(p);

        p.updateCurrentMonth();
        if(p.getCurrentMonth() <= monthsToPlay) { //if player still has a month to play
            p.setPosition(0);
        } else {

            p.setFinished(true);

            ArrayList<DealCard> dc = p.getPlayerCards();
            for (DealCard dealCard : dc) {                      // move every player's card to played stack
                model.dealDeck.moveToPlayed(dealCard);
            }
            p.emptyHand();                           //empty player's hand

        }
    }

    private void playerTurnAction(Player p1, Player p2) {
        int roll = p1.getDice().roll();
        p1.getDice().setWasRolled(true);  // player has rolled his dice

        if(roll == 6) { //jackpot action if player rolled a 6
            model.jackpot.win(p1);
        }
        p1.updatePosition(roll);
        view.update();

        //Thursday Crypto action
        if(p1.getPosition() == 4 || p1.getPosition() == 11 || p1.getPosition() == 18 || p1.getPosition() == 25) {
            thursdayCrypto(p1);
            view.update();
        }

        //Sunday Match action
        if(p1.getPosition() == 7 || p1.getPosition() == 14 || p1.getPosition() == 21 || p1.getPosition() == 28) {
            sundayMatch(p1);
            view.update();
        }


        if(model.board.getTile(p1.getPosition()) instanceof LotteryTile) {
            lotteryTileAction(p1,p2);
            view.update();
            p1.setCompletedAction(true);
            decideNextTurn();
        } else
        if(model.board.getTile(p1.getPosition()) instanceof RadioTile) {
            radioTileAction(p1,p2);
            view.update();
            p1.setCompletedAction(true);
            decideNextTurn();
        } else
        if(model.board.getTile(p1.getPosition()) instanceof BuyerTile) {
            buyerTileAction(p1);
            view.updateInfoPanelMessage("Player must sell a Deal Card");
            view.update();
            p1.setCompletedAction(true);
            decideNextTurn();
        } else
        if(model.board.getTile(p1.getPosition()) instanceof PaydayTile) {
            paydayTileAction(p1);
            view.update();
            p1.setCompletedAction(true);
            decideNextTurn();
        } else
        if(model.board.getTile(p1.getPosition()) instanceof MessageTile) {
            int count = ((MessageTile) model.getBoard().getTile(currPlayer.getPosition())).getDrawCount();
            if(count == 1) {
                view.updateInfoPanelMessage("Draw 1 Message Card");
            } else {
                view.updateInfoPanelMessage("Draw 2 Message Cards");
            }
        } else
        if(model.board.getTile(p1.getPosition()) instanceof DealTile) {
            view.updateInfoPanelMessage("Draw 1 Deal Card");
        } else
        if(model.board.getTile(p1.getPosition()) instanceof SweepstakesTile) {
            sweepstakesTileAction(p1);
            view.updateInfoPanelMessage(p1.getName() +  " won " + 1000*roll + " Euros");
            view.update();
            p1.setCompletedAction(true);
            decideNextTurn();
        } else
        if(model.board.getTile(p1.getPosition()) instanceof YardTile) {
            yardTileAction(p1);
            view.updateInfoPanelMessage(p1.getName() + " payed " + 100*roll + " Euros to purchase a Deal Card");
            view.update();
            p1.setCompletedAction(true);
            decideNextTurn();
        } else
        if(model.board.getTile(p1.getPosition()) instanceof CasinoTile) {
            casinoTileAction(p1,roll);
            view.update();
            p1.setCompletedAction(true);
            decideNextTurn();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //player1's dice button
        if(e.getSource() == view.getP1().getRollDiceButton() && currPlayer == model.p1 && !currPlayer.getDice().WasRolled() &&!currPlayer.hasCompletedAction()) {

            playerTurnAction(model.p1,model.p2);

        //player1's loan button
        } else if(e.getSource() == view.getP1().getLoanButton() && currPlayer == model.p1) { //if player1 requests a loan and it's his turn

            takeLoanDialog(model.p1);
            view.update();

            //player1's showdealCards button
        } else if(e.getSource() == view.getP1().getDealCardsButton() && currPlayer == model.p1) {

            dealCardsDialog(model.p1);

            //player2's dice button
        } else if(e.getSource() == view.getP2().getRollDiceButton() && currPlayer == model.p2 && !currPlayer.getDice().WasRolled() &&!currPlayer.hasCompletedAction()) {

            playerTurnAction(model.p2,model.p1);

            //player2's loan button
        } else if(e.getSource() == view.getP2().getLoanButton() && currPlayer == model.p2) { //if player2 requests a loan and it's his turn

            takeLoanDialog(model.p2);
            view.update();

            //player2's showdealCards button
        }else if(e.getSource() == view.getP2().getDealCardsButton() && currPlayer == model.p2) {

            dealCardsDialog(model.p2);

            //MessageCard Button action
        }else if(e.getSource() == view.getMessageCardButton() &&       // messageCard button was pressed and
                    (model.getBoard().getTile(currPlayer.getPosition())) instanceof MessageTile &&  //currentPlayer is in a MessageTile and
                    currPlayer.getDice().WasRolled() &&     // currentPlayer has rolled the dice ( to prevent player from skipping rolling his dice/only drawing Cards and not moving) and
                    !currPlayer.hasCompletedAction()) {     // currentPlayer hasn't drawn any cards

            // 1 or 2 iterations; depending on the card's draw count
            int count = ((MessageTile) model.getBoard().getTile(currPlayer.getPosition())).getDrawCount();
            for(int i = 0; i < count; i++) {
                if(model.messageDeck.isEmpty()) {
                    model.messageDeck.reshuffleDeck();
                }
                MessageCard mc = model.messageDeck.drawCard();

                mc.cardAction(currPlayer,model);

                String URL = "resources/images/" + mc.getIcon();
                ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH));

                Object[] options = {mc.getChoice()};

                int choice = JOptionPane.showOptionDialog(null,mc.getMessage(),mc.getType(),JOptionPane.OK_OPTION,0,imgIcon,options,options[0]);
                if(choice == 0) {
                    view.update();
                }

                model.messageDeck.moveToPlayed(mc);
            }

            if(currPlayer == model.p1) {
                model.p1.setCompletedAction(true);
            } else {
                model.p2.setCompletedAction(true);
            }
            decideNextTurn();

        //DealCard Button action
        } else if(e.getSource() == view.getDealCardButton() && model.getBoard().getTile(currPlayer.getPosition()) instanceof DealTile && currPlayer.getDice().WasRolled()) {
            if(model.dealDeck.isEmpty()) {
                model.dealDeck.reshuffleDeck();
                System.out.println("reshuffle dealDeck");
            }
            DealCard dc = model.dealDeck.drawCard();
            String URL = "resources/images/" + dc.getIcon();
            ImageIcon imgIcon = new ImageIcon(new ImageIcon(URL).getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH));

            Object[] options = {"Αγόρασε","Αγνόησε τη Συμφωνία"};

            int choice = JOptionPane.showOptionDialog(null,dc.getMessage() + "\nΤιμη Αγοράς: " + dc.getCost() + " Ευρω\nΤιμη Πωλησης: " + dc.getValue() + " Ευρω\n","Συμφωνια",JOptionPane.OK_OPTION,0,imgIcon,options,options[0]);

            dc.cardAction(currPlayer,model.dealDeck, choice == 0);
            view.update();

            if(currPlayer == model.p1) {
                model.p1.setCompletedAction(true);
            } else {
                model.p2.setCompletedAction(true);
            }
            decideNextTurn();
        }
    }
}
