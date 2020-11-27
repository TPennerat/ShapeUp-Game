import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class AdvancedShapeUp extends ShapeUp {
    public AdvancedShapeUp(List<Player> players, AbstractBoard board, Queue<Card> deck) {
        super(players, board, deck);
    }

    @Override
    protected void startGame() {
        Iterator<Player> ip = playerList.iterator();
        int i = 1;
        Player p;
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
            i++;
        }
        startRound();
        isFirstRound = false;
        while (!isGameFinished()) {
            startRound();
        }
    }

    @Override
    protected void startRound() {
        for (Player p :
                playerList) {
            if (!isGameFinished()) {
                System.out.println("\nA vous de jouer " + p.getPseudo());
                Card toPlay = determineWhichCardToPlay(p);
                startPlayerTurn(p, toPlay);
                System.out.println("\nPlateau de jeu :\n");
                board.showBoard();
                System.out.print('\n');
            }
        }
        if (isGameFinished()) {
            System.out.println("\nPlateau de jeu :\n");
            board.showBoard();
        }
    }

    private Card determineWhichCardToPlay(Player p) {
        showHand(p);
        String messageWhichCard = "";
        return null;
    }

    @Override
    protected boolean isGameFinished() {
        int maxPlayer = playerList.size();
        int finishPlayer = 0;
        for (Player p:
             playerList) {
            if (p.getHand().size() == 1) {
                finishPlayer++;
            }
        }
        return deck.size() == 0 && maxPlayer == finishPlayer;
    }

    @Override
    protected void startPlayerTurn(Player p, Card c) {

        super.startPlayerTurn(p, c);
    }

    private void showHand(Player p) {
        System.out.println("Main du joueur "+p.getPseudo()+" :");
        for (Card c:
                p.getHand()) {
            System.out.print(c.toASCIIArt());
        }
        System.out.println();
    }
}
