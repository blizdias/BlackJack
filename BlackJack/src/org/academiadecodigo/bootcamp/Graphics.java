package org.academiadecodigo.bootcamp;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Graphics {

    public static String welcomeToTheCasino(){

        return Colors.YELLOW_BOLD + "                                                                                                          \n" +
                "    ,o888888o.           .8.            d888888o.    8 8888 b.             8     ,o888888o.               \n" +
                "   8888     `88.        .888.         .`8888:' `88.  8 8888 888o.          8  . 8888     `88.             \n" +
                ",8 8888       `8.      :88888.        8.`8888.   Y8  8 8888 Y88888o.       8 ,8 8888       `8b            \n" +
                "88 8888               . `88888.       `8.`8888.      8 8888 .`Y888888o.    8 88 8888        `8b           \n" +
                "88 8888              .8. `88888.       `8.`8888.     8 8888 8o. `Y888888o. 8 88 8888         88           \n" +
                "88 8888             .8`8. `88888.       `8.`8888.    8 8888 8`Y8o. `Y88888o8 88 8888         88           \n" +
                "88 8888            .8' `8. `88888.       `8.`8888.   8 8888 8   `Y8o. `Y8888 88 8888        ,8P           \n" +
                "`8 8888       .8' .8'   `8. `88888.  8b   `8.`8888.  8 8888 8      `Y8o. `Y8 `8 8888       ,8P            \n" +
                "   8888     ,88' .888888888. `88888. `8b.  ;8.`8888  8 8888 8         `Y8o.`  ` 8888     ,88'             \n" +
                "    `8888888P'  .8'       `8. `88888. `Y8888P ,88P'  8 8888 8            `Yo     `8888888P'               \n" +
                "8 888888888o.      ,o888888o.  `8.`8888.      ,8'    .8.          8 8888         8 8888888888             \n" +
                "8 8888    `88.  . 8888     `88. `8.`8888.    ,8'    .888.         8 8888         8 8888                   \n" +
                "8 8888     `88 ,8 8888       `8b `8.`8888.  ,8'    :88888.        8 8888         8 8888                   \n" +
                "8 8888     ,88 88 8888        `8b `8.`8888.,8'    . `88888.       8 8888         8 8888                   \n" +
                "8 8888.   ,88' 88 8888         88  `8.`88888'    .8. `88888.      8 8888         8 888888888888           \n" +
                "8 888888888P'  88 8888         88   `8. 8888    .8`8. `88888.     8 8888         8 8888                   \n" +
                "8 8888`8b      88 8888        ,8P    `8 8888   .8' `8. `88888.    8 8888         8 8888                   \n" +
                "8 8888 `8b.    `8 8888       ,8P      8 8888  .8'   `8. `88888.   8 8888         8 8888                   \n" +
                "8 8888   `8b.   ` 8888     ,88'       8 8888 .888888888. `88888.  8 8888         8 8888                   \n" +
                "8 8888     `88.    `8888888P'         8 8888.8'       `8. `88888. 8 888888888888 8 888888888888 \n\n" + Colors.RESET;


    }


    public static String printRules() {
        return  Colors.GREEN + "Blackjack - Casino Royale Edition\nRules: \n01 - Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.\n" +
                "02 - Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.\n" +
                "03 - The players cards are added up for their total.\n" +
                "04 - Minimum bet is 10, and it can increase in multiples of itself (20, 30, 40, ...).\n" +
                "05 - Players 'Hit' to gain another card from the house. Players 'Stay' to keep their current card total.\n" +
                "06 - Dealer 'Hits' until they equal or exceed 17.\n" +
                "07 - The goal is to have a higher card total than the dealer without going over 21.\n" +
                "08 - If the player total equals the dealer total, it is a 'Draw' and the hand ends.\n" +
                "09 - Players win their bet if they beat the dealer. Players win 1.5x their bet if they get 'Blackjack'.\n" +
                "10 - Players can elect to 'Double Down', drawing only one more card, and doubling the value of their bet.\n" + Colors.RESET;
    }



    public static String gameCredits() {
        return Colors.CYAN_BOLD + "                     ,---.           ,---.\n" +
                "                    / /\"`.\\.--\"\"\"--./,'\"\\ \\\n" +
                "                    \\ \\    _       _    / /\n" +
                "                     `./  / __   __ \\  \\,'\n" +
                "                      /    /_O)_(_O\\    \\\n" +
                "                      |  .-'  ___  `-.  |\n" +
                "                   .--|       \\_/       |--.\n" +
                "                 ,'    \\   \\   |   /   /    `.\n" +
                "                /       `.  `--^--'  ,'       \\\n" +
                "             .-\"\"\"\"\"-.    `--.___.--'     .-\"\"\"\"\"-.\n" +
                ".-----------/         \\------------------/         \\--------------.\n" +
                "| .---------\\         /----------------- \\         /------------. |\n" +
                "| |          `-`--`--'                    `--'--'-'             | |\n" +
                "| |                          Bruno Liz                          | |\n" +
                "| |                         Fábio Bento                         | |\n" +
                "| |                        Gonçalo Cunha                        | |\n" +
                "| |                          Rui Silva                          | |\n" +
                "| |                                                             | |\n" +
                "| |             <SpaceINTvaders_> ∞ Bootcamp #63 ∞ 2021         | |\n" +
                "| |_____________________________________________________________| |\n" +
                "|_________________________________________________________________|\n" +
                "                   )__________|__|__________(\n" +
                "                  |            ||            |\n" +
                "                  |____________||____________|\n" +
                "                    ),-----.(      ),-----.(\n" +
                "                  ,'   ==.   \\    /  .==    `.\n" +
                "                 /            )  (            \\\n" +
                "                 `==========='    `==========='  <A/C_>" + Colors.RESET;
    }

}
