package view;

import controller.GameController;
import model.Card;
import model.Player;
import model.PlayingModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdvancedShapeUp extends ShapeUp {
    public AdvancedShapeUp(PlayingModel pm, GameController gc) {
        super(pm,gc);
    }

    protected void startGame() {
        Iterator<Player> ip = pm.getPlayerList().iterator();
        int i = 1;
        Player p;
        while (ip.hasNext()) {
            p = ip.next();
            Card pc1 = pm.getDeck().remove();
            Card pc2 = pm.getDeck().remove();
            Card pc3 = pm.getDeck().remove();
            ArrayList<Card> hand = new ArrayList<>();
            hand.add(pc1);
            hand.add(pc2);
            hand.add(pc3);
            p.setHand(hand);
            i++;
        }
        pm.getDeck().remove(); // hidden card
        startRound();
        pm.setIsFirstRound(false);
        while (!pm.isGameFinished()) {
            startRound();
        }

        for (Player player : pm.getPlayerList()) {
            player.setVictoryCard(player.getHand().get(0));
        }

    }

    @Override
    protected void startRound() {
        for (Player p :
                pm.getPlayerList()) {
            if (!pm.isGameFinished()) {
                System.out.println("\nA vous de jouer " + p.getPseudo());
                Card toPlay = determineWhichCardToPlay(p);
                startPlayerTurn(p, toPlay);
                System.out.println("\nPlateau de jeu :\n");
                pm.getBoard().showConsoleBoard();
                System.out.print('\n');
            }
        }
        if (pm.isGameFinished()) {
            System.out.println("\nPlateau de jeu :\n");
            pm.getBoard().showConsoleBoard();
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
        if (pm.getDeck().size() != 0) {
            hand.add(pm.getDeck().remove());
            p.setHand(hand);
        }
        String messageWhichCard = "Quelle carte de votre main souhaitez-vous jouer ? (" + modulaire + ")";
        return hand.remove(p.askHandChoice(messageWhichCard));
    }


    @Override
    protected void startPlayerTurn(Player p, Card c) {

        super.startPlayerTurn(p, c);
    }

    private void showHand(Player p) {
        System.out.println("Main du joueur " + p.getPseudo() + " :");
        for (Card c :
                p.getHand()) {
            System.out.print(c.toASCIIArt());
        }
        System.out.println();
    }
}
