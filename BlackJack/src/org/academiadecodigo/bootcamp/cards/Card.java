package org.academiadecodigo.bootcamp.cards;

import java.util.stream.Stream;

public enum Card {

    // SPADES
    TWO_OF_SPADES("Two of Spades", 2,
            ".------.\n" +
                    "|2 /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' 2|\n" +
                    "`------'\n" +
                    "Two of Spades"),
    THREE_OF_SPADES("Three of Spades", 3,
            ".------.\n" +
                    "|3 /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' 3|\n" +
                    "`------'\n" +
                    "Three of Spades"),
    FOUR_OF_SPADES("Four of Spades", 4,
            ".------.\n" +
                    "|4 /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' 4|\n" +
                    "`------'\n" +
                    "Four of Spades"),
    FIVE_OF_SPADES("Five of Spades", 5,
            ".------.\n" +
                    "|5 /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' 5|\n" +
                    "`------'\n" +
                    "Five of Spades"),
    SIX_OF_SPADES("Six of Spades", 6,
            ".------.\n" +
                    "|6 /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' 6|\n" +
                    "`------'\n" +
                    "Six of Spades"),
    SEVEN_OF_SPADES("Seven of Spades", 7,
            ".------.\n" +
                    "|7 /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' 7|\n" +
                    "`------'\n" +
                    "Seven of Spades"),
    EIGHT_OF_SPADES("Eight of Spades", 8,
            ".------.\n" +
                    "|8 /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' 8|\n" +
                    "`------'\n" +
                    "Eight of Spades"),
    NINE_OF_SPADES("Nine of Spades", 9,
            ".------.\n" +
                    "|9 /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' 9|\n" +
                    "`------'\n" +
                    "Nine of Spades"),
    TEN_OF_SPADES("Ten of Spades", 10,
            ".------.\n" +
                    "|10/\\  |\n" +
                    "| (__) |\n" +
                    "|  ''10|\n" +
                    "`------'\n" +
                    "Ten of Spades"),
    JACK_OF_SPADES("Jack of Spades", 10,
            ".------.\n" +
                    "|J /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' J|\n" +
                    "`------'\n" +
                    "Jack of Spades"),
    QUEEN_OF_SPADES("Queen of Spades", 10,
            ".------.\n" +
                    "|Q /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' Q|\n" +
                    "`------'\n" +
                    "Queen of Spades"),
    KING_OF_SPADES("King of Spades", 10,
            ".------.\n" +
                    "|K /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' K|\n" +
                    "`------'\n" +
                    "King of Spades"),
    ACE_OF_SPADES("Ace of Spades", 11,
            ".------.\n" +
                    "|A /\\  |\n" +
                    "| (__) |\n" +
                    "|  '' A|\n" +
                    "`------'\n" +
                    "Ace of Spades"),

    // HEARTS
    TWO_OF_HEARTS("Two of Hearts", 2,
            ".------.\n" +
                    "|2     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ 2|\n" +
                    "`------'\n" +
                    "Two of Hearts"),
    THREE_OF_HEARTS("Three of Hearts", 3,
            ".------.\n" +
                    "|3     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ 3|\n" +
                    "`------'\n" +
                    "Three of Hearts"),
    FOUR_OF_HEARTS("Four of Hearts", 4,
            ".------.\n" +
                    "|4     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ 4|\n" +
                    "`------'\n" +
                    "Four of Hearts"),
    FIVE_OF_HEARTS("Five of Hearts", 5,
            ".------.\n" +
                    "|5     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ 5|\n" +
                    "`------'\n" +
                    "Five of Hearts"),
    SIX_OF_HEARTS("Six of Hearts", 6,
            ".------.\n" +
                    "|6     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ 6|\n" +
                    "`------'\n" +
                    "Six of Hearts"),
    SEVEN_OF_HEARTS("Seven of Hearts", 7,
            ".------.\n" +
                    "|7     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ 7|\n" +
                    "`------'\n" +
                    "Seven of Hearts"),
    EIGHT_OF_HEARTS("Eight of Hearts", 8,
            ".------.\n" +
                    "|8     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ 8|\n" +
                    "`------'\n" +
                    "Eight of Hearts"),
    NINE_OF_HEARTS("Nine of Hearts", 9,
            ".------.\n" +
                    "|9     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ 9|\n" +
                    "`------'\n" +
                    "Nine of Hearts"),
    TEN_OF_HEARTS("Ten of Hearts", 10,
            ".------.\n" +
                    "|10    |\n" +
                    "| (\\/) |\n" +
                    "|  \\/10|\n" +
                    "`------'\n" +
                    "Ten of Hearts"),
    JACK_OF_HEARTS("Jack of Hearts", 10,
            ".------.\n" +
                    "|J     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ J|\n" +
                    "`------'\n" +
                    "Jack of Hearts"),
    QUEEN_OF_HEARTS("Queen of Hearts", 10,
            ".------.\n" +
                    "|Q     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ Q|\n" +
                    "`------'\n" +
                    "Queen of Hearts"),
    KING_OF_HEARTS("King of Hearts", 10,
            ".------.\n" +
                    "|K     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ K|\n" +
                    "`------'\n" +
                    "King of Hearts"),
    ACE_OF_HEARTS("Ace of Hearts", 11,
            ".------.\n" +
                    "|A     |\n" +
                    "| (\\/) |\n" +
                    "|  \\/ A|\n" +
                    "`------'\n" +
                    "Ace of Hearts"),

    // CLUBS
    TWO_OF_CLUBS("Two of Clubs", 2,
            ".------.\n" +
                    "|2 ()  |\n" +
                    "| ()() |\n" +
                    "|  '' 2|\n" +
                    "`------'\n" +
                    "Two of Clubs"),
    THREE_OF_CLUBS("Three of Clubs", 3,
            ".------.\n" +
                    "|3 ()  |\n" +
                    "| ()() |\n" +
                    "|  '' 3|\n" +
                    "`------'\n" +
                    "Three of Clubs"),
    FOUR_OF_CLUBS("Four of Clubs", 4,
            ".------.\n" +
                    "|4 ()  |\n" +
                    "| ()() |\n" +
                    "|  '' 4|\n" +
                    "`------'\n" +
                    "Four of Clubs"),
    FIVE_OF_CLUBS("Five of Clubs", 5,
            ".------.\n" +
                    "|5 ()  |\n" +
                    "| ()() |\n" +
                    "|  '' 5|\n" +
                    "`------'\n" +
                    "Five of Clubs"),
    SIX_OF_CLUBS("Six of Clubs", 6,
            ".------.\n" +
                    "|6 ()  |\n" +
                    "| ()() |\n" +
                    "|  '' 6|\n" +
                    "`------'\n" +
                    "Six of Clubs"),
    SEVEN_OF_CLUBS("Seven of Clubs", 7,
            ".------.\n" +
                    "|7 ()  |\n" +
                    "| ()() |\n" +
                    "|  '' 7|\n" +
                    "`------'\n" +
                    "Seven of Clubs"),
    EIGHT_OF_CLUBS("Eight of Clubs", 8,
            ".------.\n" +
                    "|8 ()  |\n" +
                    "| ()() |\n" +
                    "|  '' 8|\n" +
                    "`------'\n" +
                    "Eight of Clubs"),
    NINE_OF_CLUBS("Nine of Clubs", 9,
            ".------.\n" +
                    "|9 ()  |\n" +
                    "| ()() |\n" +
                    "|  '' 9|\n" +
                    "`------'\n" +
                    "Nine of Clubs"),
    TEN_OF_CLUBS("Ten of Clubs", 10,
            ".------.\n" +
                    "|10()  |\n" +
                    "| ()() |\n" +
                    "|  ''10|\n" +
                    "`------'\n" +
                    "Ten of Clubs"),
    JACK_OF_CLUBS("Jack of Clubs", 10,
            ".------.\n" +
                    "|J ()  |\n" +
                    "| ()() |\n" +
                    "|  '' J|\n" +
                    "`------'\n" +
                    "Jack of Clubs"),
    QUEEN_OF_CLUBS("Queen of Clubs", 10,
            ".------.\n" +
                    "|Q ()  |\n" +
                    "| ()() |\n" +
                    "|  '' Q|\n" +
                    "`------'\n" +
                    "Queen of Clubs"),
    KING_OF_CLUBS("King of Clubs", 10,
            ".------.\n" +
                    "|K ()  |\n" +
                    "| ()() |\n" +
                    "|  '' K|\n" +
                    "`------'\n" +
                    "King of Clubs"),
    ACE_OF_CLUBS("Ace of Clubs", 11,
            ".------.\n" +
                    "|A ()  |\n" +
                    "| ()() |\n" +
                    "|  '' A|\n" +
                    "`------'\n" +
                    "Ace of Clubs"),

    // DIAMONDS
    TWO_OF_DIAMONDS("Two of Diamonds", 2,
            ".------.\n" +
                    "|2 /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     2|\n" +
                    "`------'\n" +
                    "Two of Diamonds"),
    THREE_OF_DIAMONDS("Three of Diamonds", 3,
            ".------.\n" +
                    "|3 /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     3|\n" +
                    "`------'\n" +
                    "Three of Diamonds"),
    FOUR_OF_DIAMONDS("Four of Diamonds", 4,
            ".------.\n" +
                    "|4 /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     4|\n" +
                    "`------'\n" +
                    "Four of Diamonds"),
    FIVE_OF_DIAMONDS("Five of Diamonds", 5,
            ".------.\n" +
                    "|5 /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     5|\n" +
                    "`------'\n" +
                    "Five of Diamonds"),
    SIX_OF_DIAMONDS("Six of Diamonds", 6,
            ".------.\n" +
                    "|6 /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     6|\n" +
                    "`------'\n" +
                    "Six of Diamonds"),
    SEVEN_OF_DIAMONDS("Seven of Diamonds", 7,
            ".------.\n" +
                    "|7 /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     7|\n" +
                    "`------'\n" +
                    "Seven of Diamonds"),
    EIGHT_OF_DIAMONDS("Eight of Diamonds", 8,
            ".------.\n" +
                    "|8 /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     8|\n" +
                    "`------'\n" +
                    "Eight of Diamonds"),
    NINE_OF_DIAMONDS("Nine of Diamonds", 9,
            ".------.\n" +
                    "|9 /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     9|\n" +
                    "`------'\n" +
                    "Nine of Diamonds"),
    TEN_OF_DIAMONDS("Ten of Diamonds", 10,
            ".------.\n" +
                    "|10/\\  |\n" +
                    "|  \\/  |\n" +
                    "|    10|\n" +
                    "`------'\n" +
                    "Ten of Diamonds"),
    JACK_OF_DIAMONDS("Jack of Diamonds", 10,
            ".------.\n" +
                    "|J /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     J|\n" +
                    "`------'\n" +
                    "Jack of Diamonds"),
    QUEEN_OF_DIAMONDS("Queen of Diamonds", 10,
            ".------.\n" +
                    "|Q /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     Q|\n" +
                    "`------'\n" +
                    "Queen of Diamonds"),
    KING_OF_DIAMONDS("King of Diamonds", 10,
            ".------.\n" +
                    "|K /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     K|\n" +
                    "`------'\n" +
                    "King of Diamonds"),
    ACE_OF_DIAMONDS("Ace of Diamonds", 11,
            ".------.\n" +
                    "|A /\\  |\n" +
                    "|  \\/  |\n" +
                    "|     A|\n" +
                    "`------'\n" +
                    "Ace of Diamonds");

    public final String cardName;
    public final int cardValue;
    public final String cardArt;

    Card(String cardArt, int cardValue, String cardName) {
        this.cardValue = cardValue;
        this.cardArt = cardArt;
        this.cardName = cardName;
    }

    public static Stream<Card> cardStream() {
        return Stream.of(Card.values());
    }

    /*public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    } */
}
