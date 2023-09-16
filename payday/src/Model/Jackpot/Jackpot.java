package Model.Jackpot;

import Model.Player.Player;

/**
 * Class is responsible for modeling the Jackpot
 */
public class Jackpot {

    private int balance;

    public Jackpot() {
        this.balance = 0;
    }

    public void updateBalance(int num) {
        this.balance += num;
    }

    public int getBalance() {
        return balance;
    }

    public void win(Player p) {
        p.updateBalance(this.balance);
        this.balance = 0;
    }
}
