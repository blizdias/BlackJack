package org.academiadecodigo.bootcamp.blackjack;

import org.academiadecodigo.bootcamp.Colors;
import org.academiadecodigo.bootcamp.cards.Card;
import org.academiadecodigo.bootcamp.cards.DeckFactory;
import org.academiadecodigo.bootcamp.server.GameServer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Dealer {
    private List<Card> deckOfCards = null;
    private GameServer gameServer = null;
    private Random randomizer = null;
    private Hand dealerHand = null;
    private boolean gameStillGoing;
    private List<Player> listOfPlayers;
    private Betting bettingLogic;


    public Dealer(List<Card> deckOfCards, GameServer gameServer, List<Player> listOfPlayers) {
        this.deckOfCards = deckOfCards;
        this.gameServer = gameServer;
        this.dealerHand = new Hand();
        this.randomizer = new Random();
        this.gameStillGoing = true;
        this.listOfPlayers = Collections.synchronizedList(listOfPlayers);
        this.bettingLogic = new Betting();


    }


    public void runGame() {
        try {

            /**
             * Creating a new Deck
             */

            DeckFactory deckFactory = new DeckFactory();
            deckOfCards = deckFactory.getNewDeck();
            System.out.println(deckOfCards.size() + " initial deck size <----------");
            LinkedList<Integer> removeIndex = new LinkedList<>();

            while (true) {
                Card tempDealerCard = null;

                gameServer.dealerBroadcastAll("Please wait while other players place their bets...");
                for (Player player : listOfPlayers) {


                    if(player.getChips() < 10) {
                        gameServer.dealerBroadcastAll(player.getPlayerName() + " has run out of chips!");
                        player.gameOver();
                        removeIndex.add(listOfPlayers.indexOf(player));
                        continue;
                    }

                    bettingLogic.playerBet(player);
                }
                for (int i : removeIndex) {
                    listOfPlayers.remove(i);
                }
                removeIndex.clear();
                if (listOfPlayers.isEmpty()) {
                    System.exit(1);
                }

                /**
                 * Dealing 2 cards to all players
                 */
                gameServer.dealCardToAll(deckOfCards);


                /**
                 * Dealer deals to himself and doesn't reveal the card
                 */

                tempDealerCard = deckOfCards.remove(randomizer.nextInt(deckOfCards.size()));
                dealerHand.addCard(tempDealerCard);
                gameServer.dealerBroadcastAll(Colors.YELLOW_BOLD +"\n" + "=========== Dealer's turn ===========\n" +
                        "The Dealer has drawn a card -- and placed it in the table, facing down! \n" + Colors.RESET);

                /**
                 * Dealer deals to himself
                 */
                tempDealerCard = deckOfCards.remove(randomizer.nextInt(deckOfCards.size()));
                dealerHand.addCard(tempDealerCard);
                gameServer.dealerBroadcastAll(Colors.YELLOW_BOLD +"The Dealer has drawn a\n" + tempDealerCard.cardName + Colors.RESET);




                /**
                 * Afterwards we ask if they wish to hit or pass
                 */


                gameServer.hitOrPassAll(deckOfCards);
                TimeUnit.MILLISECONDS.sleep(3000);
                gameServer.dealerBroadcastAll("--> The Dealer is revealing their card.<--"); // I have this one and that one cards e tal


                /**
                 * Checking the value of dealer's hand
                 */

                int dealerTotal = getDealerValue();


                /**
                 * If <= 16, he draws cards until the condition is no longer true
                 */
                while (dealerTotal <= 16) {
                    tempDealerCard = deckOfCards.remove(randomizer.nextInt(deckOfCards.size()));
                    dealerHand.addCard(tempDealerCard);
                    gameServer.dealerBroadcastAll(Colors.YELLOW_BOLD + "\n" + "=========== Dealer's turn ===========\n" +
                            "The Dealer has drawn a\n" +
                            tempDealerCard.cardName + "\n" +
                            "================\n" + Colors.RESET);
                    dealerTotal = getDealerValue(); // updating the value in total after drawing 1 card
                    gameServer.dealerBroadcastAll(Colors.YELLOW_BOLD +"The Dealer's hand is: " + dealerTotal + Colors.RESET);
                }

                /**
                 * If dealer value > 21, he busts and then we see if the player has less than 21, if he does the player wins.
                 * Otherwise the player loses.
                 */

                if (dealerTotal > 21) {
                    gameServer.dealerBroadcastAll(Colors.YELLOW_BOLD +"======= The Dealer busts with " + dealerTotal + ". =======\n"+ Colors.RESET);

                    for(Player player : listOfPlayers) {
                        List<Card> playersCards = player.getListOfCardsFromHand();
                        if (playersCards.size() == 2 && gameServer.getPlayerTotal(player) == 21) {
                                bettingLogic.dealerPaysBackJack(player);
                                player.broadcast("You have drawn a BlackJack!" + "\n" + "Your current balance is now " + player.getChips() + " chips.");
                                player.resetHand();
                                continue;

                        }
                        if(gameServer.getPlayerTotal(player) <= 21) {
                            bettingLogic.dealerPayBet(player);
                            player.resetHand();
                            player.broadcast("You have won! \n Your current balance is " + player.getChips() + " chips.");
                            continue;
                        }

                        player.broadcast("You have bust!\nYour current balance is " + player.getChips() + " chips.");
                        player.resetHand();
                    }
                    checkDeckSize();
                    dealerHand.resetHand();
                    continue;
                }

                /**
                 * If the dealer didn't bust, we see if the player busted
                 * if not then we see if the player's value is higher than the dealer's
                 */

                for (Player player : listOfPlayers) {
                    int playerTotal = gameServer.getPlayerTotal(player);
                    List<Card> playersCards = player.getListOfCardsFromHand();
                    if(playerTotal > 21 ) {

                        player.broadcast("You have bust!\nYour current balance is " + player.getChips() + " chips.");
                        player.resetHand();
                        continue;
                    }
                    if (playersCards.size() == 2 && gameServer.getPlayerTotal(player) == 21) {

                            bettingLogic.dealerPaysBackJack(player);
                            player.broadcast("You have drawn a BlackJack!" + "\n" + "Your current balance is now " + player.getChips() + " chips.");
                            player.resetHand();
                            continue;

                    }

                    if (playerTotal > dealerTotal) {
                        //playerWins
                        bettingLogic.dealerPayBet(player);
                        player.resetHand();
                        player.broadcast("You have won! \nYour current balance is " + player.getChips() + " chips.");
                        continue;
                    } else if (playerTotal == dealerTotal) {
                        bettingLogic.dealerDraw(player);
                        player.resetHand();
                        player.broadcast("It's a tie." + "\n" + "Your current balance is " + player.getChips() + " chips.");
                        continue;
                    }

                    player.broadcast("You have lost." + "\n" + "Your current balance is " + player.getChips() + " chips.");
                    player.resetHand();

                }
                checkDeckSize();
                dealerHand.resetHand();















                /* for (Player player : listOfPlayers) {


                    if (getCardValue(player) > 21) {
                        gameServer.dealerBroadcastAll(player.getPlayerName() + ": You BUST");
                        gameServer.dealerBroadcastAll("======== RESET!!! ========");


                        for (Player person : listOfPlayers) {
                            person.resetHand();
                        }
                        dealerHand.resetHand();


                    }

                } */


            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkDeckSize() {
        System.out.println(deckOfCards.size() + " size of deck <------");
        if(deckOfCards.size() < 104) {
            DeckFactory deckFactory = new DeckFactory();
            deckOfCards = deckFactory.getNewDeck();
        }
    }

    public int getDealerValue() throws IOException {

        List<Card> listDealerHand = dealerHand.getCurrentCards();
        gameServer.dealerBroadcastAll(Colors.YELLOW_BOLD + "The Dealer's hand is currently:\n" + Colors.RESET);

        listDealerHand.stream().map(card -> Colors.YELLOW + card.cardName + Colors.RESET)
                .forEach(card -> {
                    try { // eww
                        gameServer.dealerBroadcastAll(card);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


        List<Integer> dealerHandValue = listDealerHand.stream().map(card -> card.cardValue).collect(Collectors.toList());
        int dealerValue = dealerHandValue.stream().reduce(0, Integer::sum);

        for (int i = 0; i < listDealerHand.size(); i++)  {
            if (listDealerHand.get(i).cardValue == 11 && dealerValue > 21) {
                dealerValue -= 10;
            }
        }
        return dealerValue;
    }

    public int getCardValue(Player player) {
        int num = player.getListOfCardsFromHand().size();
        int acc = 0;
        for (int i = 0; i < num; i++) {
            acc += player.getListOfCardsFromHand().get(i).cardValue;
        }
        return acc;
    }
}
