import java.util.*;

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

    protected void printSpace() {
        System.out.print("     ");
    }

    public int getMinimunX() {
        List<Coord> l = toList();
        int minX = l.get(0).getPosX();
        for (Coord c:
             l) {
            if (minX > c.getPosX())
                minX = c.getPosX();
        }
        return minX;
    }

    public int getMinimunY() {
        List<Coord> l = toList();
        int minY = l.get(0).getPosY();
        for (Coord c:
                l) {
            if (minY > c.getPosX())
                minY = c.getPosX();
        }
        return minY;
    }

    public int getMaximumX() {
        List<Coord> l = toList();
        int maxX = l.get(0).getPosX();
        for (Coord c:
                l) {
            if (maxX < c.getPosX())
                maxX = c.getPosX();
        }
        return maxX;
    }

    public int getMaximumY() {
        List<Coord> l = toList();
        int maxY = l.get(0).getPosX();
        for (Coord c:
                l) {
            if (maxY < c.getPosX())
                maxY = c.getPosX();
        }
        return maxY;
    }

    private List<Coord> toList() {
        Set<Coord> s = placedCards.keySet();
        return new ArrayList<>(s);
    }
}
