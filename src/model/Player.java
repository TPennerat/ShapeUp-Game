package model;

import view.ShapeUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player {

    protected String pseudo;
    protected Card victoryCard;
    protected List<Card> hand;
    protected int score;

    public final static int CARD_DEPLACEMENT = 1;
    public final static int CARD_CHOSING = 2;

    public Player(String pseudo) {
        this.pseudo = pseudo;
        hand = new ArrayList<>();
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPseudo() {
        return pseudo;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void addCardHand(Card c) {
        hand.add(c);
    }

    public void removeCardHand(Card c) {
        hand.remove(c);
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

    public abstract Coord play(int minX, int minY, int maxX, int maxY);

    public abstract Coord move(int minX, int minY, int maxX, int maxY, int choice);

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
                ", hand=" + hand +
                ", score=" + score +
                '}';
    }

    public int askMoveChoice() {
        Scanner sc = new Scanner(System.in);
        String messageChoice = "Souhaitez-vous déplacer une carte ? (1 : oui; 2 : non)";
        int choiceNumber = ShapeUp.askNumber(messageChoice);
        while (choiceNumber > 2 || choiceNumber < 1) {
            System.err.println("Le chiffre de l'action doit être 1 ou 2.");
            choiceNumber = ShapeUp.askNumber(messageChoice);
        }
        return choiceNumber;
    }

    public abstract int askHandChoice(String messageWhichCard);
}
