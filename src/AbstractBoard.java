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

    public int getRealMinimunX() {
        List<Coord> l = toList();
        if (l.size() != 0) {
            int minX = l.get(0).getPosX();
            for (Coord c :
                    l) {
                if (minX > c.getPosX())
                    minX = c.getPosX();
            }
            return minX;
        } else {
            return 1;
        }
    }

    public int getRealMinimumY() {
        List<Coord> l = toList();
        if (l.size() != 0) {
            int minY = l.get(0).getPosY();
            for (Coord c :
                    l) {
                if (minY > c.getPosY())
                    minY = c.getPosY();
            }
            return minY;
        } else {
            return 1;
        }
    }

    public int getRealMaximumX() {
        List<Coord> l = toList();
        if (l.size() != 0) {
            int maxX = l.get(0).getPosX();
            for (Coord c :
                    l) {
                if (maxX < c.getPosX())
                    maxX = c.getPosX();
            }
            return maxX;
        } else {
            return -1;
        }
    }

    public int getRealMaximumY() {
        List<Coord> l = toList();
        if (l.size() != 0) {
            int maxY = l.get(0).getPosY();
            for (Coord c :
                    l) {
                if (maxY < c.getPosY())
                    maxY = c.getPosY();
            }
            return maxY;
        } else {
            return -1;
        }
    }

    private List<Coord> toList() {
        Set<Coord> s = placedCards.keySet();
        return new ArrayList<>(s);
    }

    public abstract int getPotentialMinimumX();

    public abstract int getPotentialMinimumY();

    public abstract int getPotentialMaximumX();

    public abstract int getPotentialMaximumY();

    public void moveCard(Coord nouvelleCoord, Card aDeplacer) {
        placeCard(nouvelleCoord, aDeplacer);
    }

    public Card removeCard(Coord coord) {
        Card aPlacer = placedCards.get(coord);
        placedCards.remove(coord);
        return aPlacer;
    }
}
