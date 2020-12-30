package view;

import model.AbstractBoard;
import model.Card;
import model.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class AdvancedShapeUp extends ShapeUp {
    public AdvancedShapeUp(List<Player> players, AbstractBoard board, Queue<Card> deck) {
        super(players, board, deck);
    }

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
        deck.remove(); // hidden card
        startRound();
        isFirstRound = false;
        while (!isGameFinished()) {
            startRound();
        }

        for (Player player : playerList) {
            player.setVictoryCard(player.getHand().get(0));
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
        String modulaire = "";
        List<Card> hand = p.getHand();
        switch (hand.size()) {
            case 2:
                modulaire = (hand.get(0).toASCIIArt() + " : 1; " + hand.get(1).toASCIIArt() + " : 2");
                break;
            case 3:
                modulaire = (hand.get(0).toASCIIArt() + " : 1; " + hand.get(1).toASCIIArt() + " : 2; " + hand.get(2).toASCIIArt() + " : 3");
                break;
            default:
                break;
        }
        if (deck.size() != 0) {
            hand.add(deck.remove());
            p.setHand(hand);
        }
        String messageWhichCard = "Quelle carte de votre main souhaitez-vous jouer ? ("+modulaire+")";
        return hand.remove(p.askHandChoice(messageWhichCard));
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
