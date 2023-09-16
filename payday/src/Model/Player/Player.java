package Model.Player;

import Model.Cards.Card;
import Model.Cards.DealCards.DealCard;
import Model.Dice.Dice;
import Model.Pawn.Pawn;

import java.util.ArrayList;

/**
 * Class is used to implement each of the 2 players with all their required attributes
 */

public class
Player {

    private static final int DEFAULT_BALANCE = 3500;

    private String name;
    private int balance;
    private int loanAmount;
    private int billsAmount;
    private int position;
    private int currentMonth;
    private int score;
    private ArrayList<DealCard> playerCards;
    private Pawn pawn;
    private Dice dice;
    private boolean isFinished;
    private boolean isTurn;
    private boolean completedAction;


    /**
     * Constructor that initializes player to default state
     */
    public Player(String name) {
        this.name = name;
        balance = DEFAULT_BALANCE;
        loanAmount = 0;
        billsAmount = 0;
        position = 0;
        currentMonth = 1;
        playerCards = new ArrayList<DealCard>();
        pawn = new Pawn();
        dice = new Dice();
        isFinished = false;
        isTurn = false;
        completedAction = false;
    }

    public Dice getDice() {
        return dice;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Increase value of balance
     * @param num to be added to balance
     */
    public void updateBalance(int num) {
        this.balance += num;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * Increase value of loan
     * @param num to be added to loan
     */
    public void updateLoanAmount(int num) {
        this.loanAmount += num;
    }

    public int getBillsAmount() {
        return billsAmount;
    }


    public void setBillsAmount(int billsAmount) {
        this.billsAmount = billsAmount;
    }

    /**
     * Increase value of bills
     * @param num to be added to bills
     */
    public void updateBillsAmount(int num) {
        this.billsAmount += num;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Increase position of player
     * @param num number of positions to move
     */
    public void updatePosition(int num) {
        this.position += num;

        if(position > 31) {
            position = 31;
        }
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    /**
     * Increment currentMonth by 1
     */
    public void updateCurrentMonth() {
        this.currentMonth++;
    }

    public ArrayList<DealCard> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<DealCard> playerCards) {
        this.playerCards = playerCards;
    }

    /**
     * Add a new card into players Deal Cards
     * @param c card to be added
     */
    public void addPlayerDealCard(DealCard c) {
        this.playerCards.add(c);
    }

    /**
     * Remove card at input index from the player's Deal Cards
     * @param index position of card that will be removed
     */
    public void removePlayerCard(int index) {
        if(index >= this.playerCards.size() || index < 0)
            throw new IllegalArgumentException("Index must be in range [0,playerCards.size()]!");

        playerCards.remove(index);
    }

    /**
     * Remove input Card from the player's Deal Cards
     * @param c card that will be removed
     */
    public void removePlayerCard(Card c) {
        if(!playerCards.contains(c))
            throw new IllegalArgumentException("Card does not exist in player's Deal Cards!");

        playerCards.remove(c);
    }

    /**
     * Remove every Card in player's possession
     */
    public void emptyHand() {
        playerCards.clear();
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public int getScore() {
        return balance - loanAmount - billsAmount;
    }

    public boolean hasCompletedAction() {
        return completedAction;
    }

    public void setCompletedAction(boolean completedAction) {
        this.completedAction = completedAction;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }
}
