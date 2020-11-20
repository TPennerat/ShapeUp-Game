import java.util.*;

public class ShapeUp {
    private List<Player> playerList;
    private AbstractBoard board;
    private Set<Rule> rules;
    private Queue<Card> deck;
    private boolean isFirstTurn;

    public ShapeUp(List<Player> players, AbstractBoard board, Set<Rule> rules, Queue<Card> deck) {
        this.playerList = players;
        this.board = board;
        this.rules = rules;
        this.deck = deck;
        isFirstTurn = true;
    }

    public static void main(String[] args) {
        System.out.println("Bienvenue dans ShapeUp !"); // salutation du joueur
        Queue<Card> cards = createCards();
        List<Player> players = askPlayersInfo(cards);
        AbstractBoard board = askBoardInfo();
        Set<Rule> rules = askRulesInfo();
        ShapeUp game = new ShapeUp(players, board, rules, cards);
        game.startGame();
    }

    private void startGame() {
    }

    private static Queue<Card> createCards() {
        List<Card> shuffleCardList = new ArrayList<>();
        shuffleCardList.add(new Card(Color.RED, Shape.RECTANGLE, false));
        shuffleCardList.add(new Card(Color.BLUE, Shape.RECTANGLE, false));
        shuffleCardList.add(new Card(Color.GREEN, Shape.RECTANGLE, false));
        shuffleCardList.add(new Card(Color.RED, Shape.RECTANGLE, true));
        shuffleCardList.add(new Card(Color.BLUE, Shape.RECTANGLE, true));
        shuffleCardList.add(new Card(Color.GREEN, Shape.RECTANGLE, true));

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

    private static Set<Rule> askRulesInfo() {
        Set<Rule> rules = new HashSet<>();
        rules.add(new ClassicRule());
        return rules; //TODO rules je sais pas comment n'y prendre encore
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

    private static String askString(String s) {
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

    private static int askNumber(String message) {
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

    private static List<Player> askPlayersInfo(Queue<Card> c) {
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
        while (nbRealPlayer > nbPlayer || nbRealPlayer < 1) {
            System.err.println("Le nombre de joueur doit être compris entre 1 et " + nbPlayer + ".");
            nbRealPlayer = askNumber(messageRealPlayer);
        }
        int nbVirtualPlayer = nbPlayer - nbRealPlayer;
        String playerName;
        List<Player> players = new LinkedList<>();
        for (int i=0; i<nbRealPlayer; i++) {
            playerName = askString("Comment s'appelle le joueur "+(i+1)+" ?");
            players.add(new RealPlayer(playerName, c.remove()));
        }
        for (int i=0; i<nbVirtualPlayer; i++) {
            playerName = "Robot "+(i+1);
            players.add(new VirtualPlayer(playerName, c.remove()));
        }
        return players;
    }
}
