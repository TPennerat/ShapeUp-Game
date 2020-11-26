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
        l.sort(Coord::compareTo);
        int x, y, lastX = l.get(0).getPosX(), lastY = l.get(0).getPosY();
        for (Coord c:
             l) {
            x = c.getPosX();
            y = c.getPosY();
            if (lastY != y) {
                System.out.print('\n');
            }
            if (x != getMinimunX()) {
                if (lastY != y) {
                    for (int i = getMinimunX(); i < x; i++) {
                        printSpace();
                    }
                } else {
                    if (x - lastX != 1) {
                        for (int i = lastX; i < x; i++) {
                            printSpace();
                        }
                    }
                }
            }
            Card card = placedCards.get(c);
            System.out.print(card.toASCIIArt());
            if (card.getShape() == Shape.TRIANGLE && !card.isFilled()) {
                System.out.print(" ");
            } else {
                System.out.print("  ");
            }
            lastX = x;
            lastY = y;
        }
    }
}
