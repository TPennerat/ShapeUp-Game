package model;

import view.ShapeUp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RealPlayer extends Player {

    public RealPlayer(String pseudo) {
        super(pseudo);
    }

    @Override
    public Coord play(int minX, int minY, int maxX, int maxY) {
        Integer x = askOneCoord("x");
        Integer y = askOneCoord("y");
        while (x == null || y == null || x > maxX || x < minX || y > maxY || y < minY)  {
            System.err.println("Ta carte est en dehors des limites actuelles du plateau.");
             x = askOneCoord("x");
             y = askOneCoord("y");
        }
        return new Coord(x,y);
    }

    @Override
    public Coord move(int minX, int minY, int maxX, int maxY, int choice) {
        if (choice == CARD_DEPLACEMENT) {
            System.out.println("Entrer les nouvelles coordonnées de la carte");
        } else if (choice == CARD_CHOSING) {
            System.out.println("Entrer les coordonnées de la carte à déplacer");
        }
        Integer x = askOneCoord("x");
        Integer y = askOneCoord("y");
        while (x == null || y == null || x > maxX || x < minX || y > maxY || y < minY) {
            System.err.println("Le couple choisit est en dehors des limites actuelles du plateau.");
            if (choice == CARD_DEPLACEMENT) {
                System.out.println("Entrer les nouvelles coordonnées de la carte");
            } else if (choice == CARD_CHOSING) {
                System.out.println("Entrer les coordonnées de la carte à déplacer");
            }
            x = askOneCoord("x");
            y = askOneCoord("y");
        }
        return new Coord(x,y);
    }

    private Integer askOneCoord(String axis) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Position sur l'axe "+axis+" :");
        Integer res;
        try {
            res = sc.nextInt();
        } catch (InputMismatchException ime) {
            System.err.println("Attention, ton nombre n'est pas un entier.");
            sc.nextLine();
            res = null;
        }
        sc.nextLine();
        return res;
    }

    @Override
    public int askHandChoice(String messageWhichCard) {
        int numberWhichCard = ShapeUp.askNumber(messageWhichCard);
        while (numberWhichCard > hand.size() || numberWhichCard < 1) {
            System.err.println("La carte a jouer doit être entre 1 et "+hand.size()+".");
            numberWhichCard = ShapeUp.askNumber(messageWhichCard);
        }
        return numberWhichCard - 1;
    }
}
