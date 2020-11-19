import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ShapeUp {
    private List<Player> playerList;

    public static void main(String[] args) {
        System.out.println("Bienvenue dans ShapeUp !"); // salutation du joueur
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
}
