package model;

import view.ShapeUpGra;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class RectangleBoard extends AbstractBoard {

    @Override
    public boolean isCardCorrectlyPlaced(Coord c) {
        if (placedCards.isEmpty()) {
            return true;
        }
        return isAtLeastOneAdjacentCard(c) && !isCoordAlreadyExisting(c);
    }


    @Override
    public void showConsoleBoard() {
        Set<Coord> s = placedCards.keySet();
        List<Coord> l = new ArrayList<>(s);
        l.sort(Coord::compareTo);
        int x, y, lastX = l.get(0).getPosX(), lastY = l.get(0).getPosY();
        System.out.print(getRealMaximumY()+" ");
        for (Coord c:
             l) {
            x = c.getPosX();
            y = c.getPosY();
            if (lastY != y) {
                System.out.print('\n');
                System.out.print(y+" ");
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
        System.out.println();
        for (int i = getRealMinimumX(); i<=getRealMaximumX(); i++) {
            System.out.print("  "+i+"  ");
        }
        System.out.println();
    }

    @Override
    public JPanel renderGraphicBoard(ShapeUpGra sug) {
        Set<Coord> s = getPlacedCards().keySet();
        List<Coord> l = new ArrayList<>(s);
        l.sort(Coord::compareTo);
        HashMap<Coord, Coord> newCords = new HashMap<>();
        for (Coord coord:
             l) {
            int newCoordX = coord.getPosX()+Math.abs(getRealMinimumX());
            int newCoordY = coord.getPosY()+Math.abs(getRealMinimumY());
            newCords.put(coord, new Coord(newCoordX,newCoordY));
        }
        int xMappedCoord = Math.abs(getRealMinimumX()) + Math.abs(getRealMaximumX()) + 1;
        int yMappedCoord = Math.abs(getRealMinimumY()) + Math.abs(getRealMaximumY()) + 1;
        JPanel board = new JPanel(new GridLayout(yMappedCoord, xMappedCoord));
        for (int i=getRealMinimumY(); i<=getRealMaximumY(); i++) {
            for (int j=getRealMinimumX(); j<=getRealMaximumX(); j++) {
                Coord coord = new Coord(j,i);
                Card c = placedCards.get(coord);
                if (c == null) {
                    JPanel jp = new JPanel();
                    jp.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
                    board.add(jp);
                } else {
                    BufferedImage img = c.getImg();
                    JLabel newCard = new JLabel(new ImageIcon(img.getScaledInstance(img.getWidth() / (8+Math.abs(getRealMaximumX())),
                            img.getHeight() / (8+Math.abs(getRealMaximumY())), Image.SCALE_SMOOTH)));
                    newCard.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            sug.selectBoardCard(newCard, c);
                        }
                    });
                    board.add(newCard, newCords.get(coord).getPosY(), newCords.get(coord).getPosX());
                }
            }
        }
        return board;
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

    @Override
    public boolean isCardMoveable(Coord coord, Card card) {
        Map<Coord, Card> clo = placedCards;
        Coord ancienneCoord = null;
        for (Coord c:
                clo.keySet()) {
            if (clo.get(c).equals(card)) {
                ancienneCoord = c;
            }
        }
        clo.remove(ancienneCoord);
        if (isCardCorrectlyPlaced(coord)) {
            clo.put(coord, card);
            return true;
        } else {
            clo.put(ancienneCoord, card);
            return false;
        }
    }
}
