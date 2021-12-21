package org.academiadecodigo.bootcamp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {


    public static void main(String[] args) {
        BufferedReader in = null;
        PrintWriter out = null;
        Socket clientSocket = null;
        String inputConsole = null;
        String inputFromServer = null;
        BufferedReader inFromServer = null;

        try {




            clientSocket = new Socket(InetAddress.getLocalHost(), 8080);
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            while (true) {
                inputConsole = in.readLine();
                out.println(inputConsole);
                inputFromServer = inFromServer.readLine();
                System.out.println(inputFromServer);

            }






        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}


