package model;

import view.ShapeUpGra;

import javax.swing.*;
import java.util.*;

public class TriangleBoard extends AbstractBoard {

    @Override
    public boolean isCardCorrectlyPlaced(Coord c) {
        return isAtLeastOneAdjacentCard(c) || isCoordAlreadyExisting(c); //TODO think about new condition
    }

    @Override
    public void showConsoleBoard() {
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
            if (x != getRealMinimumX()) {
                if (lastY != y) {
                    for (int i = getRealMinimumX(); i < x; i++) {
                        printSpace();
                    }
                } else {
                    if (x - lastX != 1) {
                        for (int i = lastX; i <= x; i++) {
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

    @Override
    public JPanel renderGraphicBoard(ShapeUpGra sug) {
        return null;
    }

    @Override
    public int getPotentialMinimumX() {
        if (getRealMaximumX()- getRealMinimumX() == 4)
            return getRealMinimumX();
        return getRealMinimumX() - 1;
    }

    @Override
    public int getPotentialMinimumY() {
        if (getRealMaximumY()- getRealMinimumY() == 2)
            return getRealMinimumY();
        return getRealMinimumY() - 1;
    }

    @Override
    public int getPotentialMaximumX() {
        if (getRealMaximumX()- getRealMinimumX() == 4)
            return getRealMaximumX();
        return getRealMaximumX() + 1;
    }

    @Override
    public int getPotentialMaximumY() {
        if (getRealMaximumY() - getRealMinimumY() == 2)
            return getRealMaximumY();
        return getRealMaximumY() + 1;
    }
}
