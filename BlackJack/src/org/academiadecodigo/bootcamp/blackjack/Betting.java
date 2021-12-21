package org.academiadecodigo.bootcamp.blackjack;


import java.io.IOException;


public class Betting {


    public static final int MINIMUM_BET = 10;
    public static final int PAY_TIMES_WIN = 2;
    public static final double PAY_TIMES_BLACKJACK = 2.50;


    /**
     * checks if player has minimum required chips to play.
     * If not player gameOver.
     */

    public void checkPlayerChips (Player player) {
        if (player.getChips() < 10) {
            player.gameOver();
        }
    }

    /**
     * check number of player chips and check if he can bet the minimum
     * set player current chips minus bet
     */

    public void playerBet(Player player) {
        player.setBetValue(0);
        checkPlayerChips(player);
        player.setBetValue(player.betValidationScanner());
        player.setChips(player.getChips() - player.getBetValue());
    }

    /**
     * Sets the player chips to the amount as won!
     */

    public void dealerPayBet(Player player) {
            player.setChips(player.getChips() + player.getBetValue()*PAY_TIMES_WIN);
        }


        public void dealerPaysBackJack(Player player) {
            player.setChips(player.getChips() + (int) (player.getBetValue()*PAY_TIMES_BLACKJACK));
        }

    public void dealerDraw (Player player) {
        player.setChips(player.getChips() + player.getBetValue());
    }


}
