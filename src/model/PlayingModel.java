package model;

import java.util.*;

public class PlayingModel extends Observable{

    protected List<Player> playerList;
    protected AbstractBoard board;
    protected Queue<Card> deck;
    private int MODE;
    public static final int NORMAL_MODE = 0;
    public static final int ADVANCED_MODE = 1;
    protected boolean isFirstRound;
    protected boolean isFirstTurn;
    public static int ACTUAL_STATE;
    public final static int SETTING_STATE = 0;
    public final static int PLAYING_STATE = 1;
    public final static int MOVING_STATE = 2;

    public PlayingModel() {
        deck = createCards();
        ACTUAL_STATE = SETTING_STATE;
        isFirstRound = true;
        isFirstTurn = true;
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

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public AbstractBoard getBoard() {
        return board;
    }

    public void setBoard(AbstractBoard board) {
        this.board = board;
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
            System.out.println("\n Score du joueur "+player.getPseudo()+" : "+player.getScore());
        }
    }

}
