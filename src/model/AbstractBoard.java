package model;

import view.ShapeUpGra;

import javax.swing.*;
import java.util.*;

public abstract class AbstractBoard {

    protected Map<Coord, Card> placedCards;

    public AbstractBoard() {
        placedCards = new HashMap<>();
    }

    public abstract boolean isCardCorrectlyPlaced(Coord c);

    public boolean isCoordAlreadyExisting(Coord c) {
        for (Coord coord:
             placedCards.keySet()) {
            if (coord.equals(c))
                return true;
        }
        return false;
    }

    public Map<Coord, Card> getPlacedCards() {
        return placedCards;
    }

    public void setPlacedCards(Map<Coord, Card> placedCards) {
        this.placedCards = placedCards;
    }

    public void placeCard(Coord coord, Card card) {
        placedCards.put(coord, card);
    }

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

    public abstract void showConsoleBoard();

    public abstract JPanel renderGraphicBoard(ShapeUpGra sug);

    protected void printSpace() {
        System.out.print("     ");
    }

    public int getRealMinimumX() {
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

    public List<Coord> toList() {
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

    public void accept(InterfaceVisitor iv, Player p) {
        p.setScore(iv.visitBoard(this, p.getVictoryCard()));
    }
}
