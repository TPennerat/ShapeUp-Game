package model;

import javax.crypto.spec.DESedeKeySpec;
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
    protected boolean firstRound;
    protected boolean firstTurn;

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    private int currentPlayerIndex;

    public int getActualState() {
        return ACTUAL_STATE;
    }

    public int ACTUAL_STATE;
    public static final int SETTING_STATE = 0;
    public static final int PLAYING_STATE = 1;
    public static final int MOVING_STATE = 2;
    public static final int WAITING_STATE = 3;
    public static final int FINISHED_STATE = 4;

    public PlayingModel() {
        deck = createCards();
        ACTUAL_STATE = SETTING_STATE;
        firstRound = true;
        firstTurn = true;
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
        if (MODE == ADVANCED_MODE) {
            int maxPlayer = playerList.size();
            int finishPlayer = 0;
            for (Player p :
                    playerList) {
                if (p.getHand().size() == 1) {
                    finishPlayer++;
                }
            }
            return playerList.size() == 0 && maxPlayer == finishPlayer;
        } else {
            return deck.size() == 0;
        }
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public boolean isFirstTurn() {
        return firstTurn;
    }

    public void setIsFirstTurn(boolean isFirstTurn) {
        this.firstTurn = isFirstTurn;
    }

    public void setIsFirstRound(boolean isFirstRound) {
        this.firstRound = isFirstRound;
    }

    public void play(Coord coord, Card card) {
        board.placeCard(coord, card);
        Player p = playerList.get(currentPlayerIndex);
        p.removeCardHand(card);
        ACTUAL_STATE = PLAYING_STATE;
        setChanged();
        notifyObservers();
    }

    public void determineNextPlayer() {
        if (firstTurn) {
            firstTurn = false;
        }
        playerList.get(currentPlayerIndex).addCardHand(deck.remove());
        if (currentPlayerIndex == playerList.size() - 1) {
            currentPlayerIndex = 0;
            if (firstRound) {
                firstRound = false;
            }
        } else {
            currentPlayerIndex++;
        }
        if (isGameFinished()) {
            ACTUAL_STATE = FINISHED_STATE;
        } else {
            IATryToPlay();
            ACTUAL_STATE = WAITING_STATE;
        }
        setChanged();
        notifyObservers();
    }

    private void IATryToPlay() {
        Player p = playerList.get(currentPlayerIndex);
        if (p instanceof VirtualPlayer) {
            VirtualPlayer vrp = (VirtualPlayer) p;
            Card card;
            if (MODE == NORMAL_MODE) {
                card = p.getHand().get(0);
            } else {
                card = p.getHand().get(p.askHandChoice(""));
            }
            vrp.autoPlay(MODE, board, this, card);
            determineNextPlayer();
        }
    }

    public void move(VirtualPlayer p) {
        Coord res = p.move(board.getPotentialMinimumX(), board.getPotentialMinimumY(),
                board.getPotentialMaximumX(),board.getRealMaximumY(), 0);
        while (!board.isCardCorrectlyPlaced(res)) {
            res = p.move(board.getPotentialMinimumX(), board.getPotentialMinimumY(),
                    board.getPotentialMaximumX(),board.getRealMaximumY(), 0);
        }
        board.moveCard(res, p.chooseMovingCard(board));
    }

    public void move(Coord coord, Card c) {
        board.moveCard(coord, c);
    }

    public Coord play(VirtualPlayer p) {
        Coord res = p.play(board.getPotentialMinimumX(), board.getPotentialMinimumY(), board.getPotentialMaximumX(),
                board.getPotentialMaximumY());
        while (!board.isCardCorrectlyPlaced(res)) {
            res = p.play(board.getPotentialMinimumX(), board.getPotentialMinimumY(), board.getPotentialMaximumX(),
                    board.getPotentialMaximumY());
        }
        return res;
    }
}
