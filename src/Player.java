public abstract class Player {
    protected String pseudo;
    protected Card victoryCard;

    public Player(String pseudo, Card victoryCard) {
        this.pseudo = pseudo;
        this.victoryCard = victoryCard;
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

    @Override
    public String toString() {
        return "Player{" +
                "pseudo='" + pseudo + '\'' +
                ", victoryCard=" + victoryCard +
                '}';
    }
}
