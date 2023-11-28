import java.util.Scanner;

/**
 * Tyler Hinkie
 * 12.08.2023
 */
public class Game {
    private Player[] players;
    private Deck deck;
    private Card pile;
    // instance variable because I use it so much in my code
    private static final Scanner s = new Scanner(System.in);

    public Game(int numPlayers, String[] playerNames) {
        players = new Player[numPlayers];
        for (int i = 0; i < players.length; i++)
            players[i] = new Player(playerNames[i]);
        deck = new Deck();
    }

    // goes through the steps of playing the game
    public void playGame() {
        // shuffles deck
        deck.shuffle();

        // turns over first card for the pile
        pile = new Card(deck.deal());
        // deals the cards to the players
        deal();
        // simulates all turns, until 100
        for (int i = 0; i < 100; i++)
            for (Player player : players) {
                System.out.println(player.getName() + "'s turn: ");
                turn(player);
                if (checkWin(player))
                    return;
            }
        System.out.println("Game has ended. More than 100 turns have been played!");
    }

    // checks if given player has won the game, or has no cards left in their hand, and prints the victor's name
    public boolean checkWin(Player player) {
        if (player.getHand().isEmpty()) {
            System.out.println(player.getName());
            return true;
        }
        return false;
    }

    // deals the cards to the players in the game
    public void deal() {
        for (Player player : players)
            for (int j = 0; j < 5; j++)
                player.addCard(deck.deal());
    }

    // simulates a turn
    public void turn(Player player) {
        // prints the pile
        printPile();
        // prints the player's hand
        printHand(player);
        // asks the player for which card they want to play
        int index = pickIndex(player);
        // checks if the player wants to draw
        if (index == -1) {
            player.addCard(deck.deal());
            return;
        }
        // checks if the card is a valid card to be played as per the rules
        else if (!check(player.getHand().get(index))) {
            System.out.println("You can't play that card, please input another card or draw with -1.");
            // restarts the turn if a player has tried to play teh improper card
            turn(player);
        }
        // removes the card played from the hand
        player.getHand().remove(index);
    }

   // runs all of the checks for a valid card
    public boolean check(Card card) {
        // asks for a suit if the desired card to be played is an 8
        if (card.getPoint() == 8) {
            pile.setSuit(ask8());
            pile.setRank("8");
            pile.setPoint(8);
            return true;
        }
        // checks if the card is otherwise valid, either matching point or suit
        else if (checkCard(card)) {
            pile.setSuit(card.getSuit());
            pile.setRank(card.getRank());
            pile.setPoint(card.getPoint());
            return true;
        }
        return false;
    }

    // checks if card matches point or suit of the pile card to be played
    public boolean checkCard(Card card) {
        if (card.getSuit().equals(pile.getSuit()))
            return true;
        else return card.getPoint() == pile.getPoint();
    }

    // asks for the suit if the desired card to be played is an 8
    public String ask8() {
        System.out.println("What suit would you like it to be?");
        return s.nextLine();
    }

    // asks user for index of the card they want to play from their hand, or if they want to draw instead
    public int pickIndex(Player player) {
        System.out.println("What card would you like to play?");
        int index = s.nextInt();
        s.nextLine();
        return index;
    }

    // prints the players hand
    public void printHand(Player player) {
        System.out.println(player.getHand());
    }

    // prints the pile or top card in the pile
    public void printPile() {
        System.out.println(pile);
    }

    // prints beginning of game instructions to explain the game
    public static void printInstructions() {
        System.out.println("Hi. Welcome to Crazy 8, a card game for 1-6 players");
        System.out.println("You will be dealt 5 cards and must match the suit or rank of the top facing card.\nBut, " +
                "there's a twist: all 8's are wild!\nThis mean that you can switch the suit at any time if you play " +
                "an 8!\nHave fun!");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    // asks for the number of players playing
    public static int numPlayers() {
        System.out.println("How many players are playing crazy 8?");
        int num = s.nextInt();
        s.nextLine();
        return num;
    }

    // asks for the names of each player playing
    public static String[] playerNames(int numPlayers) {
        String[] playerNames = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("What is Player " + (i + 1) + "'s name?");
            playerNames[i] = s.nextLine();
        }
        return playerNames;
    }

    public static void main(String[] args) {
        // prints the game instructions
        printInstructions();
        // asks for teh number of players in the game
        int numPlayers = numPlayers();
        // gets the names of each player
        String[] playerNames = playerNames(numPlayers);
        // creates the game
        Game g = new Game(numPlayers, playerNames);
        // plays the game
        g.playGame();
    }
}
