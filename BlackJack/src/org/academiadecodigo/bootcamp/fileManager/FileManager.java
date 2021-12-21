package org.academiadecodigo.bootcamp.fileManager;

import org.academiadecodigo.bootcamp.blackjack.Player;

import java.io.*;

import static java.lang.Integer.parseInt;

public class FileManager {
    String thisLine = null;

// ------------------------------------------------------------------------------------- //

    // Define the path of where you want to save the Player Ledger file.
    String path = "src/org/academiadecodigo/bootcamp/fileManager/playerLedger.txt";
    int counter = 1;

// ------------------------------------------------------------------------------------- //

    // Declare and Instatiate a Buffered Writer and a Buffered Reader to write and read to the file.
    //Make sure to keep the Buffered Writer flagged with "true" as a second argument, so it keeps appending to the same file after it's created.
    BufferedWriter ledgerWriter = new BufferedWriter(new FileWriter(path, true));
    BufferedReader ledgerReader = new BufferedReader(new FileReader(path));

// ------------------------------------------------------------------------------------- //

    public FileManager() throws IOException {
    }

// ------------------------------------------------------------------------------------- //
// Writer Methods Start
// ------------------------------------------------------------------------------------- //

    public void writeNameToFile(String name) throws IOException {
        //ledgerWriter.append("-----------------------------------------------\n");
        //ledgerWriter.append("\n");

        ledgerWriter.append(counter + " - ").append("Name: ").append(name).append(" // ");
        ledgerWriter.flush();
        counter = counter + 1;

    }

// ------------------------------------------------------------------------------------- //

    public void writeEmailAddressToFile(String email) throws IOException {
        ledgerWriter.append("E-mail: ").append(email).append(" // ");
        ledgerWriter.flush();
    }

// ------------------------------------------------------------------------------------- //

    public void writePasswordToFile(String password) throws IOException {
        ledgerWriter.append("Password: ").append(password).append(" // ");
        //ledgerWriter.append("\n");
        ledgerWriter.flush();
    }

// ------------------------------------------------------------------------------------- //

    public void writeChipsToFile(int chips) throws IOException {
        String chipsString = String.valueOf(chips);
        ledgerWriter.append("Chips: ").append(chipsString).append(" |").append("\n");
        //ledgerWriter.append("\n");
        ledgerWriter.flush();
    }

// ------------------------------------------------------------------------------------- //

    // Call this method at the end of a playing session, when all clients/players disconnect.
    public void closeWriter() throws IOException {
        ledgerWriter.close();
    }

// ------------------------------------------------------------------------------------- //
// Reader Methods Start
// ------------------------------------------------------------------------------------- //



    public void credentialsWriter(Player player) {
        try {
            writeNameToFile(player.getPlayerName());
            writeEmailAddressToFile(player.getPlayerEmail());
            writePasswordToFile(player.getPlayerPassword());
            writeChipsToFile(player.getChips());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean credentialsVeryfier(Player player) throws IOException {
        String email = player.getPlayerEmail();

        while ((thisLine = ledgerReader.readLine()) != null) {
            if (thisLine.contains(email)) {
                String[] userInfo = thisLine.split(" ");
                player.setPlayerName(userInfo[3]);
                player.setPlayerEmail(userInfo[6]);
                player.setPlayerPassword(userInfo[9]);
                player.setChips(parseInt(userInfo[12]));
                return true;
            }
        }

        return false;
    }
}








