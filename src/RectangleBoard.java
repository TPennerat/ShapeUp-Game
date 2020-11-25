import java.util.Iterator;

public class RectangleBoard extends AbstractBoard {

    @Override
    public boolean isCardCorrectlyPlaced(Coord c) {
        return isAtLeastOneAdjacentCard(c);
    }

    @Override
    protected boolean isAtLeastOneAdjacentCard(Coord c) {
        Iterator<Coord> i = placedCards.values().iterator();
        boolean isAtLeastOneAdjacentCard = false;
        while (i.hasNext()) {
            Coord coord = i.next();
            if (!isAtLeastOneAdjacentCard) {
                isAtLeastOneAdjacentCard = coord.isCoordAdjacent(c);
            }
        }
        return isAtLeastOneAdjacentCard;
    }

    @Override
    public void showBoard() {
        placedCards.forEach((k, v) -> System.out.println(k.toASCIIArt()));
    }
}
