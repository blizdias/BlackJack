package org.academiadecodigo.bootcamp.blackjack;

import org.academiadecodigo.bootcamp.Graphics;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.cards.Card;
import org.academiadecodigo.bootcamp.fileManager.FileManager;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.PasswordInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.bootcamp.server.GameServer;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Player implements Runnable{

    private Prompt prompt;
    private PrintWriter out;
    private GameServer gameServer;
    private Socket playerSocket;
    private int chips;
    private boolean playerResgistered;
    private boolean playerAge;
    private String playerName;
    private String playerEmail;
    private String playerPassword;
    private Hand playerHand;
    private int betValue;
    private FileManager fileManager;


    public Player(Socket playerSocket, GameServer gameServer) {
        chips = 100;
        this.playerSocket = playerSocket;
        this.gameServer = gameServer;
        playerHand = new Hand();
        try {
            fileManager = new FileManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void scanPlayerName() throws IOException {
        StringInputScanner questionName = new StringInputScanner();
        questionName.setMessage("\nWhat is your name?\n");
        playerName = prompt.getUserInput(questionName);
    }


    public Boolean scanPlayerAge() {
        String[] options = {"Yes", "No"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Are you over 18 years old?");
        int answerIndex = prompt.getUserInput(scanner);
        return options[answerIndex - 1].equals("Yes");
    }

    public void quitAge() throws IOException {
        broadcast("It is illegal in your jurisdiction for you to be at this Casino. See you in a couple of years!");
        playerSocket.close();
        gameServer.getPlayerList().remove(gameServer.getPlayerList().indexOf(this));

    }

    public void setPlayerRegistered() {
        String[] options = {"Yes", "No"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Is this your first time in our Casino?");
        int answerIndex = prompt.getUserInput(scanner);
        playerResgistered = options[answerIndex - 1].equals("No");
    }

    public void setNewEmail() {
        StringInputScanner email = new StringInputScanner();
        email.setMessage("\nThanks for choosing Casino Royale, " + playerName + ". \n\nPlease enter your email:\n");
        playerEmail = prompt.getUserInput(email);

    }

    public void setNewPassword() throws IOException {
        StringInputScanner password = new PasswordInputScanner();
        password.setMessage("Please choose a password:\n");
        playerPassword = prompt.getUserInput(password);
        broadcast("\nThank you for registering with us, " + playerName + ".");

    }

    public void checkEmail() throws IOException {
        StringInputScanner email = new StringInputScanner();
        email.setMessage("\nPlease enter your registered email:\n");
        playerEmail = prompt.getUserInput(email);
        boolean bool = fileManager.credentialsVeryfier(this);

        if (!bool) {
            broadcast("Sorry, the information you entered does not match our records.\nPlease insert your email again:\n");
            checkEmail();
        }
        broadcast("Correct Email.\n");
    }


    public void checkPassword() throws IOException {
        StringInputScanner password = new PasswordInputScanner();
        password.setMessage("\nPlease enter your password:\n");
        playerPassword = prompt.getUserInput(password);
        boolean bool = fileManager.credentialsVeryfier(this);

        if (!password.equals(playerPassword)) {
            broadcast("Sorry, the password is incorrect.\n");
            checkPassword();
        }
        broadcast("Correct Password.\n");
    }


    @Override
    public void run() {
        try {

            broadcast("Welcome to...");
            TimeUnit.MILLISECONDS.sleep(1000);
            broadcast(Graphics.welcomeToTheCasino());
            TimeUnit.MILLISECONDS.sleep(2000);
            broadcast(Graphics.printRules());
            TimeUnit.MILLISECONDS.sleep(10000);


            prompt();
            playerAge = scanPlayerAge();
            if (!playerAge) {quitAge();}

            scanPlayerName();
                    /*
            setPlayerRegistered();
            if (!playerResgistered) {
                scanPlayerName();setNewEmail(); setNewPassword(); fileManager.credentialsWriter(this);}
            else {checkEmail(); }//checkPassword();}

                     */
            broadcast("Your starting balance is: " + chips + " chips.");



            /**
             * This is here so the Server doesn't give cards to someone who hasn't finished entering his name
             *Otherwise you deal cards to null!
             */
            gameServer.plsWakeUp();












        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void prompt() throws IOException {
        prompt = new Prompt(playerSocket.getInputStream(), new PrintStream(playerSocket.getOutputStream()));
    }

    public void write(String message) throws IOException {
        out = new PrintWriter(playerSocket.getOutputStream(), true);
        out.write(message);
        out.flush();

    }

    public int hitOrPass() throws IOException {
        if (chips >= betValue) {
            String[] options = {"Hit", "Stay", "Double Down"};
            MenuInputScanner scanner = new MenuInputScanner(options);
            scanner.setMessage("What is your next move?");
            if (!playerSocket.isClosed()) {
                int answerIndex = prompt.getUserInput(scanner);

                switch (answerIndex) {
                    case (1):
                        gameServer.dealerBroadcastAll("\n\n========== " + playerName + " hits! ========== \n");
                        break;
                    case (2):
                        gameServer.dealerBroadcastAll("\n\n========== " + playerName + " stays! ========== \n");
                        break;
                    case (3):
                        gameServer.dealerBroadcastAll("\n\n========== " + playerName + " double down! ========== \n");
                        break;
                }
                return answerIndex;
            }
        }
        String[] options = {"Hit", "Stay"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("What is your next move?");
        if (!playerSocket.isClosed()) {
            int answerIndex = prompt.getUserInput(scanner);


            switch (answerIndex) {
                case (1):
                    gameServer.dealerBroadcastAll("\n\n========== " + playerName + " hits! ========== \n");
                    break;
                case (2):
                    gameServer.dealerBroadcastAll("\n\n========== " + playerName + " stays! ========== \n");
                    break;
            }
            return answerIndex;
        }
        return 2;
    }


    public void broadcast(String message) {
        //System.out.println("Inside broadcast " + message);
        try {
            out = new PrintWriter(playerSocket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            System.out.println("ola");
        }


    }

    public void addCardToHand(Card card){
        playerHand.addCard(card);
    }

    public List<Card> getListOfCardsFromHand(){
        return playerHand.getCurrentCards();
    }

    public void resetHand(){
        playerHand.resetHand();
    }

    public void gameOver(){
        broadcast("You have run out of balance. We are sorry for your luck.\nPlease login again soon, and your balance will be refilled.");
        broadcast(Graphics.gameCredits());
        try {
            playerSocket.close();
        } catch (IOException e) {
            System.out.println("exception in close");
        }
    }

    public int betValidationScanner () {

        Set<Integer> validBetValue = new HashSet<>();

        int numberBets = getChips()/10;
        if (numberBets > 10) {
            numberBets = 10;
        }

        for (int i = 1 ; i <= numberBets; i++) {
            validBetValue.add(Betting.MINIMUM_BET*i);
        }


        IntegerInputScanner bet = new IntegerSetInputScanner(validBetValue);
        bet.setMessage("\nHow much will your bet be? ");
        bet.setError("Place a bet within your credit limit, that is a multiple of 10 and under 100 chips.");
        return prompt.getUserInput(bet);

    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public int getBetValue() {
        return betValue;
    }

    public void setBetValue(int betValue) {
        this.betValue = betValue;
    }


    public String getPlayerEmail() {
        return playerEmail;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    public void setPlayerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
    }






}
