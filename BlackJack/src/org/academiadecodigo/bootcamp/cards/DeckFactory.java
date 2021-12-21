package org.academiadecodigo.bootcamp.cards;

import java.util.LinkedList;

public class DeckFactory {

    LinkedList<Card> deck;

    public LinkedList<Card> getNewDeck() {
        deck = new LinkedList<Card>();

        for (int i = 0; i < 4; i++) {
                Card.cardStream()
                        .forEach(card -> deck.add(card));

        }
        return deck;
    }
}
    
    

