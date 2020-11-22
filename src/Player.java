import java.util.Scanner;

public abstract class Player {
    protected String pseudo;
    protected Card victoryCard;

    public Player(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Card getVictoryCard() {
        return victoryCard;
    }

    public void setVictoryCard(Card victoryCard) {
        this.victoryCard = victoryCard;
    }

    public abstract void play();

    public abstract void move();

    public int askChoice() {
        Scanner sc = new Scanner(System.in);
        String messageChoice = "Que voulez-vous faire en premier ? (1 : placer; 2 : bouger)";
        int choiceNumber = ShapeUp.askNumber(messageChoice);
        while (choiceNumber > 2 || choiceNumber < 1) {
            System.err.println("Le chiffre de l'action doit être 1 ou 2.");
            choiceNumber = ShapeUp.askNumber(messageChoice);
        }
        return choiceNumber;
    }

    @Override
    public String toString() {
        return "Player{" +
                "pseudo='" + pseudo + '\'' +
                ", victoryCard=" + victoryCard +
                '}';
    }
}
