package org.academiadecodigo.bootcamp.server;

import org.academiadecodigo.bootcamp.blackjack.Dealer;
import org.academiadecodigo.bootcamp.blackjack.Player;
import org.academiadecodigo.bootcamp.cards.Card;
import org.academiadecodigo.bootcamp.cards.DeckFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class GameServer {

    public static final int DEFAULT_PORT = 9005;
    private volatile List<Player> playerArrayList = null;
    Random randomizer = new Random();

    public GameServer() {
        playerArrayList = Collections.synchronizedList(new LinkedList<>());
    }


    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        int playerCounter = 0;
        List<Thread> threadList = new ArrayList<>();


        /**
         * We create a new socket and give it a Port as argument. The Main thread's job will be to continuously listen
         * on that port and when a client connects create a new thread and go back to listening.
         */
        try {
            int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
            ServerSocket bindSocket = new ServerSocket(port);

            while (playerCounter < 4) {

                /**
                 * Blocking method, will stop until a client connects
                 */
                Socket clientSocket = bindSocket.accept();


                /**
                 * The new thread, ServerWorker is the Runnable.
                 */
                Player newPlayer = new Player(clientSocket, gameServer);
                Thread thread = new Thread(newPlayer);
                threadList.add(thread);


                /***
                 * Adds the Runnable to the ArrayList
                 */
                gameServer.addSocketArrayList(newPlayer);
                playerCounter++;

            }

            /**
             * Start of the game
             */

            for(Thread singleThread : threadList){
                singleThread.start();

            }

            for(Thread singleThread : threadList){
                try {
                    singleThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for(Player player : gameServer.getPlayerList()){
                gameServer.dealerBroadcastAll(player.getPlayerName() + " has joined the table!");
            }



            try {

                gameServer.checkIfPlayersSetName();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            DeckFactory deck = new DeckFactory();
            Dealer dealer = new Dealer(deck.getNewDeck(), gameServer, gameServer.getPlayerList());
            dealer.runGame();





















        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addSocketArrayList(Player serverWorker) {
        playerArrayList.add(serverWorker);

    }

    public List<Player> getPlayerList(){
        return playerArrayList;
    }


    public void dealerBroadcastAll(String message) throws IOException {
        synchronized (playerArrayList) {
            for (Player player : playerArrayList) {
                player.broadcast(message);
            }
            System.out.println(message);
        }

    }
    public synchronized void plsWait() throws InterruptedException {
        wait();
    }

    public synchronized void plsWakeUp() throws InterruptedException {
        notifyAll();
    }

    public void broadcastCardsInHand(Player player) throws IOException {
        /**
         * Declarative for each, when it's the person's turn it broadcasts the cards they currently have in hand
         */
        List<Card> playerCards = player.getListOfCardsFromHand();
        dealerBroadcastAll(player.getPlayerName() + " has the following cards:\n");
        playerCards.stream().map(card -> card.cardName)
                .forEach(card -> {
                    try { // eww
                        dealerBroadcastAll(card);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }


    public void hitOrPassAll(List<Card> deck) throws IOException {
        int choice;

        for (Player player : playerArrayList) {
            /**
             * For each person we will tell them which cards they currently have, tell them the value and then ask if
             * they wish to hit or not.
             */
            dealerBroadcastAll("====== " + player.getPlayerName() + "'s turn ========\n");
            broadcastCardsInHand(player);




            int acc;

            acc = getPlayerTotal(player);

            dealerBroadcastAll("--> Hand Value: " + acc + "\n");

            if(acc == 21){
                player.broadcast("=== BLACKJACK! ===");
            }

            dealerBroadcastAll("Waiting for " + player.getPlayerName() + "...");
            choice = player.hitOrPass(); //asking if person wishes to hit, returns boolean

            /**
             * The person can keep hitting for as long as they wish, as long as it doesn't go above 21.
             */
            while(choice == 1) {
                dealOneCard(deck, player);

                acc = getPlayerTotal(player);

                dealerBroadcastAll("--> Hand Value: " + acc + "\n");

                if(acc > 21){
                    player.broadcast("You bust!");
                    break;
                }
                dealerBroadcastAll("Waiting for " + player.getPlayerName() + "...");
                choice = player.hitOrPass(); //asks if the person wishes to hit or not, updates hasHit value (boolean)


            }
            if (choice == 3) {
                dealOneCard(deck,player);
                player.setChips(player.getChips()-player.getBetValue());
                player.setBetValue(player.getBetValue() * 2);
                acc = getPlayerTotal(player);
                dealerBroadcastAll("--> Hand Value: " + acc + "\n");
                if(acc > 21){
                    player.broadcast("You bust!");
                }

            }

            /*
            if(choice == 4){
            dealOneCard(deck,player.hand2)
            }

            if(hand2.size() != 0){
            hitorpass
            }

             */

        }
    }


    public void dealOneCard(List<Card> deck, Player player) throws IOException {
        Card card;
        card = deck.remove(randomizer.nextInt(deck.size()));
        player.addCardToHand(card);
        //dealerBroadcastAll((player.getPlayerName() + " receives card: " + card.cardName));
        broadcastCardsInHand(player);
    }


    public synchronized void checkIfPlayersSetName() throws InterruptedException {
        for(Player player : playerArrayList){
            while (player.getPlayerName() == null){
                wait();
            }
        }
    }

    public int getPlayerTotal(Player player) {
        int acc = 0;

        for (int i = 0; i < player.getListOfCardsFromHand().size(); i++) {

            acc += player.getListOfCardsFromHand().get(i).cardValue; // calculates total in hand
        }
        for (int i = 0; i < player.getListOfCardsFromHand().size(); i++) {
            if ((player.getListOfCardsFromHand().get(i).cardValue == 11) && acc > 21) {
                acc = acc -10;
            }
        }

        return acc;
    }










    /*
    //broadcast all with sender name
    public void broadcastAll(String message, String originalSender) throws IOException {
        synchronized (socketArrayList) {
            for (Player player : socketArrayList) {
                if (!(player.getPlayerName().equals(originalSender))) {
                    message = originalSender + " says: " + message;
                    player.broadcast(message);
                }
            }
            System.out.println(message);

        }

    }

     */
/*
    public void asciiCat(String originalSender) throws IOException {
        synchronized (socketArrayList) {
            for (Player worker : socketArrayList) {
                worker.write(ServerHelper.asciiCat(originalSender));
            }
            System.out.println(ServerHelper.asciiCat(originalSender));

        }

    }

    public void asciiGoose(String originalSender) throws IOException {
        synchronized (socketArrayList) {
            for (Player worker : socketArrayList) {
                worker.write(ServerHelper.asciiGoose(originalSender));
            }
            System.out.println(ServerHelper.asciiGoose(originalSender));

        }

    }

    public void asciiWhale(String originalSender) throws IOException {
        synchronized (socketArrayList) {
            for (Player worker : socketArrayList) {
                worker.write(ServerHelper.asciiWhale(originalSender));
            }
            System.out.println(ServerHelper.asciiWhale(originalSender));

        }

    }


    public void sendHelp(String originalSender) throws IOException {
        synchronized (socketArrayList) {
            for (Player worker : socketArrayList) {
                if (worker.getName().equals(originalSender)) {
                    worker.write(ServerHelper.help());
                }
            }
            System.out.println(ServerHelper.help());

        }
    }



 */
    public void whisper(String message, String whisperTarget, String originalSender) throws IOException {
        synchronized (playerArrayList) {
            for (Player player : playerArrayList) {
                if (player.getPlayerName().equals(whisperTarget)) {
                    message = message.substring(message.indexOf(" ") + 1, message.length());
                    message = message.substring(message.indexOf(" ") + 1, message.length());

                    player.broadcast(originalSender + " whispers: " + message);
                }
            }
            System.out.println(originalSender + " whispers: " + message);
        }
    }

    /*
    public void checkWhichASCII(String input, String originalName) throws IOException {
        if(input.equals(":cat:")){
            asciiCat(originalName);
        }

        if(input.equals(":goose:")){
            asciiGoose(originalName);
        }

        if(input.equals(":whale:")){
            asciiWhale(originalName);
        }
    }



    public void quit(String originalSender) throws IOException {
        synchronized (socketArrayList) {
            for (Player worker : socketArrayList) {
                if (worker.getName().equals(originalSender)) {
                    worker.quit();
                }
            }

        }
    }

     */


    /**
     * Method that serves a card to each player.
     * Each player needs to have a Hand object that will receive the cards.
     * Each player needs to be able to receive and show the cards they have afterwards so the whole thing works.
     */

    public void dealCardToAll(List<Card> deck) throws IOException, InterruptedException {
        synchronized (playerArrayList) {
            Card card;
            Thread.sleep(1000);
            dealerBroadcastAll("====== Dealing CARDS ======");
            Thread.sleep(1000);
            for (Player player : playerArrayList) {
                card = deck.remove(randomizer.nextInt(deck.size()));
                player.addCardToHand(card);
                //dealerBroadcastAll((player.getPlayerName() + " receives card: " + card.cardName));

                card = deck.remove(randomizer.nextInt(deck.size()));
                player.addCardToHand(card);
                //dealerBroadcastAll((player.getPlayerName() + " receives card: " + card.cardName));
            }
        }
    }


}
