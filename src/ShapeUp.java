import java.util.*;

public class ShapeUp {
    protected List<Player> playerList;
    protected AbstractBoard board;
    protected Queue<Card> deck;
    protected boolean isFirstRound;
    protected boolean isFirstTurn;

    public ShapeUp(List<Player> players, AbstractBoard board, Queue<Card> deck) {
        this.playerList = players;
        this.board = board;
        this.deck = deck;
        isFirstRound = true;
        isFirstTurn = true;
    }

    public static void main(String[] args) {
        System.out.println("Bienvenue dans ShapeUp !"); // salutation du joueur
        System.out.println("Préparation du paquet de cartes...");
        Queue<Card> cards = createCards();
        System.out.println("Paquet de cartes prêt.");
        List<Player> players = askPlayersInfo();
        AbstractBoard board = askBoardInfo();
        ShapeUp game = askAdvancedShapeUp(players, board, cards);
        game.startGame();
    }

    private static ShapeUp askAdvancedShapeUp(List<Player> players, AbstractBoard board, Queue<Card> cards) {
        String messageVersion = "A quel version de ShapeUp! souhaitez-vous jouer ? (1 : normal; 2 : advanced)";
        int nbVersion = askNumber(messageVersion);
        while (nbVersion > 2 || nbVersion < 1) {
            System.err.println("Le nombre de version doit être compris entre 1 et 2.");
            nbVersion = askNumber(messageVersion);
        }
        if (nbVersion == 1) {
            return  new ShapeUp(players, board, cards);
        } else {
            return new AdvancedShapeUp(players, board, cards);
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
        startRound();
        isFirstRound = false;
        while (!isGameFinished()) {
            startRound();
        }

        /*for (Player p : playerList) {  //Calcul du score pour chaque joueur (pas fini)
            board.accept(new Scorecalculator);
        }*/

    }

    protected boolean isGameFinished() {
        if (playerList.size() == 2) {
            return deck.size() == 0;
        } else {
            return deck.size() == 1;
        }
    }

    protected void startRound() {
        for (Player p :
                playerList) {
            if (!isGameFinished()) {
                System.out.println("\nA vous de jouer " + p.getPseudo());
                Card card = deck.remove();
                System.out.println("Carte à placer :");
                System.out.println(card.toASCIIArt());
                startPlayerTurn(p, card);
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

    protected Coord play(Player p) {
        return p.play(board.getPotentialMinimumX(), board.getPotentialMinimumY(), board.getPotentialMaximumX(),
                board.getPotentialMaximumY());
    }

    protected Coord move(Player p, int action) {
        if (action == Player.CARD_CHOSING) {
            return p.move(board.getRealMinimunX(), board.getRealMinimumY(), board.getRealMaximumX(),
                    board.getRealMaximumY(), action);
        } else if (action == Player.CARD_DEPLACEMENT) {
            return p.move(board.getPotentialMinimumX(), board.getPotentialMinimumY(), board.getPotentialMaximumX(),
                    board.getPotentialMaximumY(), action);
        } else {
            return null;
        }
    }

    protected void startPlayerTurn(Player p, Card card) {
        Coord c;
        if (isFirstRound) {
            c = play(p);
            if (isFirstTurn) {
                isFirstTurn = false;
            } else {
                while (!board.isCardCorrectlyPlaced(c)) {
                    System.err.println("Carte mal placée.");
                    c = play(p);
                }
            }
            board.placeCard(c, card);
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
        while (!board.isCoordAlreadyExisting(c)) {
            System.err.println("Aucune carte ici.");
            c = move(p, Player.CARD_CHOSING);
        }
        Card aPlacer = board.removeCard(c);
        c = move(p, Player.CARD_DEPLACEMENT);
        while (board.isCoordAlreadyExisting(c) || !board.isCardCorrectlyPlaced(c)) {
            System.err.println("Carte implacable ici.");
            c = move(p, Player.CARD_DEPLACEMENT);
        }
        board.placeCard(c, aPlacer);
    }

    protected void playTurn(Player p, Card card) {
        Coord c = play(p);
        while (!board.isCardCorrectlyPlaced(c)) {
            System.err.println("Carte mal placée.");
            c = play(p);
        }
        board.placeCard(c, card);
    }

    private static Queue<Card> createCards() {
        List<Card> shuffleCardList = new ArrayList<>();
        shuffleCardList.add(new Card(Color.RED, Shape.SQUARE, false));
        shuffleCardList.add(new Card(Color.BLUE, Shape.SQUARE, false));
        shuffleCardList.add(new Card(Color.GREEN, Shape.SQUARE, false));
        shuffleCardList.add(new Card(Color.RED, Shape.SQUARE, true));
        shuffleCardList.add(new Card(Color.BLUE, Shape.SQUARE, true));
        shuffleCardList.add(new Card(Color.GREEN, Shape.SQUARE, true));

        shuffleCardList.add(new Card(Color.RED, Shape.TRIANGLE, false));
        shuffleCardList.add(new Card(Color.BLUE, Shape.TRIANGLE, false));
        shuffleCardList.add(new Card(Color.GREEN, Shape.TRIANGLE, false));
        shuffleCardList.add(new Card(Color.RED, Shape.TRIANGLE, true));
        shuffleCardList.add(new Card(Color.BLUE, Shape.TRIANGLE, true));
        shuffleCardList.add(new Card(Color.GREEN, Shape.TRIANGLE, true));

        shuffleCardList.add(new Card(Color.RED, Shape.CIRCLE, false));
        shuffleCardList.add(new Card(Color.BLUE, Shape.CIRCLE, false));
        shuffleCardList.add(new Card(Color.GREEN, Shape.CIRCLE, false));
        shuffleCardList.add(new Card(Color.RED, Shape.CIRCLE, true));
        shuffleCardList.add(new Card(Color.BLUE, Shape.CIRCLE, true));
        shuffleCardList.add(new Card(Color.GREEN, Shape.CIRCLE, true));
        Collections.shuffle(shuffleCardList);
        return new LinkedList<>(shuffleCardList);
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
                PlayingStrategy ps = null;
                if (nbDifficutly == 1) {
                    ps = new PlayingStrategy1();
                }
                players.add(new VirtualPlayer(playerName, ps));
            }
        }
        return players;
    }
}
