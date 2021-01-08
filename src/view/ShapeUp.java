package view;

import controller.GameController;
import model.*;

import java.util.*;

public class ShapeUp implements Observer {

    protected PlayingModel pm;
    protected GameController gc;

    public ShapeUp(PlayingModel pm, GameController gc) {
        this.pm = pm;
        this.gc = gc;
    }

    public void launcher() {
        System.out.println("Bienvenue dans ShapeUp !"); // salutation du joueur
        System.out.println("Préparation du paquet de cartes...");
        System.out.println("Paquet de cartes prêt.");
        List<Player> players = askPlayersInfo();
        AbstractBoard board = askBoardInfo();
        ShapeUp game = askAdvancedShapeUp(pm, gc);
        // start game ?
    }

    private static ShapeUp askAdvancedShapeUp(PlayingModel pm, GameController gc) {
        String messageVersion = "A quel version de ShapeUp! souhaitez-vous jouer ? (1 : normal; 2 : advanced)";
        int nbVersion = askNumber(messageVersion);
        while (nbVersion > 2 || nbVersion < 1) {
            System.err.println("Le nombre de version doit être compris entre 1 et 2.");
            nbVersion = askNumber(messageVersion);
        }
        if (nbVersion == 1) {
            return  new ShapeUp(pm,gc);
        } else {
            return new AdvancedShapeUp(pm,gc);
        }
    }






    protected void startRound() {
        for (Player p :
                pm.getPlayerList()) {
            if (!pm.isGameFinished()) {
                System.out.println("\nA vous de jouer " + p.getPseudo());
                Card card = pm.getDeck().remove();
                System.out.println("Carte à placer :");
                System.out.println(card.toASCIIArt());
                startPlayerTurn(p, card);
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

    protected Coord play(Player p) {
        return p.play(pm.getBoard().getPotentialMinimumX(), pm.getBoard().getPotentialMinimumY(), pm.getBoard().getPotentialMaximumX(),
                pm.getBoard().getPotentialMaximumY());
    }

    protected Coord move(Player p, int action) {
        if (action == Player.CARD_CHOSING) {
            return p.move(pm.getBoard().getRealMinimumX(), pm.getBoard().getRealMinimumY(), pm.getBoard().getRealMaximumX(),
                    pm.getBoard().getRealMaximumY(), action);
        } else if (action == Player.CARD_DEPLACEMENT) {
            return p.move(pm.getBoard().getPotentialMinimumX(), pm.getBoard().getPotentialMinimumY(), pm.getBoard().getPotentialMaximumX(),
                    pm.getBoard().getPotentialMaximumY(), action);
        } else {
            return null;
        }
    }

    protected void startPlayerTurn(Player p, Card card) {
        Coord c;
        if (pm.isFirstRound()) {
            c = play(p);
            if (pm.isFirstTurn()) {
                pm.setIsFirstTurn(false);
            } else {
                while (pm.getBoard().isCardCorrectlyPlaced(c)) {
                    System.err.println("Carte mal placée.");
                    c = play(p);
                }
            }
            pm.getBoard().placeCard(c, card);
        } else {
            int res = p.askChoice();
            if (res == 1) {
                playTurn(p, card);
                if (p.askMoveChoice() == 1) {
                    moveTurn(p);
                }
            } else {
                moveTurn(p);
                System.out.println("Placer maintenant la carte :");
                System.out.println(card.toASCIIArt());
                playTurn(p, card);
            }
        }
    }

    protected void moveTurn(Player p) {
        Coord c = move(p, Player.CARD_CHOSING);
        while (!pm.getBoard().isCoordAlreadyExisting(c)) {
            System.err.println("Aucune carte ici.");
            c = move(p, Player.CARD_CHOSING);
        }
        Card aPlacer = pm.getBoard().removeCard(c);
        c = move(p, Player.CARD_DEPLACEMENT);
        while (pm.getBoard().isCoordAlreadyExisting(c) || pm.getBoard().isCardCorrectlyPlaced(c)) {
            System.err.println("Carte implacable ici.");
            c = move(p, Player.CARD_DEPLACEMENT);
        }
        pm.getBoard().placeCard(c, aPlacer);
    }

    protected void playTurn(Player p, Card card) {
        Coord c = play(p);
        while (pm.getBoard().isCardCorrectlyPlaced(c)) {
            System.err.println("Carte mal placée.");
            c = play(p);
        }
        pm.getBoard().placeCard(c, card);
    }

    private static AbstractBoard askBoardInfo() {
        String messageBoardNumber = "Avec quel type de plateau de jeu veux-tu jouer ? (Rectangle = 1, Triangle = 2, " +
                "Cercle = 3)";
        int boardNumber = askNumber(messageBoardNumber);
        while (boardNumber > 3 || boardNumber < 1) {
            System.err.println("Le nombre du plateau de jeu doit être compris entre 1 et 3.");
            boardNumber = askNumber(messageBoardNumber);
        }
        AbstractBoard ab;
        switch (boardNumber) {
            case 2:
                ab = new TriangleBoard();
                break;
            case 3:
                ab = new CircleBoard();
                break;
            case 1:
            default:
                ab = new RectangleBoard();
                break;
        }
        return ab;
    }

    public static String askString(String s) {
        System.out.println(s);
        Scanner sc = new Scanner(System.in);
        String res;
        try {
            res = sc.nextLine();
        } catch (InputMismatchException ime) {
            System.err.println("Attention, ton entrée n'est pas bonne.");
            sc.nextLine();
            return null;
        }
        return res;
    }

    public static int askNumber(String message) {
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        int res;
        try {
            res = sc.nextInt();
        } catch (InputMismatchException ime) {
            System.err.println("Attention, ton nombre n'est pas un entier.");
            sc.nextLine();
            return -1;
        }
        sc.nextLine();
        return res;
    }

    private static List<Player> askPlayersInfo() {
        // demande le nombre de joueur à l'utilisateur
        String messagePlayer = "À combien de joueur souhaites-tu jouer ?";
        int nbPlayer = askNumber(messagePlayer);
        while (nbPlayer > 3 || nbPlayer < 2) {
            System.err.println("Le nombre de joueur doit être 2 ou 3.");
            nbPlayer = askNumber(messagePlayer);
        }
        // demande le nombre de joueur réel à l'utilisateur
        String messageRealPlayer = "Combien y a-t-il de joueur réel parmis les " + nbPlayer + " joueurs ?";
        int nbRealPlayer = askNumber(messageRealPlayer);
        while (nbRealPlayer > nbPlayer || nbRealPlayer < 0) {
            System.err.println("Le nombre de joueur doit être compris entre 1 et " + nbPlayer + ".");
            nbRealPlayer = askNumber(messageRealPlayer);
        }
        int nbVirtualPlayer = nbPlayer - nbRealPlayer;
        String playerName;
        List<Player> players = new LinkedList<>();
        for (int i = 0; i < nbRealPlayer; i++) {
            playerName = askString("Comment s'appelle le joueur " + (i + 1) + " ?");
            players.add(new RealPlayer(playerName));
        }
        if (nbVirtualPlayer!=0) {
            String messageDifficulty = "Difficulté de l'IA ? (1 = facile)";
            int nbDifficutly = askNumber(messageDifficulty);
            while (nbDifficutly != 1) {
                System.err.println("Le nombre de la difficulté doit être 1.");
                nbDifficutly = askNumber(messageDifficulty);
            }
            for (int i = 0; i < nbVirtualPlayer; i++) {
                playerName = "Robot " + (i + 1);
                PlayingStrategy ps;
                ps = new PlayingStrategy1();
                // nb difficulty
                players.add(new VirtualPlayer(playerName, ps));
            }
        }
        return players;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
