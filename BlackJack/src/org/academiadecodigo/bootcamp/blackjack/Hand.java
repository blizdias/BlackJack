package org.academiadecodigo.bootcamp.blackjack;

import org.academiadecodigo.bootcamp.cards.Card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Hand {
    List<Card> cardList = null;

    public Hand(){
        cardList = Collections.synchronizedList(new LinkedList<>());
    }

    public void addCard(Card card){
        cardList.add(card);
    }

    public List<Card> getCurrentCards(){
        return cardList;
    }

    public void resetHand(){
        cardList = Collections.synchronizedList(new LinkedList<>());
    }
}
