import java.util.*;

public class RectangleBoard extends AbstractBoard {

    @Override
    public boolean isCardCorrectlyPlaced(Coord c) {
        return isAtLeastOneAdjacentCard(c) && !isCoordAlreadyExisting(c);
    }

    @Override
    protected boolean isAtLeastOneAdjacentCard(Coord c) {
        Iterator<Coord> i = placedCards.keySet().iterator();
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
        Set<Coord> s = placedCards.keySet();
        List<Coord> l = new ArrayList<>(s);
        l.sort(null);
        for (Coord c:
             l) {
            System.out.println(placedCards.get(c).toASCIIArt());
        }
    }
}
