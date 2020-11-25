import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBoard {

    protected Map<Card, Coord> placedCards;

    public AbstractBoard() {
        placedCards = new HashMap<>();
    }

    public abstract boolean isCardCorrectlyPlaced(Coord c);

    public void placeCard(Card card, Coord coord) {
        placedCards.put(card, coord);
    }

    protected abstract boolean isAtLeastOneAdjacentCard(Coord c);

    public abstract void showBoard();
}
