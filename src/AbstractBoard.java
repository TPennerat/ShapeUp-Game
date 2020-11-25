import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBoard {

    protected Map<Coord, Card> placedCards;

    public AbstractBoard() {
        placedCards = new HashMap<>();
    }

    public abstract boolean isCardCorrectlyPlaced(Coord c);

    protected boolean isCoordAlreadyExisting(Coord c) {
        for (Coord coord:
             placedCards.keySet()) {
            if (coord.equals(c))
                return true;
        }
        return false;
    }

    public void placeCard(Coord coord, Card card) {
        placedCards.put(coord, card);
    }

    protected abstract boolean isAtLeastOneAdjacentCard(Coord c);

    public abstract void showBoard();
}
