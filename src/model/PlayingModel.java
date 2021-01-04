package model;

import java.util.*;

public class PlayingModel extends Observable {

    protected List<Player> playerList;
    protected AbstractBoard board;

    public Queue<Card> getDeck() {
        return deck;
    }

    protected Queue<Card> deck;

    public int getMODE() {
        return MODE;
    }

    private int MODE;
    public static final int NORMAL_MODE = 0;
    public static final int ADVANCED_MODE = 1;
    protected boolean isFirstRound;
    protected boolean isFirstTurn;

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    private int currentPlayerIndex;

    public static int getActualState() {
        return ACTUAL_STATE;
    }

    public static int ACTUAL_STATE;
    public static final int SETTING_STATE = 0;
    public static final int PLAYING_STATE = 1;
    public static final int MOVING_STATE = 2;
    public static final int WAITING_STATE = 3;

    public PlayingModel() {
        deck = createCards();
        ACTUAL_STATE = SETTING_STATE;
        isFirstRound = true;
        isFirstTurn = true;
        currentPlayerIndex = 0;
    }

    private Queue<Card> createCards() {
        List<Card> shuffleCardList = new ArrayList<>();
        shuffleCardList.add(new Card(Color.RED, Shape.SQUARE, false, "docs/cards/hollow_red_square.png"));
        shuffleCardList.add(new Card(Color.BLUE, Shape.SQUARE, false, "docs/cards/hollow_blue_square.png"));
        shuffleCardList.add(new Card(Color.GREEN, Shape.SQUARE, false, "docs/cards/hollow_green_square.png"));
        shuffleCardList.add(new Card(Color.RED, Shape.SQUARE, true, "docs/cards/filled_red_square.png"));
        shuffleCardList.add(new Card(Color.BLUE, Shape.SQUARE, true, "docs/cards/filled_blue_square.png"));
        shuffleCardList.add(new Card(Color.GREEN, Shape.SQUARE, true, "docs/cards/filled_green_square.png"));

        shuffleCardList.add(new Card(Color.RED, Shape.TRIANGLE, false, "docs/cards/hollow_red_triangle.png"));
        shuffleCardList.add(new Card(Color.BLUE, Shape.TRIANGLE, false, "docs/cards/hollow_blue_triangle.png"));
        shuffleCardList.add(new Card(Color.GREEN, Shape.TRIANGLE, false, "docs/cards/hollow_green_triangle.png"));
        shuffleCardList.add(new Card(Color.RED, Shape.TRIANGLE, true, "docs/cards/filled_red_triangle.png"));
        shuffleCardList.add(new Card(Color.BLUE, Shape.TRIANGLE, true, "docs/cards/filled_blue_triangle.png"));
        shuffleCardList.add(new Card(Color.GREEN, Shape.TRIANGLE, true, "docs/cards/filled_green_triangle.png"));

        shuffleCardList.add(new Card(Color.RED, Shape.CIRCLE, false, "docs/cards/hollow_red_circle.png"));
        shuffleCardList.add(new Card(Color.BLUE, Shape.CIRCLE, false, "docs/cards/hollow_blue_circle.png"));
        shuffleCardList.add(new Card(Color.GREEN, Shape.CIRCLE, false, "docs/cards/hollow_green_circle.png"));
        shuffleCardList.add(new Card(Color.RED, Shape.CIRCLE, true, "docs/cards/filled_red_circle.png"));
        shuffleCardList.add(new Card(Color.BLUE, Shape.CIRCLE, true, "docs/cards/filled_blue_circle.png"));
        shuffleCardList.add(new Card(Color.GREEN, Shape.CIRCLE, true, "docs/cards/filled_green_circle.png"));
        Collections.shuffle(shuffleCardList);
        return new LinkedList<>(shuffleCardList);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public AbstractBoard getBoard() {
        return board;
    }

    public synchronized void setVariables(List<Player> lp, AbstractBoard b, int mode) {
        board = b;
        playerList = lp;
        MODE = mode;
        ACTUAL_STATE = WAITING_STATE;
        setVictoryCards();
        setHands();
        setChanged();
        notifyObservers();
    }

    private void setHands() {
        Iterator<Player> ip = playerList.iterator();
        Player p;
        if (MODE==NORMAL_MODE) {
            while (ip.hasNext()) {
                p = ip.next();
                Card pc1 = deck.remove();
                ArrayList<Card> hand = new ArrayList<>();
                hand.add(pc1);
                p.setHand(hand);
            }
        } else {
            while (ip.hasNext()) {
                p = ip.next();
                Card pc1 = deck.remove();
                Card pc2 = deck.remove();
                Card pc3 = deck.remove();
                ArrayList<Card> hand = new ArrayList<>();
                hand.add(pc1);
                hand.add(pc2);
                hand.add(pc3);
                p.setHand(hand);
            }
        }
    }

    private void setVictoryCards() {
        Iterator<Player> ip = playerList.iterator();
        Player p;
        while (ip.hasNext()) {
            p = ip.next();
            Card pc = deck.remove();
            p.setVictoryCard(pc);
        }
    }

    protected void startGame() {
        Iterator<Player> ip = playerList.iterator();
        int i = 1;
        Player p;
        while (ip.hasNext()) {
            p = ip.next();
            System.out.println("Carte de victoire du joueur n°" + i);
            Card pc = deck.remove();
            p.setVictoryCard(pc);
            System.out.println(pc.toASCIIArt());
            i++;
        }
        deck.remove(); // hidden card
        /*startRound();
        isFirstRound = false;
        while (!isGameFinished()) {
            startRound();
        }*/
        calculeAndShowScore();
    }

    protected void calculeAndShowScore() {
        for (Player player : playerList) {  //Calcul du score pour chaque joueur (pas fini)
            board.accept(new ScoreCalculator(), player);
            System.out.println("\n Score du joueur " + player.getPseudo() + " : " + player.getScore());
        }
    }

    public boolean isGameFinished() {
        return deck.size() == 0;
    }

    public boolean isFirstRound() {
        return isFirstRound;
    }

    public boolean isFirstTurn() {
        return isFirstTurn;
    }

    public void setIsFirstTurn(boolean isFirstTurn) {
        this.isFirstTurn = isFirstTurn;
    }

    public void setIsFirstRound(boolean isFirstRound) {
        this.isFirstRound = isFirstRound;
    }
}
